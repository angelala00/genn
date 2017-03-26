package com.cjteam.mrile.api.controller.vo;

/**
 * TODO 需要重构
 *
 */
public class ResponseExtensionVo<T> {
	private boolean success = Boolean.TRUE;
	private String message = "";
	private T content;

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

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
		return 0;
	}
}
