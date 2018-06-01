package com.osp.ethscan.service.impl;

import java.io.IOException;
import java.math.BigInteger;

import org.springframework.stereotype.Service;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetBlockTransactionCountByNumber;
import org.web3j.protocol.core.methods.response.EthHashrate;

import com.osp.ethscan.service.EthService;
import com.osp.ethscan.util.Web3JClient;

/**
 * 区块链状态信息
 * 
 * @author zhangmingcheng
 */
@Service
public class EthServiceImpl implements EthService {

	/**
	 * 获取以太坊当前hashrate
	 */
	@Override
	public BigInteger getHashrate() {
		Request<?, EthHashrate> request = this.getAdmin().ethHashrate();
		EthHashrate ethHashrate = null;
		try {
			ethHashrate = request.send();
			return ethHashrate.getHashrate();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Double getBalanceByAdress(String address, BigInteger blockHeight) {
		Request<?, EthGetBalance> request = this.getAdmin().ethGetBalance(address,
				new DefaultBlockParameterNumber(blockHeight));
		EthGetBalance ethGetBalance = null;
		try {
			ethGetBalance = request.send();
			return Double.parseDouble(ethGetBalance.getBalance().toString()) * 1.0 / Math.pow(10, 18);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Double(0);
	}
	
	/**
	 * 取得当前区块的交易数
	 * 
	 * @param number
	 * @return
	 */
	@Override
	public BigInteger getBlockTxnCountByNumber(BigInteger number) {
		Request<?, EthGetBlockTransactionCountByNumber> request = this.getAdmin()
				.ethGetBlockTransactionCountByNumber(new DefaultBlockParameterNumber(number));
		BigInteger transactionCount = null;
		try {
			transactionCount = request.send().getTransactionCount();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return transactionCount;
	}


	public Admin getAdmin() {
		return Web3JClient.getAdminClient();
	}

}
