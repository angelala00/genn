package com.cjteam.xiao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;


/**
 * IpUserLimit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "ip_user_limit"
        , catalog = "crap"
)

public class IpUserLimit implements java.io.Serializable {


    // Fields    

    private Integer id;
    private String appId;
    private User user;
    private String ip;
    private Short tag;
    private Date date;


    // Constructors

    /**
     * default constructor
     */
    public IpUserLimit() {
    }


    /**
     * full constructor
     */
    public IpUserLimit(User user, String ip, Short tag) {
        this.user = user;
        this.ip = ip;
        this.tag = tag;
    }


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", updatable = false)
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "ip", nullable = false, length = 45)

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "tag", nullable = false)
    public Short getTag() {
        return this.tag;
    }

    public void setTag(Short tag) {
        this.tag = tag;
    }

    @Column(name = "date", nullable = false, length = 19)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }


}