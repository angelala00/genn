package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.CrapIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
@Repository
public interface CrapIssueRepository extends PagingAndSortingRepository<CrapIssue, Integer> {
    CrapIssue findByAppIdAndIssue(String appId,Integer issue);

    @Query(nativeQuery = true,
            value = "select  max(ci.issue) from crap_issue ci where ci.app_id = :appId")
    Integer getMaxIssue(@Param("appId") String appId);

    @Query(nativeQuery = true, value = "select * from crap_issue ci where ci.app_id = :appId and ci.start_time <= :time and ci.end_time> :time limit 1")
    CrapIssue findByAppIdAndStartTimeGreaterThanAndEndTimeLessThan(@Param("appId") String appId,@Param("time") Date time);

    List<CrapIssue> findByAppIdAndStatus(String appId, Integer crapStatus);

    @Query(nativeQuery = true, value = "select max(ci.end_time) from crap_issue ci where ci.app_id = :appId")
    Date findLatestEndTime(@Param("appId")String appId);

    @Modifying
    @Query(nativeQuery = true, value = "update crap_issue ci set ci.status = :status where ci.app_id = :appId and ci.end_time = :endTime")
    void updateCompleteIssueByEndTime(@Param("appId") String appId, @Param("endTime") Date endTime, @Param("status") int status);

    @Modifying
    @Query(nativeQuery = true,
            value = "update crap_issue ci set ci.sum_user = ci.sum_user+1," +
                    " ci.sum_score = ci.sum_score + :coins," +
                    " ci.bet_big = ci.bet_big + :betBig ," +
                    " ci.bet_small = ci.bet_small + :betSmall," +
                    " ci.sum_guess_big = ci.sum_guess_big + :sumGuessBig , " +
                    " ci.sum_guess_small = ci.sum_guess_small + :sumGuessSmall where ci.app_id = :appId and ci.issue = :issue")
    void recordBet(@Param("appId") String appId, @Param("issue") Integer issue, @Param("coins") Integer coins, @Param("betBig") int betBig, @Param("betSmall") int betSMall, @Param("sumGuessBig") int sumGuessBig, @Param("sumGuessSmall") int sumGuessSmall);

    Page<CrapIssue> findByAppIdAndStatusGreaterThan(String appId, Integer floorLevelOfStatus, Pageable pageable);
}
