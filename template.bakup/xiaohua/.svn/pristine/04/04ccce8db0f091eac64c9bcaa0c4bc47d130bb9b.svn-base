package com.cjteam.xiao.service;

import com.cjteam.xiao.model.CrapBet;
import com.cjteam.xiao.model.CrapIssue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ChenLong on 14-2-23.
 */
public interface CrapService {
    public CrapIssue getCurrentIssue(String appId);

    /**
     * @param appId
     * * @param userId
     * @param coinsBet
     */
    public void assertCoinsBet(String appId,String userId, int coinsBet);

    public void betSmall(String appId, String userId, Integer issueNo, int coinsBet);

    public void betBig(String appId, String userId, Integer issueNo, int coinsBet);

    List<CrapBet> getIssueInfoByUserId(String appId, Integer issue, String userId);

    List<CrapBet> getMyIssue(String appId, String userId, Pageable pageable);

    Page<CrapIssue> getAllIssues(String appId, Pageable pageable);

    CrapIssue getIssue(String appId, Integer issue);

    /**
     * 初始化今天所有将要进行的游戏
     *
     * @return 返回总共初始化期数
     * @param appId
     */
    int initHodiernalIssues(String appId);

    void lottery(String appId);

    void lottery(CrapIssue issue);

    void issueStatusManage(String appId);

    List<CrapBet> getMyWinIssues(String appId, String userId);
}
