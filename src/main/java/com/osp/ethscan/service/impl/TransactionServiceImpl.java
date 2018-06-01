package com.osp.ethscan.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;

import com.osp.ethscan.bean.AccountTransactionBean;
import com.osp.ethscan.bean.ChartBean;
import com.osp.ethscan.bean.ContractAccountBean;
import com.osp.ethscan.bean.TransactionBean;
import com.osp.ethscan.dao.ContractDao;
import com.osp.ethscan.dao.TransactionDao;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.model.TransactionBriefInfo;
import com.osp.ethscan.model.TransactionInfo;
import com.osp.ethscan.service.BlockService;
import com.osp.ethscan.service.EthService;
import com.osp.ethscan.service.TransactionService;
import com.osp.ethscan.util.BaseUtil;
import com.osp.ethscan.util.Page;
import com.osp.ethscan.util.Web3JClient;

/**
 * 交易服务类
 * 
 * @author zhangmingcheng
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionDao transactionDaoImpl;

	@Autowired
	private BlockService blockServiceImpl;

	@Autowired
	private ContractDao contractDaoImpl;
	
	@Autowired
	private EthService ethServiceImpl;

	/**
	 * 组织最近14天区块链的交易信息
	 * 
	 * @param ro
	 */
	@Override
	public void organizeChartOfTxns(ResponseObject ro) {
		List<String> formatDateLists = BaseUtil.getPastDaysList(14, "MM/dd");
		List<ChartBean> chartBeanLists = new ArrayList<>();
		// 折线图横坐标
		ro.setValue("categories", formatDateLists);
		List<String> dateLists = BaseUtil.getPastDaysList(14, "yyyy-MM-dd");
		for (String day : dateLists) {
			int txns = transactionDaoImpl.getTxnCountByDate(day);
			long dt = BaseUtil.dateToStamp(day + " 00:00:00");
			ChartBean chartBean = new ChartBean(txns, dt, day);
			chartBeanLists.add(chartBean);
		}
		// 折线图纵坐标
		ro.setValue("data", chartBeanLists);
	}

	/**
	 * 根据账户地址取得其交易列表
	 */
	@Override
	public List<TransactionInfo> getTxListByAddress(String address, Integer page, Integer pagesize, ResponseObject ro) {
		List<TransactionInfo> transactionInfoLists = new ArrayList<>();
		Page pages = new Page(page, pagesize);
		pages.setAllRow(transactionDaoImpl.getTxCountByAddress(address));
		List<AccountTransactionBean> accounts_transactions = transactionDaoImpl.getTxListByAddress(address,
				pages.countOffset(), pagesize);
		ro.setValue("page", pages);
		BigInteger blockHeight = blockServiceImpl.getBlockHeight();
		if (blockHeight != null) {
			ro.setValue("balance", ethServiceImpl.getBalanceByAdress(address, blockHeight));
		} else {
			ro.setValue("balance", 0);
		}
		for (AccountTransactionBean accountTransactionBean : accounts_transactions) {
			TransactionBean transactionBean = transactionDaoImpl
					.getTransactionByHash(accountTransactionBean.getTxn_address());
			TransactionInfo transactionInfo = new TransactionInfo();
			transactionInfo.setAge(BaseUtil.timeInterval(accountTransactionBean.getTxn_timestamp()));
			transactionInfo.setBlock(new BigInteger(transactionBean.getBlock_number()));
			transactionInfo.setFrom(transactionBean.getSender());
			transactionInfo.setTo(transactionBean.getReceiver());
			transactionInfo.setTxFee(Long.parseLong(transactionBean.getGas())
					* Long.parseLong(transactionBean.getGas_price()) / Math.pow(10, 18));
			transactionInfo.setTxHash(transactionBean.getHash());
			transactionInfo
					.setValue(new BigDecimal(transactionBean.getValue()).divide(new BigDecimal("1000000000000000000")));
			transactionInfo.setType(accountTransactionBean.getTxn_type());
			if (transactionInfo.getType() == 3) {
				transactionInfo.setTo(accountTransactionBean.getReceiver());
				transactionInfo.setStatus("create contract");
			} else if (transactionBean.getSender().equals(address) == true) {
				transactionInfo.setStatus("sender");
			} else {
				transactionInfo.setStatus("receiver");
			}
			transactionInfoLists.add(transactionInfo);
		}

		return transactionInfoLists;
	}

	/**
	 * 根据区块高度取得其交易列表
	 */
	@Override
	public List<TransactionInfo> getTxListByBlockNumber(BigInteger number, String age, ResponseObject ro) {
		List<TransactionInfo> transactionInfos = new ArrayList<>();
		Page pages = new Page();
		List<TransactionBean> transactionBeanLists = transactionDaoImpl.getTxListByBlockNumber(number.toString());
		pages.setAllRow(transactionBeanLists.size());
		ro.setValue("page", pages);
		for (TransactionBean transactionBean : transactionBeanLists) {
			TransactionInfo transactionInfo = new TransactionInfo();
			transactionInfo.setAge(age);
			transactionInfo.setBlock(number);
			transactionInfo.setFrom(transactionBean.getSender());
			transactionInfo.setTxFee(Long.parseLong(transactionBean.getGas())
					* Long.parseLong(transactionBean.getGas_price()) / Math.pow(10, 18));
			transactionInfo.setTxHash(transactionBean.getHash());
			if (transactionBean.getReceiver() == null) {
				transactionInfo.setType(3);
				ContractAccountBean contractAccountBean = contractDaoImpl.getContractByHash(transactionBean.getHash());
				transactionInfo.setTo(contractAccountBean.getAddress());
			} else {
				transactionInfo.setTo(transactionBean.getReceiver());
				if (transactionBean.getInput().length() > 2) {
					transactionInfo.setType(2);
				} else {
					transactionInfo.setType(1);
				}
			}
			transactionInfo
					.setValue(new BigDecimal(transactionBean.getValue()).divide(new BigDecimal("1000000000000000000")));
			transactionInfos.add(transactionInfo);
		}
		return transactionInfos;
	}

	/**
	 * 根据交易hash取得交易详细信息
	 */
	@Override
	public TransactionBriefInfo geTransactionByHash(String hash, ResponseObject ro) {
		TransactionBriefInfo transactionBriefInfo = new TransactionBriefInfo();
		TransactionBean transactionBean = transactionDaoImpl.getTransactionByHash(hash);
		if (transactionBean == null) {
			ro.setMessage("this txn is not exist,error!");
			return null;
		}
		transactionBriefInfo.setBlockHeight(transactionBean.getBlock_number());
		transactionBriefInfo.setFee(Long.parseLong(transactionBean.getGas())
				* Long.parseLong(transactionBean.getGas_price()) / Math.pow(10, 18));
		transactionBriefInfo.setFrom(transactionBean.getSender());
		// transactionBriefInfo.setGasLimit();
		transactionBriefInfo.setGasPrice(Long.parseLong(transactionBean.getGas_price()));
		transactionBriefInfo.setGasUsedByTxn(Long.parseLong(transactionBean.getGas()));
		transactionBriefInfo.setInputData(transactionBean.getInput());
		transactionBriefInfo.setNonce(transactionBean.getNonce());
		Long txnTimestamp = transactionDaoImpl.getTransactionTimeByAccountsTransactionTable(hash);
		transactionBriefInfo.setTimeStamp(BaseUtil.timeInterval(txnTimestamp));
		transactionBriefInfo.setTxHash(transactionBean.getHash());
		transactionBriefInfo.setTxReceiptStatus("Success");
		transactionBriefInfo
				.setValue(new BigDecimal(transactionBean.getValue()).divide(new BigDecimal("1000000000000000000")));
		if (transactionBean.getReceiver() == null) {
			transactionBriefInfo.setType(3);
			ContractAccountBean contractAccountBean = contractDaoImpl.getContractByHash(transactionBean.getHash());
			transactionBriefInfo.setTo(contractAccountBean.getAddress());
		} else {
			transactionBriefInfo.setTo(transactionBean.getReceiver());
			if (transactionBean.getInput().length() > 2) {
				transactionBriefInfo.setType(2);
			} else {
				transactionBriefInfo.setType(1);
			}
		}
		return transactionBriefInfo;
	}

	/**
	 * 取得当前页的交易信息
	 */
	@Override
	public List<TransactionInfo> getCurrentPageTxList(Integer page, Integer pagesize, ResponseObject ro) {
		List<TransactionInfo> transactionInfoLists = new ArrayList<>();
		Page pages = new Page(page, pagesize);
		pages.setAllRow(transactionDaoImpl.countAccountsTransaction());
		Long startIndex = pages.countOffset();
		List<AccountTransactionBean> accountTransactionList = transactionDaoImpl
				.getCurrentPageAccountsTransaction(startIndex, pagesize);
		ro.setValue("page", pages);
		for (AccountTransactionBean accountTransactionBean : accountTransactionList) {
			TransactionBean transactionBean = transactionDaoImpl
					.getTransactionByHash(accountTransactionBean.getTxn_address());
			TransactionInfo transactionInfo = new TransactionInfo();
			transactionInfo.setAge(BaseUtil.timeInterval(accountTransactionBean.getTxn_timestamp()));
			transactionInfo.setBlock(new BigInteger(transactionBean.getBlock_number()));
			transactionInfo.setFrom(transactionBean.getSender());
			transactionInfo.setTxFee(Long.parseLong(transactionBean.getGas())
					* Long.parseLong(transactionBean.getGas_price()) / Math.pow(10, 18));
			transactionInfo.setTxHash(transactionBean.getHash());
			if (transactionBean.getReceiver() == null) {
				transactionInfo.setType(3);
				ContractAccountBean contractAccountBean = contractDaoImpl.getContractByHash(transactionBean.getHash());
				transactionInfo.setTo(contractAccountBean.getAddress());
			} else {
				transactionInfo.setTo(transactionBean.getReceiver());
				if (transactionBean.getInput().length() > 2) {
					transactionInfo.setType(2);
				} else {
					transactionInfo.setType(1);
				}
			}
			transactionInfo
					.setValue(new BigDecimal(transactionBean.getValue()).divide(new BigDecimal("1000000000000000000")));
			transactionInfoLists.add(transactionInfo);
		}
		return transactionInfoLists;
	}

	public Admin getAdmin() {
		return Web3JClient.getAdminClient();
	}

	/**
	 * 区块链交易总数
	 */
	@Override
	public Long countAccountsTransaction() {
		return transactionDaoImpl.countAccountsTransaction();
	}
}
