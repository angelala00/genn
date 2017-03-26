package com.cjteam.xiao.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by ChenLong on 14-2-23.
 */
@Entity
@Table(name = "crap_bet")
public class CrapBet implements Serializable{
    private Long id;
    private String appId;
    private User user;
    private CrapIssue issue;
    private Integer betType;
    private Integer coins=0;
    private Integer issueResult = 0;
    private Integer lotteryScore = 0;
    private Date createTime;
    private Date lotteryTime;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
//    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "issue", referencedColumnName = "issue")
    public CrapIssue getIssue() {
        return issue;
    }

    public void setIssue(CrapIssue issue) {
        this.issue = issue;
    }

    @Column(name = "type")
    public Integer getBetType() {
        return betType;
    }

    public void setBetType(Integer betType) {
        this.betType = betType;
    }

    @Column(name = "coins")
    public Integer getCoins() {
        return coins;
    }

    public void setCoins(Integer coins) {
        this.coins = coins;
    }

    @Column(name = "issue_result")
    public Integer getIssueResult() {
        return issueResult;
    }

    public void setIssueResult(Integer issueResult) {
        this.issueResult = issueResult;
    }

    @Column(name = "lottery_score")
    public Integer getLotteryScore() {
        return lotteryScore;
    }

    public void setLotteryScore(Integer lotteryScore) {
        this.lotteryScore = lotteryScore;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "lottery_time")
    public Date getLotteryTime() {
        return lotteryTime;
    }

    public void setLotteryTime(Date lotteryTime) {
        this.lotteryTime = lotteryTime;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @PrePersist
    public void prePersist() {
        if (createTime == null)
            setCreateTime(new Date());
    }
}
