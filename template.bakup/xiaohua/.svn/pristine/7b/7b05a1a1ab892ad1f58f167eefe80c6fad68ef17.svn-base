package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.CrapBet;

import java.util.Date;

/**
 * Created by ChenLong on 14-3-2.
 */
public class CrapBetVo {
    private String userId="";
    private String userNick="";
    private Integer issue=-1;
    private Integer betType=0;
    private Integer issueResult=0;
    private Integer betCoins=0;
    private Integer lotteryScore=0;
    private Date betTime;
    private Date lotteryTime;

    public CrapBetVo(CrapBet bet) {
        setUserId(bet.getUser().getUserId());
        setUserNick(bet.getUser().getNickName());
        setBetType(bet.getBetType());
        setBetCoins(bet.getCoins());
        setLotteryScore(bet.getLotteryScore());
        setBetTime(bet.getCreateTime());
        setLotteryTime(bet.getLotteryTime());
        setIssue(bet.getIssue().getIssue());
        setIssueResult(bet.getIssueResult());
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public Integer getBetType() {
        return betType;
    }

    public void setBetType(Integer betType) {
        this.betType = betType;
    }

    public Integer getIssueResult() {
        return issueResult;
    }

    public void setIssueResult(Integer issueResult) {
        this.issueResult = issueResult;
    }

    public Integer getBetCoins() {
        return betCoins;
    }

    public void setBetCoins(Integer betCoins) {
        this.betCoins = betCoins;
    }

    public Integer getLotteryScore() {
        return lotteryScore;
    }

    public void setLotteryScore(Integer lotteryScore) {
        this.lotteryScore = lotteryScore;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }
}
