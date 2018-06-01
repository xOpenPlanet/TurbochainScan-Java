package com.osp.ethscan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.osp.ethscan.dao.AccountDao;

/**
 * 账户数据库操作
 * 
 * @author zhangmingcheng
 */
@Repository
public class AccountDaoImpl implements AccountDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 获取当前页账户地址
	 */
	@Override
	public List<String> getCurrentPageAccounts(Long startIndex, Integer pagesize) {
		List<String> userAccountLists = jdbcTemplate.queryForList(
				"SELECT address FROM users_account UNION SELECT address FROM contracts limit ?,?;",
				new Object[] { startIndex, pagesize }, String.class);
		return userAccountLists;
	}

	/**
	 * 统计合约表与外部账户表的记录总数
	 */
	@Override
	public Long countAccountWithUserAndContracts() {
		Long count = jdbcTemplate.queryForObject("select count(1) from users_account;", Long.class);
		count += jdbcTemplate.queryForObject("select count(1) from contracts;", Long.class);
		return count;
	}

	/**
	 * 判断此账户是不是外部账户
	 */
	@Override
	public int isUserAccount(String address) {
		List<String> userAccountLists = jdbcTemplate
				.queryForList("SELECT address FROM users_account where address='" + address + "';", String.class);
		if (userAccountLists.size() > 0) {
			return 1;
		}
		return 0;
	}

	/**
	 * 取得所有账户地址（外部账户、合约账户）
	 */
	@Override
	public List<String> getAllAccounts() {
		List<String> userAccountLists = jdbcTemplate
				.queryForList("SELECT address FROM users_account UNION SELECT address FROM contracts;", String.class);
		return userAccountLists;
	}

	/**
	 * 地址是否存在
	 */
	@Override
	public Boolean existUser(String address) {
		List<String> userAccountLists = jdbcTemplate.queryForList(
				"SELECT address FROM users_account UNION SELECT address FROM contracts where address = ?;",
				new Object[] { address }, String.class);
		if (userAccountLists.size() > 0) {
			return true;
		}
		return false;
	}

}
