package com.cjteam.xiao.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by ChenLong on 2014/7/4.
 */
@Entity
@Table(name = "channel_group")
public class ChannelGroup implements java.io.Serializable  {
    private Integer id;
    private String appId;
    private String code;
    private String name;
    private String info;
    private Byte valid;
    private List<Channel> channels;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "info")
    public String getInfo() {
        return info;
    }

    @Column(name = "valid")
    public Byte getValid() {
        return valid;
    }

    @OneToMany(mappedBy = "group")
    public List<Channel> getChannels() {
        return channels;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }

    public void setChannels(List<Channel> channels) {
        this.channels = channels;
    }
}
