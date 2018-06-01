package com.osp.ethscan.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ContractAccountRowMapper implements RowMapper<ContractAccountBean> {
	@Override
	public ContractAccountBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		ContractAccountBean contractAccountBean = new ContractAccountBean();
		contractAccountBean.setAddress(rs.getString("address"));
		contractAccountBean.setCodes(rs.getString("codes"));
		return contractAccountBean;
	}
}
