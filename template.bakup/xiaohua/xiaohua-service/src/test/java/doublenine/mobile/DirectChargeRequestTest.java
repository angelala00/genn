package doublenine.mobile;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.SimpleHttpRequest;
import com.cjteam.xiao.service.doublenine.impl.mobile.DirectChargeRequest;
import com.cjteam.xiao.service.doublenine.impl.mobile.DirectChargeResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by ChenLong
 * Date: 13-10-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/double-nine-service-context.xml"})
public class DirectChargeRequestTest {
    private DirectChargeRequest request;

    @Autowired
    SimpleHttpRequest simpleHttpRequest;
    static DoubleNineAgent agent = new DoubleNineAgent("11908","6KEq6BoB9KeKXJVgqpHM35Z6qMTltqYS8wPnuBsDRexsjND3mviOjtyOv0ZD6mq6");

    @Before
    public void setUp() throws Exception {
        request = new DirectChargeRequest(agent);

        request.setOutTradeId("13561130932safad"+System.currentTimeMillis());
        request.setMobile("13561130932");
        request.setValue(100);
        request.setType(0);
        request.setNotifyUrl("http://182.92.66.201:8080/dn/charge-notify");
    }

    @Test
    public void testFillPhoneCharge() throws Exception {
        DirectChargeResponse response = (DirectChargeResponse) simpleHttpRequest.getResponseByGetMothd(request);
        Assert.assertNotEquals("参数错误", response.getResult(), new Integer(1));
        Assert.assertNotEquals("签名验证失败", response.getResult(), new Integer(5));
    }
}
