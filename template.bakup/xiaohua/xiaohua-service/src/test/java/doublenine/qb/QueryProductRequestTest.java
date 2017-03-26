package doublenine.qb;

import com.cjteam.xiao.model.DnProduct;
import com.cjteam.xiao.repository.DnProductRepository;
import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.service.doublenine.SimpleHttpRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QueryProductRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QueryProductResponse;
import com.cjteam.xiao.service.doublenine.model.DoubleNineProduct;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/double-nine-service-context.xml", "/repository-context.xml"})
public class QueryProductRequestTest {
    private static final Logger log = LoggerFactory.getLogger(QueryProductRequestTest.class);

    @Autowired
    SimpleHttpRequest simpleHttpRequest;
    @Autowired
    DnProductRepository dnProductRepository;
    @Autowired
    DoubleNineAgent agent;

    @Test
    public void testQueryProducts() throws Exception {
        QueryProductRequest queryProduct = new QueryProductRequest(agent);
        queryProduct.setType(QueryProductRequest.QB);
        QueryProductResponse response = (QueryProductResponse) simpleHttpRequest.getResponse(queryProduct);
        log.info("query products result is \n {}  .", response.toString());
        List<DnProduct> dnProducts = new ArrayList<DnProduct>(response.getCount());
        for (DoubleNineProduct dnProduct : response.getProducts()) {
            DnProduct tmp = new DnProduct();
            tmp.setType(QueryProductRequest.QB);
            tmp.setDnId(dnProduct.getId());
            tmp.setName(dnProduct.getName());
            tmp.setInfo(dnProduct.getInfo());
            tmp.setPrice(dnProduct.getPrice());
            dnProducts.add(tmp);
        }
        dnProductRepository.save(dnProducts);

    }
}
