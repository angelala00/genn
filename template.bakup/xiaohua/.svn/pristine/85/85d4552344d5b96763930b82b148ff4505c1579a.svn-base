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
public class DirectChargeResponse extends DoubleNineResponse {
    @XmlElement(name = "result")
    private Integer result;
    @XmlElement(name = "sign")
    private String sign;

    @XmlTransient
    DirectChargeRequest request;

    @Override
    public DoubleNineRequest getRequest() {
        return request;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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
        sb.append("sign:").append("\"").append(getSign()).append("\"");
        sb.append("}");
        return sb.toString();
    }
}
