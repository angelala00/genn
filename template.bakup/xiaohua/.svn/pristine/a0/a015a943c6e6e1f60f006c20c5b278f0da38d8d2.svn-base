package doublenine.qb;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.SimpleHttpRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QBFillResultQueryRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QBFillResultQueryResponse;
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
public class QBFillResultQueryRequestTest {
    private static final Logger log = LoggerFactory.getLogger(QBFillResultQueryRequestTest.class);

    private QBFillResultQueryRequest request;
    @Autowired
    DoubleNineAgent agent;
    @Autowired
    SimpleHttpRequest simpleHttpRequest;

    @Before
    public void setUp() throws Exception {
        request = new QBFillResultQueryRequest(agent);

        request.setOutTradeId("100101X");
    }

    @Test
    public void testFillResultQuery() throws Exception {
        QBFillResultQueryResponse response = (QBFillResultQueryResponse) simpleHttpRequest.getResponse(request);
        log.info("query products result is \n {}  .", response.toString());
        Assert.assertNotEquals("参数错误",response.getResult(), new Integer(1));
        Assert.assertNotEquals("签名验证失败",response.getResult(), new Integer(5));
    }
}
