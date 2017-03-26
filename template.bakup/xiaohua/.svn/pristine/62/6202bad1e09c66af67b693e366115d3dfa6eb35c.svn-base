package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.ExchangeUser;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by ChenLong on 14-2-27.
 */
public interface ExchangeUserRepository extends PagingAndSortingRepository<ExchangeUser, Integer> {
    List<ExchangeUser> findByStatus(int status, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update exchange_user eu set eu.points = eu.points + :score where eu.app_id = :appId and eu.user_id = :userId")
    void increaseScore(@Param("appId") String appId,@Param("userId") String userId, @Param("score") int score);

    @Modifying
    @Query(nativeQuery = true, value = "update exchange_user eu set eu.status = :newStatus where eu.app_id = :appId and eu.user_id = :userId and eu.points >= :limit")
    int updateStatusIfReachTheLimit(@Param("appId") String appId,@Param("userId") String userId, @Param("limit") int limit, @Param("newStatus") int newStatus);

    List<ExchangeUser> findByStatus(int status);

    @Modifying
    @Query(nativeQuery = true, value = "update exchange_user eu set eu.lock_tag = :serverLabel where ( eu.lock_tag is null or eu.lock_tag = :serverLabel )and eu.status = 2 and eu.id = :id")
    int updateByLock(@Param("id") Integer id, @Param("serverLabel") String serverLabel);

    @Modifying
    @Query(nativeQuery = true, value = "update exchange_user eu set eu.lock_tag = null, eu.totally_exchanged_money =  eu.totally_exchanged_money + :totallyExchangedMoney  where  eu.lock_tag =  :serverLabel  and eu.status = 2 and eu.id = :id")
    void releaseLockAndTagForExchangeAgain(@Param("id") Integer id, @Param("serverLabel") String serverLabel, @Param("totallyExchangedMoney") Integer totallyExchangedMoney);

    List<ExchangeUser> findByStatusLessThanOrderByStatusDesc(int statusExchanged);
}
