package com.osp.ethscan.service;

import java.util.List;

import com.osp.ethscan.model.AccountInfo;
import com.osp.ethscan.model.ResponseObject;

public interface AccountService {
	List<AccountInfo> getAllAccounts(Integer page,Integer pagesize,ResponseObject ro);

	List<AccountInfo> getAllContractAccounts();
	/**
	 * 判断用户是否存在
	 * @param address
	 * @return
	 */
	Boolean existUser(String address);
}
