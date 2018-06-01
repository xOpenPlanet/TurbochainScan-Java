package com.osp.ethscan.model;

import java.math.BigInteger;

/**
 * 区块详细信息
 * 
 * @author zhangmingcheng
 */
public class BlockDetailInfo {
	
	private BigInteger height;
	private String timeStamp;
	private int transactionNum;
	private String hash;
	private String parentHash;
	private String sha3Uncles;
	private String miner;
	private String diffculty;
	private String totalDiffculty;
	private String size;
	private Long gasUsed;
	private Long gasLimit;
	private String nonce;
	private double blockReward;
	private double unclesReward;
	private String extraData;

	public BigInteger getHeight() {
		return height;
	}

	public void setHeight(BigInteger height) {
		this.height = height;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public int getTransactionNum() {
		return transactionNum;
	}

	public void setTransactionNum(int transactionNum) {
		this.transactionNum = transactionNum;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getParentHash() {
		return parentHash;
	}

	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}

	public String getSha3Uncles() {
		return sha3Uncles;
	}

	public void setSha3Uncles(String sha3Uncles) {
		this.sha3Uncles = sha3Uncles;
	}

	public String getMiner() {
		return miner;
	}

	public void setMiner(String miner) {
		this.miner = miner;
	}

	public String getDiffculty() {
		return diffculty;
	}

	public void setDiffculty(String diffculty) {
		this.diffculty = diffculty;
	}

	public String getTotalDiffculty() {
		return totalDiffculty;
	}

	public void setTotalDiffculty(String totalDiffculty) {
		this.totalDiffculty = totalDiffculty;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public double getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(Long gasUsed) {
		this.gasUsed = gasUsed;
	}

	public double getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(Long gasLimit) {
		this.gasLimit = gasLimit;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public double getBlockReward() {
		return blockReward;
	}

	public void setBlockReward(double blockReward) {
		this.blockReward = blockReward;
	}

	public double getUnclesReward() {
		return unclesReward;
	}

	public void setUnclesReward(double unclesReward) {
		this.unclesReward = unclesReward;
	}

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
}
