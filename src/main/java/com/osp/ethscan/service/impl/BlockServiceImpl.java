package com.osp.ethscan.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthBlockNumber;

import com.osp.ethscan.bean.BlockBean;
import com.osp.ethscan.bean.TransactionBean;
import com.osp.ethscan.dao.BlockDao;
import com.osp.ethscan.dao.TransactionDao;
import com.osp.ethscan.model.BlockBriefInfo;
import com.osp.ethscan.model.BlockDetailInfo;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.service.BlockService;
import com.osp.ethscan.util.BaseUtil;
import com.osp.ethscan.util.Web3JClient;

/**
 * 存储区块信息业务逻辑类
 * 
 * @author zhangmingcheng
 */
@Service
public class BlockServiceImpl implements BlockService {

	@Autowired
	private BlockDao blockDaoImpl;
	@Autowired
	private TransactionDao transactionDaoImpl;

	/**
	 * 组织当前页区块的数据
	 */
	@Override
	public List<BlockBriefInfo> getCurrentPageBlocks(Integer page, Integer pagesize, ResponseObject ro) {
		List<BlockBriefInfo> blockInfos = new ArrayList<>();
		BigInteger current_block_height = blockDaoImpl.getCurrentBlockHeight();
		BigInteger pageSize = new BigInteger(String.valueOf(pagesize));
		if (current_block_height.mod(pageSize).compareTo(new BigInteger("0")) == 0) {
			ro.setValue("countPage", current_block_height.divide(pageSize));
		} else {
			ro.setValue("countPage", current_block_height.divide(pageSize).add(new BigInteger("1")));
		}
		BigInteger startIndex = current_block_height
				.subtract(new BigInteger(String.valueOf(page - 1)).multiply(pageSize));
		BigInteger endIndex = startIndex.subtract(pageSize);
		ro.setValue("endIndex", endIndex.add(new BigInteger("1")));
		if (endIndex.compareTo(new BigInteger("0")) <= 0) {
			endIndex = new BigInteger("0");
			ro.setValue("endIndex", new BigInteger("1"));
		}
		ro.setValue("startIndex", startIndex);
		ro.setValue("blockHeight", current_block_height.add(new BigInteger("1")));
		List<BlockBean> blockLists = blockDaoImpl.getCurrentPageBlocks(startIndex, endIndex);
		for (BlockBean blockBean : blockLists) {
			BlockBriefInfo blockBriefInfo = new BlockBriefInfo();
			blockBriefInfo.setHeight(blockBean.getNumber());
			blockBriefInfo.setAge(BaseUtil.timeInterval(Long.parseLong(blockBean.getTimestamp())));
			blockBriefInfo.setTxn(blockBean.getTransactions_size());
			blockBriefInfo.setUncles(blockBean.getUncles_size());
			blockBriefInfo.setMiner(blockBean.getMiner());
			long gasUsed = Long.parseLong(blockBean.getGas_used());
			blockBriefInfo.setGasUsed(gasUsed);
			blockBriefInfo.setGasLimit(Long.parseLong(blockBean.getGas_limit()));
			List<TransactionBean> transactionBeans = transactionDaoImpl.getTxListByBlockNumber(blockBean.getNumber());
			double gasEther = 0;
			for (TransactionBean transactionBean : transactionBeans) {
				gasEther += Long.parseLong(transactionBean.getGas()) * Long.parseLong(transactionBean.getGas_price())
						/ Math.pow(10, 18);
			}
			blockBriefInfo.setReward(gasEther + 5 + blockBean.getUncles_size() * 5.0 / 32);// 区块的固定奖励+gas花费+叔块奖励
			if (gasUsed != 0) {
				double avgGasPrivate = gasEther * Math.pow(10, 9) / gasUsed;
				blockBriefInfo.setAvgGasPrice(avgGasPrivate);
			}
			blockInfos.add(blockBriefInfo);
		}
		return blockInfos;
	}

	/**
	 * 区块高度
	 * 
	 * @return
	 */
	@Override
	public BigInteger getBlockHeight() {
		Request<?, EthBlockNumber> request = this.getAdmin().ethBlockNumber();
		EthBlockNumber ethBlockNumber = null;
		try {
			ethBlockNumber = request.send();
			return ethBlockNumber.getBlockNumber();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 区块高度
	 * 
	 * @return
	 */
	@Override
	public BigInteger getCurrentBlockHeight() {
		return blockDaoImpl.getCurrentBlockHeight();
	}

	/**
	 * 取得高度为number区块的数据
	 * 
	 * @param number
	 * @return
	 */
	@Override
	public BlockDetailInfo getBlockByNumber(BigInteger number, ResponseObject ro) {
		BlockDetailInfo blockDetailInfo = new BlockDetailInfo();
		BigInteger blockHeight = blockDaoImpl.getCurrentBlockHeight();
		if (number.compareTo(new BigInteger("1")) < 0) {
			ro.setValue("preMenu", false);
		} else {
			ro.setValue("preMenu", true);
		}
		if (number.compareTo(blockHeight) >= 0) {
			ro.setValue("afterMenu", false);
		} else {
			ro.setValue("afterMenu", true);
		}
		BlockBean blockBean = blockDaoImpl.getBlockByNumber(number);
		if (blockBean == null) {
			ro.setMessage("error!");
			return null;
		}
		blockDetailInfo.setHeight(number);
		blockDetailInfo.setTimeStamp(BaseUtil.timeInterval(Long.parseLong(blockBean.getTimestamp())));
		blockDetailInfo.setTransactionNum(blockBean.getTransactions_size());
		blockDetailInfo.setHash(blockBean.getHash());
		blockDetailInfo.setParentHash(blockBean.getParent_hash());
		blockDetailInfo.setSha3Uncles(blockBean.getSha3_uncles());
		blockDetailInfo.setMiner(blockBean.getMiner());
		blockDetailInfo.setDiffculty(blockBean.getDifficulty());
		blockDetailInfo.setTotalDiffculty(blockBean.getTotal_difficulty());
		blockDetailInfo.setSize(blockBean.getSize());
		blockDetailInfo.setGasUsed(Long.parseLong(blockBean.getGas_used()));
		blockDetailInfo.setGasLimit(Long.parseLong(blockBean.getGas_limit()));
		blockDetailInfo.setNonce(blockBean.getNonce());
		List<TransactionBean> transactionBeans = transactionDaoImpl.getTxListByBlockNumber(blockBean.getNumber());
		double gasEther = 0;
		for (TransactionBean transactionBean : transactionBeans) {
			gasEther += Long.parseLong(transactionBean.getGas()) * Long.parseLong(transactionBean.getGas_price())
					/ Math.pow(10, 18);
		}
		blockDetailInfo.setBlockReward(5 + gasEther + blockBean.getUncles_size() * 5.0 / 32);
		blockDetailInfo.setExtraData(blockBean.getExtra_data());
		return blockDetailInfo;
	}

	public Admin getAdmin() {
		return Web3JClient.getAdminClient();
	}

	@Override
	public String getBlockDifficulty(BigInteger number) {
		BlockBean blockBean = blockDaoImpl.getBlockByNumber(number);
		if (blockBean != null) {
			BigDecimal difficult = new BigDecimal(blockBean.getTotal_difficulty()).divide(new BigDecimal("1000000000"));
			String result = difficult.toString();
			int len = result.length();
			if (result.indexOf('.') + 3 < len) {
				return result.substring(0, result.indexOf('.')+3) + " TH";
			}
			return result + " TH";
		}
		return null;
	}
}
