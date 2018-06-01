package com.osp.ethscan.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.osp.ethscan.bean.ContractAccountBean;
import com.osp.ethscan.dao.ContractDao;

/**
 * 智能合约数据库操作
 * 
 * @author zhangmingcheng
 */
@Repository
public class ContractDaoImpl implements ContractDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public ContractAccountBean getContractByHash(String hash) {
		List<ContractAccountBean> contractAccountList = jdbcTemplate.query(
				"select * from contracts where transaction_hash = '" + hash + "';",
				new BeanPropertyRowMapper<ContractAccountBean>(ContractAccountBean.class));
		if (null != contractAccountList && contractAccountList.size() > 0) {
			return contractAccountList.get(0);
		}
		return null;
	}

}
