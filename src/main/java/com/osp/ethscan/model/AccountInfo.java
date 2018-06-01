package com.osp.ethscan.model;

public class AccountInfo {
	private String address;
	private double balance;
	private long txCount;
	private int type;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public long getTxCount() {
		return txCount;
	}

	public void setTxCount(long txCount) {
		this.txCount = txCount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
