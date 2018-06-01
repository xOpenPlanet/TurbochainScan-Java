package com.osp.ethscan.service;

import java.math.BigInteger;

/**
 * ether命令
 * @author zhangmingcheng
 */
public interface EthService {
	BigInteger getHashrate();
	/**
	 * 根据账户地址获取账户余额
	 * @param address
	 * @param blockHeight
	 * @return
	 */
	Double getBalanceByAdress(String address, BigInteger blockHeight);
	
	BigInteger getBlockTxnCountByNumber(BigInteger number);
}
