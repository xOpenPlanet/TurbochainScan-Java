package com.osp.ethscan.bean;

import java.io.Serializable;

import org.web3j.protocol.core.methods.response.Transaction;

/**
 * 交易表
 * 
 * @author zhangmingcheng
 */
public class TransactionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hash;
	private String nonce;
	private String block_hash;
	private String block_number;
	private String transaction_index;
	private String sender;
	private String receiver;
	private String value;
	private String gas_price;
	private String gas;
	private String input;
	private String creates;
	private String public_key;
	private String raw;
	private String r;
	private String s;
	private Integer v;
	//下面字段不是数据库对应字段，展示使用
	private String timestamp;
	private String contractAddress;
	private int type;
	private String status;
	
	public void organizeTransactionData(Transaction transaction, TransactionBean transactionBean) {
		transactionBean.setBlock_hash(transaction.getBlockHash());
		transactionBean.setBlock_number(transaction.getBlockNumber().toString());
		if (transaction.getCreates() != null) {
			transactionBean.setCreates(transaction.getCreates());
		}
		if (transaction.getGas() != null) {
			transactionBean.setGas(transaction.getGas().toString());
		}
		if (transaction.getGasPrice() != null) {
			transactionBean.setGas_price(transaction.getGasPrice().toString());
		}
		transactionBean.setHash(transaction.getHash());
		if (transaction.getInput() != null) {
			transactionBean.setInput(transaction.getInput());
		}
		if (transaction.getNonce() != null) {
			transactionBean.setNonce(transaction.getNonce().toString());
		}
		if (transaction.getPublicKey() != null) {
			transactionBean.setPublic_key(transaction.getPublicKey());
		}
		if (transaction.getR() != null) {
			transactionBean.setR(transaction.getR());
		}
		if (transaction.getRaw() != null) {
			transactionBean.setRaw(transaction.getRaw());
		}
		if (transaction.getTo() != null) {
			transactionBean.setReceiver(transaction.getTo());
		}
		if (transaction.getS() != null) {
			transactionBean.setS(transaction.getS());
		}
		if (transaction.getFrom() != null) {
			transactionBean.setSender(transaction.getFrom());
		}
		if (transaction.getTransactionIndex() != null) {
			transactionBean.setTransaction_index(transaction.getTransactionIndex().toString());
		}
		transactionBean.setV(transaction.getV());
		if (transaction.getValue() != null) {
			transactionBean.setValue(transaction.getValue().toString());
		}

	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
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

	public String getTransaction_index() {
		return transaction_index;
	}

	public void setTransaction_index(String transaction_index) {
		this.transaction_index = transaction_index;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getGas_price() {
		return gas_price;
	}

	public void setGas_price(String gas_price) {
		this.gas_price = gas_price;
	}

	public String getGas() {
		return gas;
	}

	public void setGas(String gas) {
		this.gas = gas;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getCreates() {
		return creates;
	}

	public void setCreates(String creates) {
		this.creates = creates;
	}

	public String getPublic_key() {
		return public_key;
	}

	public void setPublic_key(String public_key) {
		this.public_key = public_key;
	}

	public String getRaw() {
		return raw;
	}

	public void setRaw(String raw) {
		this.raw = raw;
	}

	public String getR() {
		return r;
	}

	public void setR(String r) {
		this.r = r;
	}

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public Integer getV() {
		return v;
	}

	public void setV(Integer v) {
		this.v = v;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
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
