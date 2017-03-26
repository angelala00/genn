package com.cjteam.xiao.service.doublenine.impl.qb;

import com.cjteam.xiao.service.doublenine.DoubleNineRequest;
import com.cjteam.xiao.service.doublenine.DoubleNineResponse;

import javax.xml.bind.annotation.*;

/**
 * Created by ChenLong
 * Date: 13-10-26
 */
@XmlRootElement(name = "return")
@XmlAccessorType(XmlAccessType.FIELD)
public class QBFillResultQueryResponse extends DoubleNineResponse {

    @XmlElement(name = "result")
    private Integer result;
    @XmlElement(name = "state")
    private String state;
    @XmlElement(name = "account")
    private String account;
    @XmlElement(name = "product_id")
    private String productId;
    @XmlElement(name = "quantity")
    private Integer quantity;
    @XmlElement(name = "price")
    private Integer price;
    @XmlElement(name = "product_info")
    private String productInfo;
    @XmlElement(name = "sign")
    private String sign;

    @XmlTransient
    QBFillResultQueryRequest request;

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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStateMeaning() {
        return QBFillResultState.getQBFillResultState(getState()).getMeaning();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append("{");
        sb.append("result:").append("\"").append(getResultMeaning(getResult())).append("\"");
        sb.append(",");
        sb.append("state:").append("\"").append(getState()).append("-").append(QBFillResultState.getQBFillResultState(getState()).getMeaning()).append("\"");
        sb.append(",");
        sb.append("account:").append("\"").append(getAccount()).append("\"");
        sb.append(",");
        sb.append("productId:").append("\"").append(getProductId()).append("\"");
        sb.append(",");
        sb.append("quantity:").append("\"").append(getQuantity()).append("\"");
        sb.append(",");
        sb.append("price:").append("\"").append(getPrice()).append("\"");
        sb.append(",");
        sb.append("productInfo:").append("\"").append(getProductInfo()).append("\"");
        sb.append(",");
        sb.append("sign:").append("\"").append(getSign()).append("\"");
        sb.append("}");
        return sb.toString();
    }
}

enum QBFillResultState {
    SUCCESS("ok", "交易成功"),
    DOING("doing", "充值中"),
    FAIL("fail", "充值失败"),
    RETURN("return", "已经退货"),
    NULL("null", "null");

    private String code;
    private String meaning;

    QBFillResultState(String code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public static QBFillResultState getQBFillResultState(String code) {
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
