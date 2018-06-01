package com.osp.ethscan.service;

import java.math.BigInteger;
import java.util.List;

import com.osp.ethscan.model.ResponseObject;
import com.osp.ethscan.model.TransactionBriefInfo;
import com.osp.ethscan.model.TransactionInfo;

/**
 * 交易服务
 * 
 * @author zhangmingcheng
 */
public interface TransactionService {
	List<TransactionInfo> getTxListByAddress(String address, Integer page, Integer pagesize, ResponseObject ro);

	List<TransactionInfo> getTxListByBlockNumber(BigInteger number, String age, ResponseObject ro);

	List<TransactionInfo> getCurrentPageTxList(Integer page, Integer pagesize, ResponseObject ro);

	TransactionBriefInfo geTransactionByHash(String hash, ResponseObject ro);

	/**
	 * 组织最近14天的交易信息
	 * 
	 * @param ro
	 */
	void organizeChartOfTxns(ResponseObject ro);
	
	/**
	 * 区块链交易总数
	 * @return
	 */
	Long countAccountsTransaction();
}
