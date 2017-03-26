package com.cjteam.xiao.stat.repository;

import com.cjteam.xiao.model.DailyChannelIntegralStat;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-16
 */
public interface ChannelDailyStatRepository extends PagingAndSortingRepository<DailyChannelIntegralStat, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT d.date " +
                    ", SUM(IF(d.channel = 'DUOMENG', d.score, 0)) AS 'DUOMENG' " +
                    ", SUM(IF(d.channel = 'AIPU', d.score, 0)) AS 'AIPU' " +
                    ", SUM(IF(d.channel = 'YIJIFEN', d.score, 0)) AS 'YIJIFEN' " +
                    ", SUM(IF(d.channel = 'YOUMI', d.score, 0)) AS 'YOUMI' " +
                    ", SUM(IF(d.channel = 'ADWO', d.score, 0)) AS 'ADWO' " +
                    ", SUM(IF(d.channel = 'LIMEI', d.score, 0)) AS 'LIMEI' " +
                    ", SUM(IF(d.channel = 'DIANRU', d.score, 0)) AS 'DIANRU' " +
                    ", SUM(IF(d.channel = 'STATIC', d.score, 0)) AS 'STATIC' " +
                    ", SUM(IF(d.channel = 'TOPJOY', d.score, 0)) AS 'TOPJOY' " +
                    "FROM daily_channel_integral_stat d where d.app_id = :appId and  d.date >= (INTERVAL -30 DAY + DATE(NOW())) " +
                    "GROUP BY d.date " +
                    "ORDER BY d.date ASC")
    public List<Object[]> queryDailyStatForChannel(@Param("appId") String appId);

    @Query(nativeQuery = true,
            value = "SELECT d.date , SUM(d.score) AS SCORE  " +
                    "FROM daily_channel_integral_stat d  where d.app_id = :appId and d.channel = :channelCode and  d.date >= (INTERVAL -30 DAY + DATE(NOW())) " +
                    "GROUP BY d.date " +
                    "ORDER BY d.date ASC")
    public List<Object[]> queryDailyStatForChannel(@Param("appId") String appId, @Param(value = "channelCode") String channelCode);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(DISTINCT(i.create_time)) AS onlineDays " +
                    "FROM integral i " +
                    "WHERE i.app_id = :appId and i.user_id =  :userId")
    BigInteger countUserIntegralDays(@Param("appId") String appId, @Param(value = "userId") String userId);

    @Query(nativeQuery = true,
            value = "SELECT SUM(i.score) AS totalyEarnedScore " +
                    "FROM integral i " +
                    "WHERE i.app_id = :appId and i.user_id =  :userId")
    BigDecimal sumTotalyEarnedScore(@Param("appId") String appId, @Param(value = "userId") String userId);

    @Query(nativeQuery = true,
            value = "SELECT SUM(e.money_exchange) AS totalyExchangedMoney  " +
                    "FROM exchange e " +
                    "WHERE e.app_id = :appId and e.user_id =  :userId")
    BigDecimal sumTotalyExchangedMoney(@Param("appId") String appId, @Param(value = "userId") String userId);

    @Query(nativeQuery = true,
            value = "SELECT MAX(ii.sumScore) " +
                    "FROM  " +
                    "( " +
                    "SELECT SUM(i.score) AS sumScore " +
                    "FROM integral i " +
                    "WHERE i.app_id = :appId and i.user_id= :userId " +
                    "GROUP BY DATE(i.create_time)) ii")
    BigDecimal highSocreEarnedOneDay(@Param("appId") String appId, @Param(value = "userId") String userId);

    @Query(nativeQuery = true,
            value = "SELECT MAX(ee.sumExchane) " +
                    "FROM  " +
                    "( " +
                    "SELECT SUM(e.money_exchange) AS sumExchane " +
                    "FROM exchange e " +
                    "WHERE e.app_id = :appId and e.user_id= :userId " +
                    "GROUP BY DATE(e.create_time)) ee")
    BigDecimal highExchangedMoneyOneDay(@Param("appId") String appId, @Param(value = "userId") String userId);

    @Query(nativeQuery = true, value = "SELECT p.`type`,sum(e.money_exchange) AS sumExchane " +
            "FROM exchange e,product p  " +
            "WHERE e.app_id = :appId and e.product_code=p.code and  e.user_id=:userId " +
            "GROUP BY p.`type`")
    List<Object[]> statExchangedMoneyOnProducts(@Param("appId") String appId, @Param(value = "userId") String uniqueUserId);

    @Query(nativeQuery = true, value = "SELECT i.channel,SUM(i.score) AS sumScore " +
            "FROM integral i " +
            "WHERE i.app_id = :appId and i.user_id=:userId  " +
            "GROUP BY i.channel")
    List<Object[]> statEarndSocreOnChannels(@Param("appId") String appId, @Param(value = "userId") String uniqueUserId);


    @Query(nativeQuery = true, value =
            "SELECT DATE( i.create_time) as date, " +
                    "SUM(IF( i.channel = 'DUOMENG',  i.score, 0)) AS 'DUOMENG'," +
                    " SUM(IF( i.channel = 'AIPU',  i.score, 0)) AS 'AIPU', " +
                    "SUM(IF( i.channel = 'YIJIFEN',  i.score, 0)) AS 'YIJIFEN', " +
                    "SUM(IF( i.channel = 'YOUMI',  i.score, 0)) AS 'YOUMI', " +
                    "SUM(IF( i.channel = 'ADWO',  i.score, 0)) AS 'ADWO', " +
                    "SUM(IF( i.channel = 'LIMEI',  i.score, 0)) AS 'LIMEI'," +
                    "SUM(IF( i.channel = 'DIANRU',  i.score, 0)) AS 'DIANRU'," +
                    "SUM(IF( i.channel = 'TOPJOY',  i.score, 0)) AS 'TOPJOY' " +
                    "FROM integral  i where i.app_id = :appId  and  i.create_time >= (INTERVAL -30 DAY + DATE(NOW()))   " +
                    "GROUP BY DATE(i.create_time) " +
                    "ORDER BY DATE( i.create_time) ASC")
    List<Object[]> integralDailyStat(@Param("appId") String appId);

    @Query(nativeQuery = true, value =
            "SELECT EXTRACT(YEAR_MONTH FROM  i.create_time) as date, " +
                    "SUM(IF( i.channel = 'DUOMENG',  i.score, 0)) AS 'DUOMENG'," +
                    " SUM(IF( i.channel = 'AIPU',  i.score, 0)) AS 'AIPU', " +
                    "SUM(IF( i.channel = 'YIJIFEN',  i.score, 0)) AS 'YIJIFEN', " +
                    "SUM(IF( i.channel = 'YOUMI',  i.score, 0)) AS 'YOUMI', " +
                    "SUM(IF( i.channel = 'ADWO',  i.score, 0)) AS 'ADWO', " +
                    "SUM(IF( i.channel = 'LIMEI',  i.score, 0)) AS 'LIMEI'," +
                    "SUM(IF( i.channel = 'DIANRU',  i.score, 0)) AS 'DIANRU'," +
                    "SUM(IF( i.channel = 'TOPJOY',  i.score, 0)) AS 'TOPJOY' " +
                    "FROM integral  i where i.app_id = :appId " +
                    "GROUP BY EXTRACT(YEAR_MONTH FROM  i.create_time) " +
                    "ORDER BY EXTRACT(YEAR_MONTH FROM  i.create_time) ASC")
    List<Object[]> integralMonthlyStat(@Param("appId") String appId);

    @Query(nativeQuery = true, value =
            "SELECT EXTRACT(YEAR_MONTH FROM  i.create_time) as date, " +
                    "SUM(i.score) AS 'SCORE' " +
                    "FROM integral  i   where  i.app_id = :appId and i.channel=:channelCode and i.status = 1 " +
                    "GROUP BY EXTRACT(YEAR_MONTH FROM  i.create_time) " +
                    "ORDER BY EXTRACT(YEAR_MONTH FROM  i.create_time) ASC")
    List<Object[]> integralMonthlyStat(@Param("appId") String appId, @Param(value = "channelCode") String channelCode);

    @Query(nativeQuery = true, value =
            "select i.channel,sum(i.score) from integral i where i.app_id = :appId  group by i.channel order by i.channel")
    List<Object[]> integralScoreCategoryStat(@Param("appId") String appId);

    @Query(nativeQuery = true, value = "SELECT s.date, SUM(s.score) " +
            "FROM daily_channel_integral_stat s where s.app_id = :appId and  s.date >= (INTERVAL -30 DAY + DATE(NOW())) " +
            "GROUP BY s.date " +
            "ORDER BY s.date")
    List<Object[]> sumChannelsReturnedScoreByDate(@Param("appId") String appId);

    @Query(nativeQuery = true, value = "SELECT DATE(e.processing_time), SUM(e.score_consumption) " +
            "FROM exchange e " +
            "WHERE e.app_id = :appId and e.processing_time IS NOT NULL and DATE(e.processing_time) >= (INTERVAL -30 DAY + DATE(NOW())) " +
            "GROUP BY DATE(e.processing_time) " +
            "ORDER BY DATE(e.processing_time)")
    List<Object[]> userCostsByDate(@Param("appId") String appId);

    @Query(nativeQuery = true, value = "SELECT DATE(w.check_time), SUM(w.amount_money) " +
            "FROM withdraw w " +
            "WHERE w.app_id = :appId and w.check_time IS NOT NULL and DATE(w.check_time) >= (INTERVAL -30 DAY + DATE(NOW()))  " +
            "GROUP BY DATE(w.check_time) " +
            "ORDER BY DATE(w.check_time)")
    List<Object[]> alipayDrawoutsByDate(@Param("appId") String appId);
}
