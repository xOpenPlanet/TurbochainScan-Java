package com.osp.ethscan.bean;

/**
 * 图表
 * 
 * @author zhangmingcheng
 */
public class ChartBean {

	private long dt;// 时间戳
	private int y;// 交易数
	private String friendlydate;// 友情提示日期

	public ChartBean() {
		super();
	}

	public ChartBean(int y, long dt, String friendlydate) {
		this.y = y;
		this.dt = dt;
		this.friendlydate = friendlydate;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getFriendlydate() {
		return friendlydate;
	}

	public void setFriendlydate(String friendlydate) {
		this.friendlydate = friendlydate;
	}
}
