package com.osp.ethscan.util;
/**
 * 分页工具类
 * @author zhangmingcheng
 */
public class Page {
	private long allRow;// 总记录数
	private int currentPage = 1;// 当前页
	private int pageSize = 15;// 每页记录数
	private long totalPage;// 总页数
	
	public Page(){
    	super();
    }
	
	public Page(int page,int pagesize){
		this.currentPage = page;
		this.pageSize = pagesize;
	}
	/*
	 * 计算总页数
	 */
	public long countTotalPage(final long allRow) {
		long totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
		return totalPage;
	}

	/*
	 * 计算当前页开始的记录
	 */
	public long countOffset() {
		long offset = (long)pageSize * (long)(this.currentPage - 1);
		return offset;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}	

	public long getAllRow() {
		return allRow;
	}

	public void setAllRow(long allRow) {
		this.allRow = allRow;
		this.setTotalPage(this.countTotalPage(allRow));
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
