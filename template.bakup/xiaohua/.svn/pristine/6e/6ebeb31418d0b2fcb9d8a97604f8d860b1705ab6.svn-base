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
public class QBFillResultQueryRequest extends DoubleNineRequest {
    private static final Logger log = LoggerFactory.getLogger(QBFillResultQueryRequest.class);

    private String outTradeId;

    public QBFillResultQueryRequest(DoubleNineAgent agent) {
        interfaceAPI = "http://api.99dou.com/direct/query.aspx";
        setAgent(agent);
        try {
            jc = JAXBContext.newInstance(QBFillResultQueryResponse.class);

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
        return params;
    }

    public String getOutTradeId() {
        return outTradeId;
    }

    public void setOutTradeId(String outTradeId) {
        this.outTradeId = outTradeId;
    }
}
