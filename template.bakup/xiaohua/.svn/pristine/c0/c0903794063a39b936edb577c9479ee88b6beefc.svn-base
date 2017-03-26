package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.IpUserLimit;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by ChenLong
 * Date: 13-12-16
 */
public interface IpUserLimitRepository extends PagingAndSortingRepository<IpUserLimit, Integer> {
    @Query(nativeQuery = true, value = "select  * from ip_user_limit i where  i.app_id = :appId and  i.ip=:ip and user_id=:userId and i.date=DATE(now())")
    IpUserLimit findByAppIdAndIpAndUser_userId(@Param("appId") String appId, @Param(value = "ip") String ip, @Param(value = "userId") String userId);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO ip_user_limit(app_id,ip,user_id,tag,date) " +
            "SELECT :appId,:ip,:userId, IFNULL(MAX(tag),-1)+1,DATE(now())" +
            "FROM ip_user_limit " +
            "WHERE app_id = :appId and ip=:ip  and date=DATE(now())" +
            "LIMIT 1")
    int insertNewUserAccessRecord(@Param("appId") String appId, @Param(value = "ip") String xRealIp, @Param(value = "userId") String userId);
}
