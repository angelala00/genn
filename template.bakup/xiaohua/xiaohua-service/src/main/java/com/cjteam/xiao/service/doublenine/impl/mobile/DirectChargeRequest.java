package com.cjteam.xiao.service.doublenine.impl.mobile;

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
public class DirectChargeRequest extends DoubleNineRequest {
    private static final Logger log = LoggerFactory.getLogger(DirectChargeRequest.class);

    private String outTradeId;
    private String mobile;
    private Integer value;
    private Integer type;
    private String notifyUrl;

    public DirectChargeRequest(DoubleNineAgent agent) {
        interfaceAPI = "http://api.99dou.com/mobile/direct.aspx/";
        setAgent(agent);
        try {
            jc = JAXBContext.newInstance(DirectChargeResponse.class);

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
        params.put("mobile", getMobile());
        params.put("value", getValue());
        params.put("type", getType());
        params.put("notify_url", getNotifyUrl());
        return params;
    }

    public String getOutTradeId() {
        return outTradeId;
    }

    public void setOutTradeId(String outTradeId) {
        this.outTradeId = outTradeId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value / DoubleNineAgent.RATE;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> tmp = getKeyValuePair();
        sb.append(super.toString()).append("{");
        for (String key : tmp.keySet()) {
            sb.append(key).append(tmp.get(key)).append(",");
        }
        sb.append("}");
        return sb.toString();
    }
}
