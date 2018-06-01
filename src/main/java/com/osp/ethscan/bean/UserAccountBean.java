package com.osp.ethscan.bean;

import java.io.Serializable;

/**
 * 用户表
 * 
 * @author zhangmingcheng
 */
public class UserAccountBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 账户在区块链上的地址
	private String address;
	
	public UserAccountBean() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
