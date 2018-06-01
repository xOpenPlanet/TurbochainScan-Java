package com.osp.ethscan.bean;

import java.io.Serializable;

/**
 * 合约表
 * 
 * @author zhangmingcheng
 */
public class ContractAccountBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String address;
	private String codes;
	private String creater;
	private String block_hash;
	private String block_number;
	private String transaction_hash;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCodes() {
		return codes;
	}

	public void setCodes(String codes) {
		this.codes = codes;
	}

	public String getCreater() {
		return creater;
	}

	public void setCreater(String creater) {
		this.creater = creater;
	}

	public String getBlock_hash() {
		return block_hash;
	}

	public void setBlock_hash(String block_hash) {
		this.block_hash = block_hash;
	}

	public String getBlock_number() {
		return block_number;
	}

	public void setBlock_number(String block_number) {
		this.block_number = block_number;
	}

	public String getTransaction_hash() {
		return transaction_hash;
	}

	public void setTransaction_hash(String transaction_hash) {
		this.transaction_hash = transaction_hash;
	}

}
