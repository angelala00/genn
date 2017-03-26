package com.cjteam.mrile.api.controller.vo;


/**
 * @author JiangChi
 * TODO 需要重构
 *
 */
public class BaseResponseVo {
	private boolean success;
	private String message = "";
	private int count = 0;
	private Object content;

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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}
	
}
