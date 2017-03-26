package com.cjteam.xiao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cjteam.xiao.model.Channel;

import java.util.List;

@Repository
public interface ChannelRepository extends PagingAndSortingRepository<Channel, Integer> {

    @Query("from Channel c where c.appId = :appId")
    Page<Channel> getChannelsByApp(@Param("appId") String appId,Pageable pageable);
    Channel findByCode(String code);

    @Query("from Channel c where c.code = :channelCode and c.appId = :appId")
    Channel findOneByAppIdAndCode(@Param("appId") String appId, @Param("channelCode") String channelCode);

    @Modifying
    @Query(nativeQuery = true, value = "update channel set is_valid=:status where id=:id")
    void updateChannelStatus(@Param("id") Integer id, @Param("status") int status);

    @Modifying
    @Query(nativeQuery = true, value = "update channel set is_valid_=:status where id=:id")
    void updateChannelStatus2(@Param("id") Integer id, @Param("status") int status);

    List<Channel> findByAppIdAndValidTrue(String appId, Pageable pageable);

    List<Channel> findByAppIdAndUsed(String appId, int usedStatus);

    List<Channel> findByAppIdAndGroup_CodeAndValidTrue(String appId, String code);
}
