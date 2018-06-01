package com.osp.ethscan.bean;

import java.io.Serializable;

/**
 * 维护存储区块的时间
 * 
 * @author zhangmingcheng
 */
public class EtherStatusBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String number;
	private String start_timestamp;
	private String end_timestamp;

	public EtherStatusBean() {
		super();
	}

	public EtherStatusBean(String number, String start_timestamp) {
		this.number = number;
		this.start_timestamp = start_timestamp;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStart_timestamp() {
		return start_timestamp;
	}

	public void setStart_timestamp(String start_timestamp) {
		this.start_timestamp = start_timestamp;
	}

	public String getEnd_timestamp() {
		return end_timestamp;
	}

	public void setEnd_timestamp(String end_timestamp) {
		this.end_timestamp = end_timestamp;
	}

}
