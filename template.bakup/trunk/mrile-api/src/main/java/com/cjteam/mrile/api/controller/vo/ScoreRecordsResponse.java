package com.cjteam.mrile.api.controller.vo;

import java.util.List;

/**
 * TODO 需要重构
 *
 */
public class ScoreRecordsResponse {
	private boolean success = Boolean.FALSE;
	private String message = "";

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private List<IntegralVo> records;

	public List<IntegralVo> getRecords() {
		return records;
	}

	public void setRecords(List<IntegralVo> records) {
		this.records = records;
	}

	public int getCount() {
		return records == null || records.size() <= 0 ? 0 : records.size();
	}
}
