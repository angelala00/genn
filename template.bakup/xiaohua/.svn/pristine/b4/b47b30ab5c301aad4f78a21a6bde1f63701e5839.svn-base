package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.DailyRegionIntegralStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by ChenLong on 14-3-17.
 */
@Repository
public interface DailyRegionIntegralStatRepository extends PagingAndSortingRepository<DailyRegionIntegralStat, Long> {
    @Query(nativeQuery = true, value = "select  * from  daily_region_integral_stat d where  d.app_id = :appId and d.channel = :channel and d.region_name = :regionName and d.city_name = :cityName and d.date = DATE(:date)")
    DailyRegionIntegralStat findOneByAppIdAndChannelAndRegionNameAndCityNameAndDate(@Param("appId") String appId, @Param("channel") String channel, @Param("regionName") String regionName, @Param("cityName") String cityName, @Param("date") Date date);
}
