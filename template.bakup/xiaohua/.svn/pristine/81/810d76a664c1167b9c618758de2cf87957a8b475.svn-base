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
public class ChargeResultQueryResponse extends DoubleNineResponse {
    @XmlElement(name = "result")
    private Integer result;
    @XmlElement(name = "state")
    private String state;
    @XmlElement(name = "mobile")
    private String mobile;
    @XmlElement(name = "prop")
    private String prop;
    @XmlElement(name = "price")
    private Integer price;
    @XmlElement(name = "sign")
    private String sign;

    @XmlTransient
    ChargeResultQueryRequest request;

    @Override
    public DoubleNineRequest getRequest() {
        return request;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStateMeaning() {
        return ChargeResultState.getQBFillResultState(getState()).getMeaning();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("{");
        sb.append("result:").append("\"").append(getResultMeaning(getResult())).append("\"");
        sb.append(",");
        sb.append("state:").append("\"").append(getState()).append("-").append(ChargeResultState.getQBFillResultState(getState()).getMeaning()).append("\"");
        sb.append(",");
        sb.append("mobile:").append("\"").append(getMobile()).append("\"");
        sb.append(",");
        sb.append("prop:").append("\"").append(getProp()).append("\"");
        sb.append(",");
        sb.append(",");
        sb.append("price:").append("\"").append(getPrice()).append("\"");
        sb.append(",");
        sb.append("sign:").append("\"").append(getSign()).append("\"");
        sb.append("}");
        return sb.toString();
    }
}

enum ChargeResultState {
    SUCCESS("ok", "交易成功"),
    DOING("doing", "充值中"),
    FAIL("fail", "充值失败"),
    RETURN("return", "已经退货"),
    NULL("null", "null");

    private String code;
    private String meaning;

    ChargeResultState(String code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public static ChargeResultState getQBFillResultState(String code) {
        if ("ok".equals(code)) {
            return SUCCESS;
        } else if ("doing".equals(code)) {
            return DOING;
        } else if ("fail".equals(code)) {
            return FAIL;
        } else if ("return".equals(code)) {
            return RETURN;
        } else {
            return NULL;
        }
    }

    String getCode() {
        return code;
    }

    String getMeaning() {
        return meaning;
    }
}
