package com.osp.ethscan.model;

/**
 * 粗略展示区块信息bean
 * 
 * @author zhangmingcheng
 */
public class BlockBriefInfo {
	private String height;
	private String age;
	private int txn;
	private int uncles;
	private String miner;
	private long gasUsed;
	private long gasLimit;
	private double avgGasPrice;
	private double reward;

	public BlockBriefInfo() {
		super();
	}

	public BlockBriefInfo(String height, String age, int txn, int uncles, String miner, long gasUsed, long gasLimit,
			double avgGasPrice, double reward) {
		this.height = height;
		this.age = age;
		this.txn = txn;
		this.uncles = uncles;
		this.miner = miner;
		this.gasUsed = gasUsed;
		this.gasLimit = gasLimit;
		this.avgGasPrice = avgGasPrice;
		this.reward = reward;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public int getTxn() {
		return txn;
	}

	public void setTxn(int txn) {
		this.txn = txn;
	}

	public int getUncles() {
		return uncles;
	}

	public void setUncles(int uncles) {
		this.uncles = uncles;
	}

	public String getMiner() {
		return miner;
	}

	public void setMiner(String miner) {
		this.miner = miner;
	}

	public long getGasUsed() {
		return gasUsed;
	}

	public void setGasUsed(long gasUsed) {
		this.gasUsed = gasUsed;
	}

	public long getGasLimit() {
		return gasLimit;
	}

	public void setGasLimit(long gasLimit) {
		this.gasLimit = gasLimit;
	}

	public double getAvgGasPrice() {
		return avgGasPrice;
	}

	public void setAvgGasPrice(double avgGasPrice) {
		this.avgGasPrice = avgGasPrice;
	}

	public double getReward() {
		return reward;
	}

	public void setReward(double reward) {
		this.reward = reward;
	}

}
