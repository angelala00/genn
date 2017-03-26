package com.cjteam.xiao.service.impl;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by ChenLong
 * Date: 13-11-19
 */
public class QueryCondition {
    private Integer[] id;
    private String appId;
    private String name;
    private String mobile;
    private String ip;
    private String mac;
    private String token;
    private String openUdid;
    private String payNo;// qq,or telphone number,or alipay number
    private int pageNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date afterDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beforeDate;
    private boolean unPaid;
    private String  channel;
    private String  productType;

    private String queryField;
    private String queryFieldValue;

    public String getName() {
        return name;
    }

    public String getQueryName() {
        if (StringUtils.isNotBlank(name))
            return "%" + getName() + "%";
        return getName();
    }

    public void setName(String name) {
        this.name = StringUtils.trimToEmpty(name);
    }

    public String getMobile() {
        return mobile;
    }

    public String getQueryMobile() {
        if (StringUtils.isNotBlank(mobile))
            return "%" + getMobile() + "%";
        return getMobile();
    }

    public void setMobile(String mobile) {
        this.mobile = StringUtils.trimToEmpty(mobile);
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo < 0 ? 0 : pageNo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOpenUdid() {
        return openUdid;
    }

    public void setOpenUdid(String openUdid) {
        this.openUdid = openUdid;
    }

    @Override
    public String toString() {
        return "QueryCondition{" +
                "appId='" + appId + '\'' +
                "name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", ip='" + ip + '\'' +
                ", mac='" + mac + '\'' +
                ", token='" + token + '\'' +
                ", openUdid='" + openUdid + '\'' +
                ", payNo='" + payNo + '\'' +
                ", pageNo=" + pageNo +
                ", afterDate=" + afterDate +
                ", beforeDate=" + beforeDate +
                ", unPaid=" + unPaid +
                ", queryField='" + queryField + '\'' +
                ", queryFieldValue='" + queryFieldValue + '\'' +
                ", channel='" + channel + '\'' +
                ", ids='" + ArrayUtils.toString(id) + '\'' +
                '}';
    }

    public static boolean isValid(QueryCondition queryCondition) {
        if (null == queryCondition || StringUtils.isBlank(queryCondition.getAppId()))
            return false;
        return StringUtils.isNotBlank(queryCondition.getMobile()) || StringUtils.isNotBlank(queryCondition.getName())
                || StringUtils.isNotBlank(queryCondition.getIp()) || StringUtils.isNotBlank(queryCondition.getMac()) || StringUtils.isNotBlank(queryCondition.getToken())
                || StringUtils.isNotBlank(queryCondition.getOpenUdid());
    }

    public boolean isIpQuery() {
        return StringUtils.isNotBlank(getIp());
    }

    public boolean isMacQuery() {
        return StringUtils.isNotBlank(getMac());
    }

    public boolean isTokenQuery() {
        return StringUtils.isNotBlank(getToken());
    }

    public boolean isOpenUidiQuery() {
        return StringUtils.isNotBlank(getOpenUdid());
    }

    public boolean isNameQuery() {
        return StringUtils.isNotBlank(getName());
    }

    public boolean isMobileQuery() {
        return StringUtils.isNotBlank(getMobile());
    }

    public String getQueryField() {
        if (StringUtils.isNotBlank(getMobile())) {
            queryFieldValue = getMobile();
            return "mobile";
        } else if (StringUtils.isNotBlank(getName())) {
            queryFieldValue = getName();
            return "name";
        } else if (StringUtils.isNotBlank(getIp())) {
            queryFieldValue = getIp();
            return "ip";
        } else if (StringUtils.isNotBlank(getMac())) {
            queryFieldValue = getMac();
            return "mac";
        } else if (StringUtils.isNotBlank(getToken())) {
            queryFieldValue = getToken();
            return "token";
        } else if (StringUtils.isNotBlank(getOpenUdid())) {
            queryFieldValue = getOpenUdid();
            return "openUdid";
        } else return null;
    }

    public String getQueryFieldValue() {
        return queryFieldValue;
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Date afterDate) {
        this.afterDate = afterDate;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(Date beforeDate) {
        this.beforeDate = beforeDate;
    }

    public boolean isUnPaid() {
        return unPaid;
    }

    public void setUnPaid(boolean unPaid) {
        this.unPaid = unPaid;
    }

    public String getPayNo() {
        return payNo;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public Integer[] getId() {
        return id;
    }

    public void setId(Integer[] id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
