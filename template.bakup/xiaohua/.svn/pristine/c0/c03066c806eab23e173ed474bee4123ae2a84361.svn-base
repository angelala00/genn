package com.cjteam.xiao.model;

import javax.persistence.*;

/**
 * Created by ChenLong on 14-3-9.
 */
@Entity
@Table(name = "ip_location_library")
public class IpLocation {
    private Long id;
    private String ip;
    private String country;
    private String area;
    private String region;
    private String city;
    private String county;
    private String isp;

    private String countryId;
    private String areaId;
    private String regionId;
    private String cityId;
    private String countyId;
    private String ispId;

    public IpLocation() {
    }

    public IpLocation(String ip) {
        setIp(ip);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    public Long getId() {
        return id;
    }

    @Column(name = "ip", length = 100)
    public String getIp() {
        return ip;
    }

    @Column(name = "country", length = 100)
    public String getCountry() {
        return country;
    }

    @Column(name = "area", length = 100)
    public String getArea() {
        return area;
    }

    @Column(name = "region", length = 100)
    public String getRegion() {
        return region;
    }

    @Column(name = "city", length = 100)
    public String getCity() {
        return city;
    }

    @Column(name = "county", length = 100)

    public String getCounty() {
        return county;
    }

    @Column(name = "isp", length = 100)
    public String getIsp() {
        return isp;
    }

    @Column(name = "country_id", length = 50)
    public String getCountryId() {
        return countryId;
    }

    @Column(name = "area_id", length = 50)
    public String getAreaId() {
        return areaId;
    }

    @Column(name = "region_id", length = 50)
    public String getRegionId() {
        return regionId;
    }

    @Column(name = "city_id", length = 50)
    public String getCityId() {
        return cityId;
    }

    @Column(name = "county_id", length = 50)
    public String getCountyId() {
        return countyId;
    }

    @Column(name = "isp_id", length = 50)
    public String getIspId() {
        return ispId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public void setCountyId(String countyId) {
        this.countyId = countyId;
    }

    public void setIspId(String ispId) {
        this.ispId = ispId;
    }

    @Override
    public String toString() {
        return "IpLocation{" +
                "ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", area='" + area + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", isp='" + isp + '\'' +
                ", countryId='" + countryId + '\'' +
                ", areaId='" + areaId + '\'' +
                ", regionId='" + regionId + '\'' +
                ", cityId='" + cityId + '\'' +
                ", countyId='" + countyId + '\'' +
                ", ispId='" + ispId + '\'' +
                '}';
    }
}
