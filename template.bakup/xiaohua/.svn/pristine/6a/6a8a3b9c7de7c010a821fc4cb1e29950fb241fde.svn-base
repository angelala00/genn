package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.CrapIssue;

import java.util.Date;

/**
 * Created by ChenLong on 14-2-23.
 */
public class CrapIssueVo {
    private Integer issueNo=0;
    private Date startTime;
    private Date endTime;
    private Integer sumScore=0;
    private Integer sumUser=0;
    private Integer sumGuessBig=0;
    private Integer sumGuessSmall=0;
    private Integer result = 0;

    public CrapIssueVo(CrapIssue issue) {
        if(issue==null)
            return;
        setIssueNo(issue.getIssue());
        setStartTime(issue.getStartTime());
        setEndTime(issue.getEndTime());
        setSumScore(issue.getSumScore());
        setSumUser(issue.getSumUser());
        setSumGuessBig(issue.getSumGuessBig());
        setSumGuessSmall(issue.getSumGuessSmall());
        setResult(issue.getResult());
    }

    public Integer getIssueNo() {
        return issueNo;
    }

    public void setIssueNo(Integer issueNo) {
        this.issueNo = issueNo;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    public Integer getSumUser() {
        return sumUser;
    }

    public void setSumUser(Integer sumUser) {
        this.sumUser = sumUser;
    }

    public Integer getSumGuessBig() {
        return sumGuessBig;
    }

    public void setSumGuessBig(Integer sumGuessBig) {
        this.sumGuessBig = sumGuessBig;
    }

    public Integer getSumGuessSmall() {
        return sumGuessSmall;
    }

    public void setSumGuessSmall(Integer sumGuessSmall) {
        this.sumGuessSmall = sumGuessSmall;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
