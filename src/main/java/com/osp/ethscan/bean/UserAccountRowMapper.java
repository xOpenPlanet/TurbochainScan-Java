package com.osp.ethscan.bean;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
/**
 * 
 * @author zhangmingcheng
 */
public class UserAccountRowMapper implements RowMapper<UserAccountBean>{
	@Override
	public UserAccountBean mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAccountBean userBean = new UserAccountBean();
		userBean.setAddress(rs.getString("address"));
		return userBean;
	}
}