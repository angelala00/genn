package com.cjteam.xiao.service.doublenine.impl.qb;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.DoubleNineRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 13-10-26
 */
public class QBFillRequest extends DoubleNineRequest {
    private static final Logger log = LoggerFactory.getLogger(QBFillRequest.class);

    private String outTradeId;
    private String account;
    private String productId;
    private Integer quantity;
    private String notifyUrl;

    public QBFillRequest(DoubleNineAgent agent) {
        interfaceAPI = "http://api.99dou.com/direct/fill.aspx";
        setAgent(agent);
        try {
            jc = JAXBContext.newInstance(QBFillResponse.class);

            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException e) {
            log.error(e.getLocalizedMessage(), e);
            System.exit(-1);
        }
    }

    @Override
    public Map<String, Object> getKeyValuePair() {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("partner", getPartner());
        params.put("out_trade_id", getOutTradeId());
        params.put("account", getAccount());
        params.put("product_id", getProductId());
        params.put("quantity", getQuantity());
        params.put("notify_url", getNotifyUrl());
        return params;
    }

    public String getOutTradeId() {
        return outTradeId;
    }

    public void setOutTradeId(String outTradeId) {
        this.outTradeId = outTradeId;
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

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
