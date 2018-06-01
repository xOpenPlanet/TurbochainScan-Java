package com.osp.ethscan.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
/**
 * 返回的数据
 * @author zhangmingcheng
 */
public class ResponseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ResponseObject() {
		super();
	}

	public static ResponseObject getInstance() {
		return new ResponseObject();
	}

	/**
	 * 返回状态
	 */
	private int status;
	
	private String message;

	private Map<String, Object> data = new HashMap<String, Object>();

	public void setValue(String key, Object value) {
		data.put(key, value);
	}

	public Object getValue(String key, Object value) {
		return data.get(key);
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
