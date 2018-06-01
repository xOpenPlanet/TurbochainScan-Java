package com.osp.ethscan.bean;

import java.io.Serializable;

/**
 * 交易关系表
 * 
 * @author zhangmingcheng
 */
public class AccountTransactionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String txn_address;
	private String sender;
	private String receiver;
	private Long txn_timestamp;
	private Integer txn_type;

	public String getTxn_address() {
		return txn_address;
	}

	public void setTxn_address(String txn_address) {
		this.txn_address = txn_address;
	}

	public Long getTxn_timestamp() {
		return txn_timestamp;
	}

	public void setTxn_timestamp(Long txn_timestamp) {
		this.txn_timestamp = txn_timestamp;
	}

	public Integer getTxn_type() {
		return txn_type;
	}

	public void setTxn_type(Integer txn_type) {
		this.txn_type = txn_type;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
}
