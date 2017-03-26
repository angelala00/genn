package com.cjteam.xiao.model;

import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

/**
 * Created by ChenLong on 2014/7/7.
 */
@Entity
@Table(name = "about")
public class About  implements java.io.Serializable {
    private Integer id;
    private String title;
    private String content;
    private Byte valid;
    private String appId;


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

    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    @Column(name = "content")
    public String getContent() {
        return content;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setValid(Byte valid) {
        this.valid = valid;
    }
}
