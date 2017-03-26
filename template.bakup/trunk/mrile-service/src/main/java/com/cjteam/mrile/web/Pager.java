package com.cjteam.mrile.web;

public class Pager {

	private int page = 0;
	private int size = 7;
	
	public Pager() {
		super();
	}
	/**
	 * 页码？第一页传0？
	 * @param page
	 */
	public Pager(int page) {
		super();
		this.page = page;
	}
	public Pager(int page, int size) {
		super();
		this.page = page;
		this.size = size;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		return "Pager [page=" + page + ", size=" + size + "]";
	}
	
}
