package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.SysConf;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ChenLong
 * Date: 13-12-16
 */
public interface SysConfRepository extends PagingAndSortingRepository<SysConf, Integer> {
    SysConf findByAppIdAndCode(String appId,String code);
}
