package com.cjteam.xiao.model;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
 * Integral entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "integral")
public class Integral implements java.io.Serializable {

    // Fields

    private Integer id;
    private String appId;
    private User user;
    private IntegralType integralType;
    private Channel channel;
    private Integer score;
    private Date createTime;
    public static final String filedCreateTime="createTime";
    private Boolean status;
    private String orderId;
    private String adId;
    private String adName;
    private String msg;
    private String udid;
    private String ip;
    // Constructors

    /**
     * default constructor
     */
    public Integral() {
    }

    /**
     * minimal constructor
     */
    public Integral(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * full constructor
     */
    public Integral(User user, IntegralType integralType, Channel channel,
                    Integer score, Date createTime, Boolean status) {
        this.user = user;
        this.integralType = integralType;
        this.channel = channel;
        this.score = score;
        this.createTime = createTime;
        this.status = status;
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
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "type")
    public IntegralType getIntegralType() {
        return this.integralType;
    }

    public void setIntegralType(IntegralType integralType) {
        this.integralType = integralType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "channel", referencedColumnName = "code")
    public Channel getChannel() {
        return this.channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    @Column(name = "score")
    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "create_time", nullable = false, length = 19)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return this.status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Column(name = "order_id")
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Column(name = "ad_id", length = 100)
    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    @Column(name = "ad_name", length = 100)
    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    @Column(name = "msg", length = 200)
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Column(name = "udid")
    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid;
    }

    @Override
    public String toString() {
        return "Integral [id=" + id + ", user.getUserId=" + user.getUserId() + ", integralType=" + integralType + ", channel.getName()=" + channel.getName() + ", score=" + score
                + ", createTime=" + createTime + ", status=" + status + "]";
    }

}