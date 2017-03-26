package com.cjteam.xiao.web.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ChenLong Date: 13-10-8
 */
public class ChannelResponse extends ResponseVo {
    private List<ChannelVo> channels;

    public List<ChannelVo> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelVo> channels) {
        this.channels = channels;
    }

    public int getCount() {
        return CollectionUtils.isEmpty(channels) ? 0 : channels.size();
    }
}
