package com.cjteam.xiao.service.doublenine.impl.mobile;

import com.cjteam.xiao.service.doublenine.DoubleNineRequest;
import com.cjteam.xiao.service.doublenine.DoubleNineResponse;

import javax.xml.bind.annotation.*;

/**
 * Created by ChenLong
 * Date: 13-10-26
 */
@XmlRootElement(name = "return")
@XmlAccessorType(XmlAccessType.FIELD)
public class TelephoneRegionResponse extends DoubleNineResponse {
    @XmlElement(name = "result")
    private Integer result;
    @XmlElement(name = "mobile")
    private String mobile;
    @XmlElement(name = "provider")
    private String provider;
    @XmlElement(name = "province")
    private String province;
    @XmlElement(name = "city")
    private String city;

    @XmlTransient
    TelephoneRegionQueryRequest request;

    @Override
    public DoubleNineRequest getRequest() {
        return request;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("{");
        sb.append("result:").append("\"").append(getResult()).append("-").append(getResultMeaning(getResult())).append("\"");
        sb.append(",");
        sb.append("mobile:").append("\"").append(getMobile()).append("\"");
        sb.append("provider:").append("\"").append(getProvider()).append("\"");
        sb.append("province:").append("\"").append(getProvince()).append("\"");
        sb.append("city:").append("\"").append(getCity()).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
