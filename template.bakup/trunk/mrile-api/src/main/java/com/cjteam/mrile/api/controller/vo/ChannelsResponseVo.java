package com.cjteam.mrile.api.controller.vo;

import java.util.List;


/**
 * @author JiangChi
 * TODO 需要重构
 *
 */
public class ChannelsResponseVo {
	private boolean success;
	private String message = "";
	private int count = 0;
	private List<ChannelsVo> channels;

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

	public List<ChannelsVo> getChannels() {
		return channels;
	}

	public void setChannels(List<ChannelsVo> channels) {
		this.channels = channels;
	}
	
}
