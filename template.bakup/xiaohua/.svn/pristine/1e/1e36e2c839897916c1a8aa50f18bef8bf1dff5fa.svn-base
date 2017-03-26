package com.cjteam.xiao.model;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by ChenLong on 14-3-2.
 */
@Entity
@Table(name = "suggestion")
public class Suggestion {
    private Long id;
    private String appId;
    private String userId;
    private String content;
    private Date createTime;

    public Suggestion() {
    }

    public Suggestion(String suggestion, String userId,String appId) {
        setContent(suggestion);
        setUserId(userId);
        setAppId(appId);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Column(name = "content", length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @PrePersist
    public void prePersist() {
        createTime = new Date();
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
