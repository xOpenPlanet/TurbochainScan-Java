package com.osp.ethscan.dao;

import java.util.List;

public interface AccountDao {
	List<String> getCurrentPageAccounts(Long startIndex,Integer pagesize);
	Long countAccountWithUserAndContracts();
	int isUserAccount(String address);
	/**
	 * 取得所有账户地址（外部账户、合约账户）
	 * @return
	 */
	List<String> getAllAccounts();
	/**
	 * 地址是否存在
	 * @param address
	 * @return
	 */
	Boolean existUser(String address);
} 
