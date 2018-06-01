package com.osp.ethscan.model;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 交易简略信息
 * 
 * @author zhangmingcheng
 */
public class TransactionInfo {
	private String txHash;
	private BigInteger block;
	private String age;
	private String from;
	private String to;
	private BigDecimal value;
	private double txFee;
	private int type;//值为1表示外部账户交易，值为2表示合约交易,值为3表示创建合约
	private String status;

	public String getTxHash() {
		return txHash;
	}

	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}

	public BigInteger getBlock() {
		return block;
	}

	public void setBlock(BigInteger block) {
		this.block = block;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public double getTxFee() {
		return txFee;
	}

	public void setTxFee(double txFee) {
		this.txFee = txFee;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
