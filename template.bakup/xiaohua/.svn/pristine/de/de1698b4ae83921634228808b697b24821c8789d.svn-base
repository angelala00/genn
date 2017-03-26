package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.CrapBet;
import com.cjteam.xiao.model.StateType;
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
public interface CrapBetRepository extends PagingAndSortingRepository<CrapBet, Long> {
    List<CrapBet> findByAppIdAndUser_UserIdAndIssue_Issue(String appId,String userId, Integer issue);

    List<CrapBet> findByAppIdAndUser_UserId(String appId, String userId, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "update crap_bet cb set cb.issue_result =:result,cb.lottery_score = 0,cb.lottery_time = :lotteryTime where cb.issue = :issue and cb.app_id = :appId")
    int updateBetLoserStatus(@Param("appId") String appId,@Param("issue") Integer issue, @Param("result") Integer result, @Param("lotteryTime") Date lotteryTime);

    List<CrapBet> findByAppIdAndIssue_IssueAndBetType(String appId,Integer issue, Integer result);

    @Query(nativeQuery = true, value = "select  * from crap_bet cb where cb.app_id = :appId and cb.user_id = :userId and cb.issue_result <> 0  and cb.type = cb.issue_result")
    List<CrapBet> findWinIssuesByAppIdAndUserId(@Param("appId") String appId,@Param("userId") String userId);
}
