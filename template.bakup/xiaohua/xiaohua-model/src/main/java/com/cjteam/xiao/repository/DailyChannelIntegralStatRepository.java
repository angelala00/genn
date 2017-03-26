package com.cjteam.xiao.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cjteam.xiao.model.DailyChannelIntegralStat;

import java.util.Date;

@Repository
public interface DailyChannelIntegralStatRepository extends PagingAndSortingRepository<DailyChannelIntegralStat, Long> {
    @Override
    <S extends DailyChannelIntegralStat> S save(S entity);

    @Query(nativeQuery = true, value = "select  *  from daily_channel_integral_stat dcis where " +
            " dcis.app_id = :appId and dcis.user_id = :userId and dcis.channel = :channelCode and dcis.date = DATE(:date)")
    DailyChannelIntegralStat getOneByAppIdAndUserIdAndChannel(@Param("appId") String appId,@Param("userId") String userId, @Param("channelCode") String channelCode, @Param("date") Date date);
}
