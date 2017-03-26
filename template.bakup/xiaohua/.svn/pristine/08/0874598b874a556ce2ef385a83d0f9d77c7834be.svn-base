package com.cjteam.xiao.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Table(name = "exchange_user")
public class ExchangeUser implements Serializable {
    public static final int STATUS_INVALID = 0;
    public static final int STATUS_USEABLE = 1;
    public static final int STATUS_EXCHANGEABLE = 2;
    public static final int STATUS_EXCHANGED = 3;

    private Integer id;
    private String appId;
    private String userId;
    private Integer points;
    private Date createTime;
    private Integer status;
    private String lock;
    private Integer totallyExchangedMoney = 0;


    // Property accessors
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Column(name = "user_id", nullable = false, length = 100)
    public String getUserId() {
        return userId;
    }

    @Column(name = "points", nullable = false, length = 100)
    public Integer getPoints() {
        return points;
    }

    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    @Column(name = "status", nullable = false)
    public Integer getStatus() {
        return status;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Column(name = "totally_exchanged_money", nullable = false)
    public Integer getTotallyExchangedMoney() {
        return totallyExchangedMoney;
    }

    public void setTotallyExchangedMoney(Integer totallyExchangedMoney) {
        this.totallyExchangedMoney = totallyExchangedMoney;
    }

    @Column(name = "lock_tag")
    public String getLock() {
        return lock;
    }

    public void setLock(String lock) {
        this.lock = lock;
    }
}
