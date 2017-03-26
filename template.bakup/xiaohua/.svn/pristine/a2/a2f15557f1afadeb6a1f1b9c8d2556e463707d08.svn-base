package doublenine.qb;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.SimpleHttpRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QBFillRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QBFillResponse;
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
 * User: wuyimin
 * Date: 13-10-26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/double-nine-service-context.xml"})
public class FileRequestTest {
    private static final Logger log = LoggerFactory.getLogger(FileRequestTest.class);
    private QBFillRequest request;

    static DoubleNineAgent agent = new DoubleNineAgent("11908","6KEq6BoB9KeKXJVgqpHM35Z6qMTltqYS8wPnuBsDRexsjND3mviOjtyOv0ZD6mq6");
    @Autowired
    SimpleHttpRequest simpleHttpRequest;

    @Before
    public void setUp() throws Exception {
        request = new QBFillRequest(agent);
//        request.ge
        request.setOutTradeId("1455fafasfd65601X");
        request.setAccount("107007297");
        request.setQuantity(1);
        request.setProductId("10316");
        request.setNotifyUrl("http://www.mywebsite.com/callback.aspx");
    }

    @Test
    public void testFillPhoneCharge() throws Exception {
        QBFillResponse response = (QBFillResponse) simpleHttpRequest.getResponseByGetMothd(request);
        log.info("query products result is \n {}  .", response.toString());
        Assert.assertNotEquals("参数错误",response.getResult(), new Integer(1));
        Assert.assertNotEquals("签名验证失败",response.getResult(), new Integer(5));
    }
}
