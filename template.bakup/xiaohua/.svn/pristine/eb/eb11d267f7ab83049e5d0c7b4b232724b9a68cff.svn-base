package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Channel;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * Created by ChenLong Date: 13-9-26
 */
public interface ChannelService {

    List<Channel> getValidChannels(String appId);

    Channel findOneByCode(String appId, String channelCode);

    Channel get(Integer id);

    void closeChannel(Integer id);

    void openChannel(Integer id);
    void close2Channel(Integer id);

    void open2Channel(Integer id);

    Page<Channel> getAllChannels(String appId);

    Channel save(Channel channel);


    List<Channel> getAllUsedChannels(String appId);
}
