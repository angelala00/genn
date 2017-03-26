package com.cjteam.xiao.duiba.model;

import javax.persistence.*;

/**
 * Created by ChenLong on 2014/7/9.
 */
@Entity
@Table(name = "app_duiba_account_reference")
public class AppDuibaAccountReference {
    private Integer id;
    private String appId;
    private DuibaAccount duibaAccount;
    private Byte valid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Integer getId() {
        return id;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    @OneToOne
    @JoinColumn(name = "duiba_account")
    public DuibaAccount getDuibaAccount() {
        return duibaAccount;
    }

    @Column(name = "valid")
    public Byte getValid() {
        return valid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setDuibaAccount(DuibaAccount duibaAccount) {
        this.duibaAccount = duibaAccount;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }
}
