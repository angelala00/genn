package doublenine.mobile;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.SimpleHttpRequest;
import com.cjteam.xiao.service.doublenine.impl.mobile.TelephoneRegionResponse;
import com.cjteam.xiao.service.doublenine.impl.mobile.TelephoneRegionQueryRequest;
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
 * Date: 14-03-04
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/double-nine-service-context.xml"})
public class TelephoneRegionQueryRequestTest {
    private static final Logger log = LoggerFactory.getLogger(TelephoneRegionQueryRequestTest.class);

    private TelephoneRegionQueryRequest request;

    @Autowired
    SimpleHttpRequest simpleHttpRequest;
    @Autowired
    DoubleNineAgent agent;

    @Before
    public void setUp() throws Exception {
        request = new TelephoneRegionQueryRequest(agent,"13621391346");
    }

    @Test
    public void testTelephoneRegionQuery() throws Exception {
        TelephoneRegionResponse response = (TelephoneRegionResponse) simpleHttpRequest.getResponse(request);
        log.info("query products result is \n {}  .", response.toString());
        Assert.assertNotEquals("参数错误", response.getResult(), new Integer(1));
        Assert.assertNotEquals("签名验证失败", response.getResult(), new Integer(5));
    }
}
