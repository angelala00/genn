package com.cjteam.xiao.model;

import javax.persistence.*;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by ChenLong
 * Date: 13-10-31
 */
@Entity
@Table(name = "double_nine_agent")
public class DoubleNineAgent {
    public static final Integer RATE = 100;
    private Integer id;
    private String appId;
    private String partner;
    private String key;
    private String orderIdPrefix;
    private String notifyUrl;

    public DoubleNineAgent() {
    }

    public DoubleNineAgent(String partner, String key) {
        this.partner = partner;
        this.key = key;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "app_id",nullable = false,unique = true)
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Column(name = "partner")
    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    @Column(name = "key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Column(name = "order_prefix")
    public String getOrderIdPrefix() {
        return orderIdPrefix;
    }

    public void setOrderIdPrefix(String orderIdPrefix) {
        this.orderIdPrefix = orderIdPrefix;
    }

    @Column(name = "notify_url")
    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public static String md5Digest(String source) throws NoSuchAlgorithmException, IOException {
        return DoubleNineAgent.bytesToHex(MessageDigest.getInstance("MD5").digest(source.getBytes("UTF-8")));
    }

    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for (int j = 0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static Integer RESULT_OK = 0;

    public static String STATE_OK = "ok";
    public static String STATE_DOING = "doing";
    public static String STATE_FAIL = "fail";
    public static String STATE_RETURN = "return";
}
