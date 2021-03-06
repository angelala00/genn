package com.cjteam.xiao.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "daily_region_integral_limit")
public class DailyRegionIntegralLimit implements java.io.Serializable {
    private Integer id;
    private String appId;
    private String channel;
    private Integer score = 0;
    private String region;
    private String city;
    private String regionName;
    private String cityName;

    public DailyRegionIntegralLimit() {
    }

    public DailyRegionIntegralLimit(String appId, String region, String regionName, String city, String cityName, String channelCode) {
        setAppId(appId);
        setRegion(region);
        setRegionName(regionName);
        setCity(city);
        setCityName(cityName);
        setChannel(channelCode);
        setScore(0);
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "channel", length = 50)
    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    @Column(name = "score", precision = 12, scale = 0)
    public Integer getScore() {
        return this.score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Column(name = "region")
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    @Column(name = "region_name")
    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "city_name")
    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Column(name = "app_id")
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    @Override
    public String toString() {
        return "DailyRegionIntegralLimit{" +
                "id=" + id +
                ", channel='" + channel + '\'' +
                ", score=" + score +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", appId='" + appId + '\'' +
                ", regionName='" + regionName + '\'' +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}