package com.osp.ethscan.bean;

import java.io.Serializable;

import org.web3j.protocol.core.methods.response.EthBlock.Block;

/**
 * blocks表
 * 
 * @author zhangmingcheng
 */
public class BlockBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String number;
	private String hash;
	private String parent_hash;
	private String nonce;
	private String sha3_uncles;
	private String logs_bloom;
	private String transactions_root;
	private String state_root;
	private String receipts_root;
	private String author;
	private String miner;
	private String mix_hash;
	private String difficulty;
	private String total_difficulty;
	private String extra_data;
	private String size;
	private String gas_limit;
	private String gas_used;
	private String timestamp;
	private Integer transactions_size;
	private Integer uncles_size;
	private Integer sealfields_size;

	public void organizeBlockData(Block block, BlockBean blockBean) {
		if (block.getAuthor() != null) {
			blockBean.setAuthor(block.getAuthor());
		}
		if (block.getDifficulty() != null) {
			blockBean.setDifficulty(block.getDifficulty().toString());
		}
		if (block.getExtraData() != null) {
			blockBean.setExtra_data(block.getExtraData());
		}
		if (block.getGasLimit() != null) {
			blockBean.setGas_limit(block.getGasLimit().toString());
		}
		if (block.getGasUsed() != null) {
			blockBean.setGas_used(block.getGasUsed().toString());
		}
		if (block.getHash() != null) {
			blockBean.setHash(block.getHash());
		}
		if (block.getLogsBloom() != null) {
			blockBean.setLogs_bloom(block.getLogsBloom());
		}
		if (block.getMiner() != null) {
			blockBean.setMiner(block.getMiner());
		}
		if (block.getMixHash() != null) {
			blockBean.setMix_hash(block.getMixHash());
		}
		if (block.getNonce() != null) {
			blockBean.setNonce(block.getNonce().toString());
		}
		blockBean.setNumber(block.getNumber().toString());
		if (block.getParentHash() != null) {
			blockBean.setParent_hash(block.getParentHash());
		}
		if (block.getReceiptsRoot() != null) {
			blockBean.setReceipts_root(block.getReceiptsRoot());
		}
		if (block.getSealFields() != null) {
			blockBean.setSealfields_size(block.getSealFields().size());
		}
		if (block.getSha3Uncles() != null) {
			blockBean.setSha3_uncles(block.getSha3Uncles());
		}
		if (block.getSize() != null) {
			blockBean.setSize(block.getSize().toString());
		}
		if (block.getStateRoot() != null) {
			blockBean.setState_root(block.getStateRoot());
		}
		if (block.getTimestamp() != null) {
			blockBean.setTimestamp(block.getTimestamp().toString());
		}
		if (block.getTotalDifficulty() != null) {
			blockBean.setTotal_difficulty(block.getTotalDifficulty().toString());
		}
		if (block.getTransactionsRoot() != null) {
			blockBean.setTransactions_root(block.getTransactionsRoot());
		}
		if (block.getTransactions() != null) {
			blockBean.setTransactions_size(block.getTransactions().size());
		}
		if (block.getUncles() != null) {
			blockBean.setUncles_size(block.getUncles().size());
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getParent_hash() {
		return parent_hash;
	}

	public void setParent_hash(String parent_hash) {
		this.parent_hash = parent_hash;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getSha3_uncles() {
		return sha3_uncles;
	}

	public void setSha3_uncles(String sha3_uncles) {
		this.sha3_uncles = sha3_uncles;
	}

	public String getLogs_bloom() {
		return logs_bloom;
	}

	public void setLogs_bloom(String logs_bloom) {
		this.logs_bloom = logs_bloom;
	}

	public String getTransactions_root() {
		return transactions_root;
	}

	public void setTransactions_root(String transactions_root) {
		this.transactions_root = transactions_root;
	}

	public String getState_root() {
		return state_root;
	}

	public void setState_root(String state_root) {
		this.state_root = state_root;
	}

	public String getReceipts_root() {
		return receipts_root;
	}

	public void setReceipts_root(String receipts_root) {
		this.receipts_root = receipts_root;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getMiner() {
		return miner;
	}

	public void setMiner(String miner) {
		this.miner = miner;
	}

	public String getMix_hash() {
		return mix_hash;
	}

	public void setMix_hash(String mix_hash) {
		this.mix_hash = mix_hash;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public String getTotal_difficulty() {
		return total_difficulty;
	}

	public void setTotal_difficulty(String total_difficulty) {
		this.total_difficulty = total_difficulty;
	}

	public String getExtra_data() {
		return extra_data;
	}

	public void setExtra_data(String extra_data) {
		this.extra_data = extra_data;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getGas_limit() {
		return gas_limit;
	}

	public void setGas_limit(String gas_limit) {
		this.gas_limit = gas_limit;
	}

	public String getGas_used() {
		return gas_used;
	}

	public void setGas_used(String gas_used) {
		this.gas_used = gas_used;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getTransactions_size() {
		return transactions_size;
	}

	public void setTransactions_size(Integer transactions_size) {
		this.transactions_size = transactions_size;
	}

	public Integer getUncles_size() {
		return uncles_size;
	}

	public void setUncles_size(Integer uncles_size) {
		this.uncles_size = uncles_size;
	}

	public Integer getSealfields_size() {
		return sealfields_size;
	}

	public void setSealfields_size(Integer sealfields_size) {
		this.sealfields_size = sealfields_size;
	}
}
