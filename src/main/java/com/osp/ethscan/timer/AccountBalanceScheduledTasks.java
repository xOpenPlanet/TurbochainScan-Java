package com.osp.ethscan.timer;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.web3j.protocol.admin.Admin;

import com.osp.ethscan.dao.AccountDao;
import com.osp.ethscan.service.BlockService;
import com.osp.ethscan.service.EthService;
import com.osp.ethscan.util.Web3JClient;

/**
 * 维护账户余额信息定时器
 * 
 * @author zhangmingcheng
 */

@Component
public class AccountBalanceScheduledTasks {

	@Autowired
	private AccountDao accountDaoImpl;

	@Autowired
	private BlockService blockServiceImpl;

	@Autowired
	private EthService ethServiceImpl;

	// 存储所有账户余额
	public static Map<String, Double> accountBalances = new HashMap<>();

	/**
	 * 定时维护账户余额
	 */
	@Scheduled(fixedDelay = 10000)
	public void storeAccountBalance() {
		List<String> accountLists = accountDaoImpl.getAllAccounts();
		BigInteger blockHeight = blockServiceImpl.getBlockHeight();
		if (blockHeight == null) {
			return;
		}
		for (String address : accountLists) {
			accountBalances.put(address, ethServiceImpl.getBalanceByAdress(address, blockHeight));
		}
	}

	public Admin getAdmin() {
		return Web3JClient.getAdminClient();
	}
}
