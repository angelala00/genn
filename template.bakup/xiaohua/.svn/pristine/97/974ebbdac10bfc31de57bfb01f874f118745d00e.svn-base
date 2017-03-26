package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.Channel;
import com.cjteam.xiao.model.ChannelGroup;
import com.cjteam.xiao.repository.ChannelGroupRepository;
import com.cjteam.xiao.repository.ChannelRepository;
import com.cjteam.xiao.service.ChannelService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChenLong Date: 13-9-27
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {
    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImpl.class);

    @Override
    public List<Channel> getValidChannels(String appId) {
        List<ChannelGroup> groups = channelGroupRepository.findByAppIdAndValid(appId, (byte) 1);
        if (CollectionUtils.isEmpty(groups)) {
            return channelRepository.findByAppIdAndValidTrue(appId,new PageRequest(0,Integer.MAX_VALUE, Sort.Direction.DESC,"order"));
        }
        List<Channel> result = new ArrayList<Channel>(groups.size());
        List<Channel> tmp = null;
        for (ChannelGroup group : groups) {
            tmp = channelRepository.findByAppIdAndGroup_CodeAndValidTrue(appId, group.getCode());
            if (CollectionUtils.isNotEmpty(tmp)) {
                Collections.shuffle(tmp);
                result.add(tmp.get(0));
            }
        }
        return result;
    }


  

    @Override

    public Page<Channel> getAllChannels(String appId) {
        return channelRepository.getChannelsByApp(appId, new PageRequest(0, 1000, Sort.Direction.ASC, "order"));
    }

    @Override
    public Channel save(Channel channel) {
        if (null == channel)
            return null;
        Channel existChannel = null;
        if (channel.getId() != null) {
            existChannel = channelRepository.findOne(channel.getId());
            if (null != channel) {
                existChannel.setName(channel.getName());
                existChannel.setCode(channel.getCode());
                existChannel.setDescription(channel.getDescription());
                existChannel.setMaxScoreTimes(channel.getMaxScoreTimes());
                existChannel.setMaxScoreDaily(channel.getMaxScoreDaily());
                existChannel.setTwiceInternal(channel.getTwiceInternal());
                existChannel.setOrder(channel.getOrder());
                existChannel.setType(channel.getType());
                existChannel.setValid(channel.getValid());
                existChannel.setDailyTimes(channel.getDailyTimes());
                channel = existChannel;
            }
        }
        return channelRepository.save(channel);
    }

    @Override
    public List<Channel> getAllUsedChannels(String appId) {
        return channelRepository.findByAppIdAndUsed(appId, 1);
    }

    @Override
    public Channel get(Integer id) {
        return channelRepository.findOne(id);
    }

    @Override
    public void closeChannel(Integer id) {
        channelRepository.updateChannelStatus(id, 0);
    }

    @Override
    public void openChannel(Integer id) {
        channelRepository.updateChannelStatus(id, 1);
    }

    @Override
    public void close2Channel(Integer id) {
        channelRepository.updateChannelStatus2(id, 0);
    }

    @Override
    public void open2Channel(Integer id) {
        channelRepository.updateChannelStatus2(id, 1);
    }

    @Override
    public Channel findOneByCode(String appId, String channelCode) {
        return channelRepository.findOneByAppIdAndCode(appId, channelCode);
    }

    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ChannelGroupRepository channelGroupRepository;
}