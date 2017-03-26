package com.cjteam.xiao.repository;

import java.util.Map;

import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.model.Exchange;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by ChenLong on 14-3-17.
 */
public interface DailyRegionIntegralLimitRepository extends PagingAndSortingRepository<DailyRegionIntegralLimit, Integer> {
    DailyRegionIntegralLimit findByAppIdAndChannelAndRegionNameAndCityName(String appId, String region, String city, String channel);   
    DailyRegionIntegralLimit findById(Integer id);
}
