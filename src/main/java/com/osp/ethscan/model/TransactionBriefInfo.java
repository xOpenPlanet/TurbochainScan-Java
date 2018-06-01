package com.osp.ethscan.model;

import java.math.BigDecimal;

/**
 * 详细交易信息
 * 
 * @author zhangmingcheng
 */
public class TransactionBriefInfo {
	private String txHash;
	private String txReceiptStatus;
	private String blockHeight;
	private String timeStamp;
	private String from;
	private String to;
	private BigDecimal value;
	private long gasLimit;
	private long gasUsedByTxn;
	private long gasPrice;
	private double fee;
	private String nonce;
	private String inputData;
	private Integer type;//1、外部交易 2、合约交易 3、代理交易
	
	public String getTxHash() {
		return txHash;
	}
	public void setTxHash(String txHash) {
		this.txHash = txHash;
	}
	public String getTxReceiptStatus() {
		return txReceiptStatus;
	}
	public void setTxReceiptStatus(String txReceiptStatus) {
		this.txReceiptStatus = txReceiptStatus;
	}
	public String getBlockHeight() {
		return blockHeight;
	}
	public void setBlockHeight(String blockHeight) {
		this.blockHeight = blockHeight;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
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
	public long getGasLimit() {
		return gasLimit;
	}
	public void setGasLimit(long gasLimit) {
		this.gasLimit = gasLimit;
	}
	public long getGasUsedByTxn() {
		return gasUsedByTxn;
	}
	public void setGasUsedByTxn(long gasUsedByTxn) {
		this.gasUsedByTxn = gasUsedByTxn;
	}
	public long getGasPrice() {
		return gasPrice;
	}
	public void setGasPrice(long gasPrice) {
		this.gasPrice = gasPrice;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getInputData() {
		return inputData;
	}
	public void setInputData(String inputData) {
		this.inputData = inputData;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
