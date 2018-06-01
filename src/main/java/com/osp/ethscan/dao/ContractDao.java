package com.osp.ethscan.dao;

import com.osp.ethscan.bean.ContractAccountBean;

/**
 * 智能合约数据库操作
 * @author zhangmingcheng
 */
public interface ContractDao {
	/**
	 * 根据交易hash取得智能合约信息
	 * @param hash
	 * @return
	 */
	ContractAccountBean getContractByHash(String hash);
}
