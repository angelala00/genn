package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Integral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IntegralRepository extends PagingAndSortingRepository<Integral, Integer> {

    @Query("from Integral i  where i.appId = :appId order by i.createTime desc")
    Iterable<Integral> findAll(@Param("appId") String appId);


    @Query("from Integral i where i.appId = :appId and i.user.userId = :userId and status=1 order by  i.createTime desc")
    Page<Integral> queryByAppIdAndUserId(@Param("appId") String appId, @Param("userId") String userId, Pageable pageable);

    @Query(nativeQuery = true, value = "select * from integral i where  i.app_id = :appId   and i.order_id = :orderId limit 0,1")
    Integral findOneByOrderId(@Param("appId") String appId, @Param("orderId") String orderId);

    @Query(nativeQuery = true, value = "select * from integral i where  i.app_id = :appId   and i.user_id = :userId and i.channel = :channelCode and i.status = 1 order by i.create_time desc limit  0,1")
    Integral findOneLastest(@Param("appId") String appId, @Param("userId") String userId, @Param("channelCode") String channelCode);

    Page<Integral> findByAppIdAndUser_UserId(String appId, String userId, Pageable pageRequest);

    Integer countByAppIdAndUser_UserIdAndChannel_CodeAndStatusTrue(String appId, String userId, String channelCode);

    int countByAppIdAndUser_UserIdAndIntegralType_Code(String appId, String userId, String integralType);

    @Query(nativeQuery = true, value = "select  * from integral i where i.app_id = :appId and i.create_time>=:createTime  and i.status=1 limit :limit")
    List<Integral> findByAppIdAndCreateTime(@Param("appId") String appId, @Param("createTime") Date createTime, @Param("limit") int limit);

    Page<Integral> findByAppId(String appId, Pageable pageable);

    @Query(nativeQuery = true, value = "select  * from integral i where i.app_id = :appId and i.user_id= :userId and i.channel = :code and i.ad_id = :adId limit 1")
    Integral findByUser_UserIdAndChannel_CodeAndAdId(@Param("appId") String appId, @Param("userId") String userId, @Param("code") String code, @Param("adId") String adId);// pageRequest limit the query just contain one row

    @Query(nativeQuery = true, value = "select  * from integral i where i.app_id = :appId and  i.ip= :ip and i.channel = :code and i.ad_id = :adId limit 1")
    Integral findByUser_IpAndChannel_CodeAndAdId(@Param("appId") String appId,@Param("ip") String userId, @Param("code") String code, @Param("adId") String adId);

    List<Integral> findByAppIdAndUdidAndAdIdAndStatusTrue(String appId, String deviceUdid, String adid);

    int countByUser_IdAndStatusTrue(Integer userId);
    @Query("select  count(*) from Integral  where user.userId =:userId  and channel.code=:channelCode and  createTime >=:upTime     ")
    int countByUserIdAndChannelCodeAndCreateTime(@Param("userId")   String userId , @Param("channelCode") String channelCode , @Param("upTime") Date upTime) ;
}
