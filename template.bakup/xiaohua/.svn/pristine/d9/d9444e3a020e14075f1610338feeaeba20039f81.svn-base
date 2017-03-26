package com.cjteam.xiao.service.doublenine.impl.qb;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.DoubleNineRequest;
import com.cjteam.xiao.service.doublenine.model.DoubleNineProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
public class QueryProductRequest extends DoubleNineRequest {
    private static final Logger log = LoggerFactory.getLogger(QueryProductRequest.class);

    private int type = ALL;

    public QueryProductRequest(DoubleNineAgent agent) {
        interfaceAPI = "http://api.99dou.com/direct/products.aspx";
        setAgent(agent);
        try {
            jc = JAXBContext.newInstance(
                    DoubleNineProduct.class,
                    QueryProductResponse.class);

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
        params.put("type", getType());
        return params;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        if (type < 0 || type > 4)
            throw new RuntimeException("product query type param invalid value [0,1,2,3,4]");
        this.type = type;
    }

    public static final int ALL = 0;
    public static final int PHONE_QUICK = 1;
    public static final int PHONE_SLOW = 2;
    public static final int QB = 3;
    public static final int GAME = 4;
}
