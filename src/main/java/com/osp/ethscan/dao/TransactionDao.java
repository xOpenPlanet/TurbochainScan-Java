package com.osp.ethscan.dao;

import java.util.List;

import com.osp.ethscan.bean.AccountTransactionBean;
import com.osp.ethscan.bean.TransactionBean;

public interface TransactionDao {
	
	/**
	 * 返回当前日期的交易数
	 * @param date
	 * @return
	 */
	Integer getTxnCountByDate(String date);
	/**
	 * 取得当前地址当前页的交易信息
	 * @param address
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	List<AccountTransactionBean> getTxListByAddress(String address,Long startIndex,Integer endIndex);
	TransactionBean getTransactionByHash(String hash);
	List<TransactionBean> getTxListByBlockNumber(String BlockNumber);
	List<AccountTransactionBean> getCurrentPageAccountsTransaction(Long startIndex,Integer endIndex);
	Long countAccountsTransaction();
	Long getTransactionTimeByAccountsTransactionTable(String txnHash);
	Long getTxCountByAddress(String address);
}
