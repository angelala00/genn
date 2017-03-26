package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.Channel;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by ChenLong Date: 13-9-26
 */
public class ChannelVo {
    private Integer id = -1;
    private String code = "";
    private String name = "";
    private String description = "";
    private Integer order = 0;
    private String type = "";
    private int typeint;
    private String advertisementUrl = "";
    private String pic = "";
    private String packageName = "";
    private String keys = "";

    public ChannelVo(Channel channel) {
        super();
        this.id = channel.getId();
        if (null != channel.getGroup()) {
            setCode(channel.getGroup().getCode());
        } else {
            setCode(channel.getCode());
        }
        this.name = channel.getName();
        this.description = channel.getDescription();
        this.order = channel.getOrder();

        if (StringUtils.isNotBlank(channel.getAdvertisementUrl())) {
            this.advertisementUrl = channel.getAdvertisementUrl();
        }
        if (StringUtils.isNotBlank(channel.getType())) {
            setType(channel.getType());
            setTypeint(channel.getCodeInt());
        }
        if (StringUtils.isNotBlank(channel.getPackageName())) {
            setPackageName(channel.getPackageName());
        }
        if (StringUtils.isNotBlank(channel.getPic())) {
            this.pic = channel.getPic();
        }
        if (StringUtils.isNotBlank(channel.getKeys())) {
            setKeys(channel.getKeys());
        }
    }

    public ChannelVo() {
        super();
    }

    public ChannelVo(Integer id, String code, String name, String description, Integer order) {
        super();
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getAdvertisementUrl() {
        return advertisementUrl;
    }

    public void setAdvertisementUrl(String advertisementUrl) {
        this.advertisementUrl = advertisementUrl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public int getTypeint() {
        return typeint;
    }

    public void setTypeint(int typeint) {
        this.typeint = typeint;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }
}

