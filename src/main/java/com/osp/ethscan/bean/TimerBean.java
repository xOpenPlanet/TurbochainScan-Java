package com.osp.ethscan.bean;

import java.io.Serializable;

/**
 * timer表记录已维护区块高度
 * 
 * @author zhangmingcheng
 */
public class TimerBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String current_block_height;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCurrent_block_height() {
		return current_block_height;
	}

	public void setCurrent_block_height(String current_block_height) {
		this.current_block_height = current_block_height;
	}
}
