package com.cjteam.xiao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ChenLong on 14-3-21.
 */
@Entity
@Table(name = "white_list_alipay")
public class AlipayWhiteList {
    private String account;
    private Boolean valid = Boolean.TRUE;

    @Id
    @Column(name = "account", nullable = false)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Column(name = "valid")
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
