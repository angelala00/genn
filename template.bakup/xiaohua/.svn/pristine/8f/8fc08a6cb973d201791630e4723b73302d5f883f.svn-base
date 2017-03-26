package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByUserId   (String userId) ;
    User findByUniqueUserId(String userId);
    @Query("from User u where u.appId = :appId order by u.createTime desc")
    Page<User> findByAppId(@Param("appId") String appId, Pageable pageRequest);

    User findByAppIdAndUserId(String appId, String userId);

    @Query("from User u  where u.appId = :appId  order by u.surplus desc")
    Page<User> queryTopSortBySurplusScore(@Param("appId") String appId,Pageable pageable);

    @Query("from User u where u.appId = :appId and u.mac = :userMac")
    User findOneByMac(@Param("appId") String appId,@Param("userMac") String userMac);

    @Modifying
    @Query("update User u set u.surplus = u.surplus + :score where u.id=:id and u.isBlack=0 and ( u.surplus + :score >=0)")
    int rollbackExchangeConsumptionScore(@Param("id") Integer id, @Param("score") Long score);

    @Query("select count(u.id) from User u where u.appId = :appId and (u.mac=:mac or u.token=:token or u.openUdid=:openUdid)")
    Long countUserBySimilarInfo(@Param("appId") String appId, @Param("mac") String mac, @Param("token") String token, @Param("openUdid") String openUdid);

    @Query("from User u where u.appId = :appId and (u.mac=:mac or u.token=:token or u.openUdid=:openUdid)")
    List<User> queryUserBySimilarInfo(@Param("appId") String appId,@Param("mac") String mac, @Param("token") String token, @Param("openUdid") String openUdid);

    @Query("from User u where u.appId = :appId and (u.mac=:mac  or u.openUdid=:openUdid)")
    List<User> queryUserBySimilarInfo(@Param("appId") String appId,@Param("mac") String mac, @Param("openUdid") String openUdid);


    @Query("from User u where  u.appId = :appId and (u.nickName like :name or u.mobilePhone like :mobile)")
    Page<User> findByNickNameLikeOrMobilePhoneLike(@Param("appId") String appId, @Param("name") String name, @Param("mobile") String mobile, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update user u set u.is_black=1 where u.id=:id")
    void blackUser(@Param(value = "id") Integer id);

    Page<User> findByAppIdAndIsBlackTrue(String appId, Pageable pageable);

    Page<User> findByAppIdAndIsBlackTrueAndIp(String appId, String ip, Pageable pageable);

    Page<User> findByAppIdAndIsBlackTrueAndMac(String appId, String mac, Pageable pageable);

    Page<User> findByAppIdAndIsBlackTrueAndMobilePhone(String appId, String mobile, Pageable pageable);

    Page<User> findByAppIdAndIsBlackTrueAndNickName(String appId, String nickName, Pageable pageable);

    Page<User> findByAppIdAndIsBlackTrueAndOpenUdid(String appId, String openUdid, Pageable pageable);

    Page<User> findByAppIdAndIsBlackTrueAndToken(String appId, String token, Pageable pageable);

    User findByAppIdAndOpenUdid(String appId, String openUdid);

    @Query(nativeQuery = true, value = "select  u.surplus from user u where u.app_id = :appId and u.id = :id")
    Long getSurplus(@Param("appId")  String appId,@Param("id") Integer userId);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.surplus = u.surplus - :newScore WHERE u.id=:id and (u.surplus - :newScore)  > 0")
    int consumeScore(@Param("id") Integer userId, @Param("newScore") int consumeScore);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.surplus = u.surplus - :newScore WHERE u.app_id = :appId and u.client_user_id = :userId and (u.surplus - :newScore)  > 0")
    int consumeScore(@Param("appId") String appId, @Param("userId") String userId, @Param("newScore") int consumeScore);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.create_time = INTERVAL :hours HOUR + INTERVAL :minutes MINUTE + INTERVAL :seconds SECOND +  u.create_time WHERE u.app_id = :appId and u.client_user_id=:userId")
    void updateUserCreateTime(@Param("appId")  String appId,@Param("userId") String userId, @Param("hours") Integer hours, @Param("minutes") Integer minutes, @Param("seconds") Integer seconds);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.total_points = u.total_points + :newScore,u.surplus = u.surplus + :newScore WHERE u.id=:id")
    void awardScore(@Param("id") Integer id, @Param("newScore") int newScore);

    @Query(nativeQuery = true, value = "select * from user u order by total_points desc limit :start,:limit")
    List<User> findSortedScoreUser(@Param("start") int start, @Param("limit") int limit);

    @Query("from User )")
    List<User> findAll();

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.product_eye_count = u.product_eye_count + :number WHERE u.app_id = :appId and u.client_user_id = :userId and (u.product_eye_count + :number) >= 0")
    int recordEye(@Param("appId") String appId, @Param("userId") String userId, @Param("number") Integer number);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.product_time_count = u.product_time_count + :number WHERE u.app_id = :appId and u.client_user_id = :userId and (u.product_time_count + :number)>=0")
    int recordTime(@Param("appId") String appId, @Param("userId") String userId,@Param("number") Integer number);

    @Modifying
    @Query(nativeQuery = true, value = "UPDATE user u SET u.level = u.level + :levelIncrease WHERE u.app_id = :appId and u.client_user_id = :userId")
    int increaseUserLevel(@Param("appId") String appId, @Param("userId") String userId, @Param("levelIncrease") Integer levelIncrease);

    @Modifying
    @Query(nativeQuery = true, value = "update user u set u.ip = :ip where u.id = :id")
    int updateLoginIp(@Param("id") Integer id, @Param("ip") String currentLoginIp);
}
