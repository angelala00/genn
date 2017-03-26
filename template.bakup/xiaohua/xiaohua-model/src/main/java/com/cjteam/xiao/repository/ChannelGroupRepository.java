package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.ChannelGroup;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by ChenLong on 2014/7/4.
 */
public interface ChannelGroupRepository extends PagingAndSortingRepository<ChannelGroup,Integer>{
    List<ChannelGroup> findByAppIdAndValid(String appId, byte valid);
}
