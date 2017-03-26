package com.cjteam.xiao.duiba.model;

import javax.persistence.*;

/**
 * Created by ChenLong on 2014/7/9.
 */
@Entity
@Table(name = "duiba_account")
public class DuibaAccount {
    private Integer id;
    private String account;
    private String appKey;
    private String appSecret;
    private AppDuibaAccountReference appDuibaAccountReference ;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "duibaAccount")
    public AppDuibaAccountReference getAppDuibaAccountReference() {
        return appDuibaAccountReference;
    }

    public void setAppDuibaAccountReference(AppDuibaAccountReference appDuibaAccountReference) {
        this.appDuibaAccountReference = appDuibaAccountReference;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    @Column(name = "account")
    public String getAccount() {
        return account;
    }

    @Column(name = "app_key")
    public String getAppKey() {
        return appKey;
    }

    @Column(name = "app_secret")
    public String getAppSecret() {
        return appSecret;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
