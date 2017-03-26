package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.ExchangeAccount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-5
 */
public interface ExchangeAccountRepository extends PagingAndSortingRepository<ExchangeAccount, Integer> {

    @Modifying
    @Query(nativeQuery = true, value = "update exchange_account ea set ea.times=0,ea.date = DATE(NOW())  where ea.date is null  or  ea.date<>DATE(NOW())")
    void refreshDrawsTimesStat();

    List<ExchangeAccount> findByTimesLessThanOrderByTimesAsc(Integer exchangeLimitTimes);

    @Modifying
    @Query(nativeQuery = true, value = "update exchange_account ea set ea.times = ea.times+1  where ea.date = date(now()) and ea.account= :alipayAccount")
    void appendExchangeTimes(@Param("alipayAccount") String aliPayAccount);
}
