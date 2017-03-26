package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.Advertisement;

/**
 * Created by ChenLong
 * Date: 13-11-4
 */
public class AdvertisementVo {
    private String name="";
    private String content="";
    private String pic="";
    private Integer score=0;
    private String channelType="";
    private String channelName="";
    private String description="";

    public AdvertisementVo() {
    }

    public AdvertisementVo(Advertisement advertisement) {
        setName(advertisement.getName());
        setContent(advertisement.getContent());
        setPic(advertisement.getPic());
        setScore(advertisement.getScore());
        if (advertisement.getChannel() != null) {
            setChannelName(advertisement.getChannel().getName());
            setChannelType(advertisement.getChannel().getCode());
        }
        setDescription(advertisement.getDescription());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
