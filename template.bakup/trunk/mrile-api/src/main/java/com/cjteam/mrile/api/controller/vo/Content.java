package com.cjteam.mrile.api.controller.vo;

import java.util.List;
import java.util.Map;

/**
 * @author JiangChi
 * TODO 需要重构
 */
public class Content {
	private int number;
	private int totalPages;
	private List<Map<String,Object>> content;
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<Map<String,Object>> getContent() {
		return content;
	}
	public void setContent(List<Map<String,Object>> content) {
		this.content = content;
	}
}
