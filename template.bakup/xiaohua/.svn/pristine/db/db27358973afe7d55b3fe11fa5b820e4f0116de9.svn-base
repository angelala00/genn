package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Exchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ExchangeRepository extends PagingAndSortingRepository<Exchange, Integer> {

    @Query("from Exchange e where e.appId = :appId order by e.createTime desc")
    Iterable<Exchange> findAll(@Param("appId")String appId);

    Page<Exchange> findByAppIdAndUser_UserIdAndProduct_ProductType_CodeIn(String appId, String userId, String[] productTypeCodes, Pageable pageable);

    Page<Exchange> findByAppIdAndProduct_ProductType_CodeIn(String appId,String[] strings, Pageable pageable);

    Long countByUser_UserIdAndProduct_ProductType_CodeAndCreateTimeAfter(String userId, String exchangeTypes, Date time);

    Page<Exchange> findByAppIdAndProduct_ProductType_CodeInAndUser_UserId(String appId,String[] exchangeTypes, String userId, Pageable pageable);

    Integer countByAppIdAndConsumerAccountAndProduct_ProductType_CodeAndCreateTimeAfter(String appId,String consumerAccount, String productType, Date time);

    @Query(nativeQuery = true, value = "select  * from exchange e where e.app_id = :appId and  e.create_time>=:createTimeDuration limit :limit")
    List<Exchange> findAppIdAndByTime(@Param("appId") String appId, @Param("createTimeDuration") Date createTimeDuration, @Param("limit") int limit);
}
