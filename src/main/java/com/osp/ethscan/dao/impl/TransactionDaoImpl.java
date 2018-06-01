package com.osp.ethscan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.osp.ethscan.bean.AccountTransactionBean;
import com.osp.ethscan.bean.TransactionBean;
import com.osp.ethscan.dao.TransactionDao;
import com.osp.ethscan.util.BaseUtil;

/**
 * 交易数据库操作
 * 
 * @author zhangmingcheng
 */
@Repository
public class TransactionDaoImpl implements TransactionDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 取得当前地址当前页的交易信息
	 * 
	 * @param address
	 * @param startIndex
	 * @param endIndex
	 * @param isAddContract
	 * @return
	 */
	@Override
	public List<AccountTransactionBean> getTxListByAddress(String address, Long startIndex, Integer endIndex) {
		List<AccountTransactionBean> accounts_transactions = jdbcTemplate.query(
				"select * from accounts_transaction where sender = ? or receiver = ? order by txn_timestamp desc limit ?,?;",
				new Object[] { address, address, startIndex, endIndex }, new RowMapper<AccountTransactionBean>() {
					public AccountTransactionBean mapRow(ResultSet rs, int rowNum) throws SQLException {
						AccountTransactionBean accountTransactionBean = new AccountTransactionBean();
						accountTransactionBean.setSender(rs.getString("sender"));
						accountTransactionBean.setReceiver(rs.getString("receiver"));
						accountTransactionBean.setTxn_address(rs.getString("txn_address"));
						accountTransactionBean.setTxn_timestamp(rs.getLong("txn_timestamp"));
						accountTransactionBean.setTxn_type(rs.getInt("txn_type"));
						return accountTransactionBean;
					}
				});
		return accounts_transactions;
	}

	/**
	 * 取得当前区块的交易信息
	 * 
	 * @param BlockNumber
	 * @return
	 */
	@Override
	public List<TransactionBean> getTxListByBlockNumber(String BlockNumber) {
		List<TransactionBean> transactionList = jdbcTemplate.query(
				"select * from transactions where block_number = '" + BlockNumber + "';",
				new BeanPropertyRowMapper<TransactionBean>(TransactionBean.class));
		return transactionList;
	}

	/**
	 * 根据交易地址获取交易详细信息
	 */
	@Override
	public TransactionBean getTransactionByHash(String hash) {
		List<TransactionBean> transactionList = jdbcTemplate.query(
				"select * from transactions where hash = '" + hash + "';",
				new BeanPropertyRowMapper<TransactionBean>(TransactionBean.class));
		if (null != transactionList && transactionList.size() > 0) {
			return transactionList.get(0);
		}
		return null;
	}

	/**
	 * 根据交易地址获取交易的时间戳
	 */
	@Override
	public Long getTransactionTimeByAccountsTransactionTable(String txnHash) {
		Long txnTimestamp = jdbcTemplate.queryForObject(
				"select txn_timestamp from accounts_transaction where txn_address = '" + txnHash + "';", Long.class);
		return txnTimestamp;
	}

	/**
	 * 取得交易关系表数据
	 */
	@Override
	public List<AccountTransactionBean> getCurrentPageAccountsTransaction(Long startIndex, Integer endIndex) {
		List<AccountTransactionBean> accountTransactionList = jdbcTemplate.query(
				"select * from accounts_transaction order by txn_timestamp desc limit ?,?;",
				new Object[] { startIndex, endIndex },
				new BeanPropertyRowMapper<AccountTransactionBean>(AccountTransactionBean.class));
		return accountTransactionList;
	}

	/**
	 * 统计accounts_transaction表记录数
	 */
	@Override
	public Long countAccountsTransaction() {
		return jdbcTemplate.queryForObject("select count(1) from accounts_transaction;", Long.class);
	}

	/**
	 * 取得当前账户的交易数
	 */
	@Override
	public Long getTxCountByAddress(String address) {
		return jdbcTemplate.queryForObject(
				"select count(1) from accounts_transaction where sender = ? or receiver = ?;",
				new Object[] { address, address }, Long.class);
	}

	/**
	 * 返回当前日期的交易数
	 */
	@Override
	public Integer getTxnCountByDate(String date) {
		long startDate = BaseUtil.dateToStamp(date + " 00:00:00");
		long endDate = BaseUtil.dateToStamp(date + " 23:59:59");
		return jdbcTemplate.queryForObject(
				"select count(1) from accounts_transaction where txn_timestamp >= ? and txn_timestamp <= ?;",
				new Object[] { startDate, endDate }, Integer.class);
	}

}
