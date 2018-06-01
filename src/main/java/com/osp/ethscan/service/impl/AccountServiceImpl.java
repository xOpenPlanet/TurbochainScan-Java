package com.osp.ethscan.service.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;

import com.osp.ethscan.dao.AccountDao;
import com.osp.ethscan.dao.TransactionDao;
import com.osp.ethscan.model.AccountInfo;
import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.service.AccountService;
import com.osp.ethscan.service.BlockService;
import com.osp.ethscan.timer.AccountBalanceScheduledTasks;
import com.osp.ethscan.util.Page;
import com.osp.ethscan.util.Web3JClient;

/**
 * 账户信息业务逻辑类
 * 
 * @author zhangmingcheng
 */
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountDao accountDaoImpl;

	@Autowired
	private BlockService blockServiceImpl;

	@Autowired
	private TransactionDao transactionDaoImpl;

	/**
	 * 获取当前页账户地址(包括外部账户、合约账户)
	 */
	@Override
	public List<AccountInfo> getAllAccounts(Integer page, Integer pagesize, ResponseObject ro) {
		List<AccountInfo> accountInfoLists = new ArrayList<>();
		Page pages = new Page(page, pagesize);
		pages.setAllRow(accountDaoImpl.countAccountWithUserAndContracts());
		Long startIndex = pages.countOffset();
		ro.setValue("page", pages);
		List<String> accountLists = accountDaoImpl.getCurrentPageAccounts(startIndex, pagesize);
		for (String address : accountLists) {
			AccountInfo accountInfo = new AccountInfo();
			accountInfo.setAddress(address);
			if (AccountBalanceScheduledTasks.accountBalances.containsKey(address) == true) {
				accountInfo.setBalance(AccountBalanceScheduledTasks.accountBalances.get(address));
			} else {// 防止用户余额信息并没有维护到accountBalances
				BigInteger blockHeight = blockServiceImpl.getBlockHeight();
				if (blockHeight == null) {
					continue;
				}
				Request<?, EthGetBalance> request = this.getAdmin().ethGetBalance(address,
						new DefaultBlockParameterNumber(blockHeight));
				EthGetBalance ethGetBalance = null;
				try {
					ethGetBalance = request.send();
					double balance = Double.parseDouble(ethGetBalance.getBalance().toString()) * 1.0 / Math.pow(10, 18);
					accountInfo.setBalance(balance);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			accountInfo.setTxCount(transactionDaoImpl.getTxCountByAddress(address));
			accountInfo.setType(accountDaoImpl.isUserAccount(address));// 0为合约账户，1为外部账户
			accountInfoLists.add(accountInfo);
		}
		return accountInfoLists;
	}

	/**
	 * 获取所有合约账户信息
	 */
	@Override
	public List<AccountInfo> getAllContractAccounts() {
		return null;
	}

	public Admin getAdmin() {
		return Web3JClient.getAdminClient();
	}

	/**
	 * 判断用户是否存在
	 */
	@Override
	public Boolean existUser(String address) {
		return accountDaoImpl.existUser(address);
	}
}
