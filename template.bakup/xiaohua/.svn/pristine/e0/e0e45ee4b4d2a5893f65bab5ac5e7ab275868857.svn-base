package com.cjteam.xiao.duiba.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ChenLong on 2014/7/9.
 */
@Entity
@Table(name = "duiba_credit_consume")
public class DuibaCreditConsume {
    private Long id;
    private String appKey;
    private Integer credits;//消耗积分数
    private String orderNum;//兑吧订单号
    private String description;

    private String type = "";//类型：QB,Phonebill,AlipayFast,Coupon  所有类型不区分大小写
    private Integer facePrice = 0;//面值，分为单位
    private Integer actualPrice = 0;//实际扣款，分为单位
    private String alipay = "";//支付宝账号,type为alipayfast时会有值
    private String phone = "";//手机号,type为phonebill时会有值
    private String qq = "";//qq号,type为qb时会有值
    private String uid;

    private boolean waitAudit = false;//是否等待审核， 如果返回true，表示此订单需要审核，审核通过后才会继续下去。 如果返回false表示此订单无须审核，会直接继续兑换流程

    private boolean creditNotifyResult;
    private String creditNotifyMessage;
    private Date creditNotifyTime;

    private String consumeMessage;
    private Boolean consumeResult;
    private Date timestamp;//consume 时间戳

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false,unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "app_key")
    public String getAppKey() {
        return appKey;
    }
    @Column(name = "timestamp")
    public Date getTimestamp() {
        return timestamp;
    }
    @Column(name = "credits")
    public Integer getCredits() {
        return credits;
    }
    @Column(name = "order_num")
    public String getOrderNum() {
        return orderNum;
    }
    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    @Column(name = "type")
    public String getType() {
        return type;
    }
    @Column(name = "face_price")
    public Integer getFacePrice() {
        return facePrice;
    }
    @Column(name = "actual_price")
    public Integer getActualPrice() {
        return actualPrice;
    }
    @Column(name = "alipay")
    public String getAlipay() {
        return alipay;
    }
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }
    @Column(name = "wait_audit")
    public boolean isWaitAudit() {
        return waitAudit;
    }

    @Column(name = "notify_result")
    public boolean isCreditNotifyResult() {
        return creditNotifyResult;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFacePrice(Integer facePrice) {
        this.facePrice = facePrice;
    }

    public void setActualPrice(Integer actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setWaitAudit(boolean waitAudit) {
        this.waitAudit = waitAudit;
    }

    public void setCreditNotifyResult(boolean creditNotifyResult) {
        this.creditNotifyResult = creditNotifyResult;
    }

    public void setCreditNotifyMessage(String creditNotifyMessage) {
        this.creditNotifyMessage = creditNotifyMessage;
    }

    @Column(name = "notify_message")
    public String getCreditNotifyMessage() {
        return creditNotifyMessage;
    }

    public void setCreditNotifyTime(Date creditNotifyTime) {
        this.creditNotifyTime = creditNotifyTime;
    }

    @Column(name = "notify_date")
    public Date getCreditNotifyTime() {
        return creditNotifyTime;
    }

    public void setConsumeMessage(String consumeMessage) {
        this.consumeMessage = consumeMessage;
    }
    @Column(name = "consume_message")
    public String getConsumeMessage() {
        return consumeMessage;
    }

    public void setConsumeResult(Boolean consumeResult) {
        this.consumeResult = consumeResult;
    }
    @Column(name = "consume_result")
    public Boolean getConsumeResult() {
        return consumeResult;
    }
}
