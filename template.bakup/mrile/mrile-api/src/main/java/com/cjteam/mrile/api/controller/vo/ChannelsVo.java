package com.cjteam.mrile.api.controller.vo;

import org.apache.commons.lang3.StringUtils;

import com.cjteam.mrile.model.Channel;

/**
 * @author JiangChi TODO 需要重构
 *
 */
public class ChannelsVo {
	private int id;
	private String code;
	private String name;
	private String description;
	private int order;
	private String type;
	private int typeint;
	private String advertisementUrl;
	private String pic;
	private String packageName;
	private String keys;

	public ChannelsVo() {

	}

	public ChannelsVo(Channel channel) {
		super();
		this.id = channel.getId();
		// if (null != channel.getGroup()) {
		// setCode(channel.getGroup().getCode());
		// } else {
		// setCode(channel.getCode());
		// }
		setCode(channel.getCode());
		this.name = channel.getName();
		this.description = channel.getDescription();
		this.order = channel.getOrder();

		if (StringUtils.isNotBlank(channel.getAdvertisementUrl())) {
			this.advertisementUrl = channel.getAdvertisementUrl();
		} else {
			this.advertisementUrl = "";
		}
		if (StringUtils.isNotBlank(channel.getType())) {
			setType(channel.getType());
			setTypeint(channel.getCodeInt());
		}
		if (StringUtils.isNotBlank(channel.getPackageName())) {
			setPackageName(channel.getPackageName());
		} else {
			setPackageName("");
		}
		if (StringUtils.isNotBlank(channel.getPic())) {
			this.pic = channel.getPic();
		}
		if (StringUtils.isNotBlank(channel.getKeys())) {
			setKeys(channel.getKeys());
		} else {
			setKeys("");
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getTypeint() {
		return typeint;
	}

	public void setTypeint(int typeint) {
		this.typeint = typeint;
	}

	public String getAdvertisementUrl() {
		return advertisementUrl;
	}

	public void setAdvertisementUrl(String advertisementUrl) {
		this.advertisementUrl = advertisementUrl;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

}
