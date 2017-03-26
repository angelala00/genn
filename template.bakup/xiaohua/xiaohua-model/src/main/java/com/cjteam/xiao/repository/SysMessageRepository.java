package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.SysConf;
import com.cjteam.xiao.model.SysMessage;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ChenLong on 14-3-4.
 */
public interface SysMessageRepository extends PagingAndSortingRepository<SysMessage, Integer> {
    Iterable<SysMessage> findByAppId(String appId);
}
