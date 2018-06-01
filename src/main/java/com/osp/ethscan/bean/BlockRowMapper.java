package com.osp.ethscan.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BlockRowMapper implements RowMapper<BlockBean> {

	@Override
	public BlockBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		BlockBean blockBean = new BlockBean();
		blockBean.setAuthor(rs.getString("author"));
		blockBean.setDifficulty(rs.getString("difficulty"));
		blockBean.setExtra_data(rs.getString("extra_data"));
		blockBean.setGas_limit(rs.getString("gas_limit"));
		blockBean.setGas_used(rs.getString("gas_used"));
		blockBean.setHash(rs.getString("hash"));
		blockBean.setLogs_bloom(rs.getString("logs_bloom"));
		blockBean.setMiner(rs.getString("miner"));
		blockBean.setMix_hash(rs.getString("mix_hash"));
		blockBean.setNonce(rs.getString("nonce"));
		blockBean.setNumber(rs.getString("number"));
		blockBean.setParent_hash(rs.getString("parent_hash"));
		blockBean.setReceipts_root(rs.getString("receipts_root"));
		blockBean.setSealfields_size(rs.getInt("sealfields_size"));
		blockBean.setSha3_uncles(rs.getString("sha3_uncles"));
		blockBean.setSize(rs.getString("size"));
		blockBean.setState_root(rs.getString("state_root"));
		blockBean.setTimestamp(rs.getString("timestamp"));
		blockBean.setTotal_difficulty(rs.getString("total_difficulty"));
		blockBean.setTransactions_root(rs.getString("transactions_root"));
		blockBean.setTransactions_size(rs.getInt("transactions_size"));
		blockBean.setUncles_size(rs.getInt("uncles_size"));
		return blockBean;
	}

}
