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
 * Date: 14-03-04
 */
public class TelephoneRegionQueryRequest extends DoubleNineRequest {
    private static final Logger log = LoggerFactory.getLogger(TelephoneRegionQueryRequest.class);

    private String mobile;

    public TelephoneRegionQueryRequest(DoubleNineAgent agent,String mobile) {
        interfaceAPI = "http://api.99dou.com/mobile/query1.aspx";
        setAgent(agent);
        try {
            jc = JAXBContext.newInstance(TelephoneRegionResponse.class);

            marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            unmarshaller = jc.createUnmarshaller();
        } catch (JAXBException e) {
            log.error(e.getLocalizedMessage(), e);
            System.exit(-1);
        }
        setMobile(mobile);
    }

    @Override
    public Map<String, Object> getKeyValuePair() {
        Map<String, Object> params = new LinkedHashMap<String, Object>();
        params.put("partner", getPartner());
        params.put("mobile", getMobile());
        params.put("charset", "utf-8");
        return params;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
