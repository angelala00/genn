package com.cjteam.mrile.api.controller.vo;

import java.text.SimpleDateFormat;

import com.cjteam.mrile.model.Integral;

/**
 * TODO 需要重构
 *
 */
public class IntegralVo {
	private Integer id = 0;
	private String type = "";
	private String channelName = "";
	private Integer increase = 0;
	private String createTime = "";
	private Boolean status = Boolean.FALSE;

	public IntegralVo(Integral integral) {
		super();
		this.id = integral.getId();
		this.channelName = integral.getChannel();
		this.increase = integral.getScore();
		this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(integral.getCreateTime());
		this.status = integral.getStatus();
	}

	public IntegralVo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIncrease() {
		return increase;
	}

	public void setIncrease(Integer increase) {
		this.increase = increase;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
