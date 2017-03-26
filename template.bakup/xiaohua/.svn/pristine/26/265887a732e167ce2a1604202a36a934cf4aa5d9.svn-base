package com.cjteam.xiao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by ChenLong on 14-2-23.
 */
@Entity
@Table(name = "crap_issue")
public class CrapIssue implements Serializable{
    public static final Integer CRAP_BIG = 2;
    public static final Integer CRAP_SMALL = 1;
    public static final int CRAP_STATUS_INIT = 0;
    public static final int CRAP_STATUS_DOING = 1;
    public static final int CRAP_STATUS_LOTTERY = 2;
    public static final int CRAP_STATUS_DONE = 3;

    private Integer id;
    private Integer issue;
    private String  appId;
    private Date startTime;
    private Date endTime;
    private Integer sumScore = 0;
    private Integer sumUser = 0;
    private Integer betBigPersonSum = 0;
    private Integer betSmallPersonSum = 0;
    private Integer sumGuessBig = 0;
    private Integer sumGuessSmall = 0;
    private Integer result = 0;
    private Integer status = 0;
    private Integer surplus=0;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "issue")
    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    @Column(name = "start_time", nullable = false, length = 19)
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time", nullable = false, length = 19)
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Column(name = "sum_score")
    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    @Column(name = "sum_user")
    public Integer getSumUser() {
        return sumUser;
    }

    public void setSumUser(Integer sumUser) {
        this.sumUser = sumUser;
    }

    @Column(name = "sum_guess_big")
    public Integer getSumGuessBig() {
        return sumGuessBig;
    }

    public void setSumGuessBig(Integer sumGuessBig) {
        this.sumGuessBig = sumGuessBig;
    }

    @Column(name = "sum_guess_small")
    public Integer getSumGuessSmall() {
        return sumGuessSmall;
    }

    public void setSumGuessSmall(Integer sumGuessSmall) {
        this.sumGuessSmall = sumGuessSmall;
    }

    @Column(name = "status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "result")
    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Column(name = "bet_big")
    public Integer getBetBigPersonSum() {
        return betBigPersonSum;
    }

    public void setBetBigPersonSum(Integer betBigPersonSum) {
        this.betBigPersonSum = betBigPersonSum;
    }

    @Column(name = "bet_small")
    public Integer getBetSmallPersonSum() {
        return betSmallPersonSum;
    }

    public void setBetSmallPersonSum(Integer betSmallPersonSum) {
        this.betSmallPersonSum = betSmallPersonSum;
    }

    @Override
    public String toString() {
        return "CrapIssue{" +
                "issue=" + issue +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", sumScore=" + sumScore +
                ", sumUser=" + sumUser +
                ", betBigPersonSum=" + betBigPersonSum +
                ", betSmallPersonSum=" + betSmallPersonSum +
                ", sumGuessBig=" + sumGuessBig +
                ", sumGuessSmall=" + sumGuessSmall +
                ", result=" + result +
                '}';
    }

    @PrePersist
    public void prePersist() {
    }

    @Column(name = "surplus")
    public void setSurplus(Integer surplus) {
        this.surplus = surplus;
    }

    public int getSurplus() {
        return surplus;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}