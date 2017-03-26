package com.cjteam.xiao.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by ChenLong on 2014/5/6.
 */
@Entity
@Table(name = "app_client")
public class FrontApp {
    public static final String notificationType_native = "apple_native";
    public static final String notificationType_xinge = "qq_xinge";

    public static final int TEST = 0;
    public static final int JUDGE = 1;
    public static final int ONLINE = 2;
    public static final int ADTEST = 3; // temp status for score callback test
    private String id;
    private String name;
    private String desc;
    private String host;
    private int status = 0;
    private String version;
    private String updateUrl;
    private Integer policy = 0;
    private String aboutPage;
    private String helpPage;
    private String notificationType;

    @Id
    @Column(name = "id", unique = true, nullable = false)
    public String getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    @Column(name = "host")
    public String getHost() {
        return host;
    }

    @Column(name = "status")
    public int getStatus() {
        return status;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FrontApp{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", host='" + host + '\'' +
                ", status=" + status +
                '}';
    }

    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Column(name = "update_url")
    public String getUpdateUrl() {
        return updateUrl;
    }

    public void setUpdateUrl(String updateUrl) {
        this.updateUrl = updateUrl;
    }

    @Column(name = "policy")
    public Integer getPolicy() {
        return policy;
    }

    public void setPolicy(Integer policy) {
        this.policy = policy;
    }

    @Column(name = "about_page")
    public String getAboutPage() {
        return aboutPage;
    }

    public void setAboutPage(String aboutPage) {
        this.aboutPage = aboutPage;
    }

    @Column(name = "help_page")
    public String getHelpPage() {
        return helpPage;
    }

    public void setHelpPage(String helpPage) {
        this.helpPage = helpPage;
    }


    @Column(name = "notification_type")
    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }
}
