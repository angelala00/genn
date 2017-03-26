package doublenine;

import com.cjteam.xiao.service.doublenine.impl.qb.QueryProductResponse;
import com.cjteam.xiao.service.doublenine.model.DoubleNineProduct;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
public class ProductsResponseJXMTest {
    private static final Logger log = LoggerFactory.getLogger(ProductsResponseJXMTest.class);
    JAXBContext jc;
    Marshaller marshaller;
    Unmarshaller unmarshaller;
    QueryProductResponse queryProductResponse;
    DoubleNineProduct product;

    @Before
    public void setUp() throws Exception {
        jc = JAXBContext.newInstance(QueryProductResponse.class, DoubleNineProduct.class);
        marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        unmarshaller = jc.createUnmarshaller();

        createInstance();
    }

    private void createInstance() throws JAXBException {
        queryProductResponse = new QueryProductResponse();

        queryProductResponse.addProduct(createNewProduct("1000", "安徽移动100元快充", 9845, "运营商:移动;省份:安徽;面额:100"));
        queryProductResponse.addProduct(createNewProduct("1001", "北京移动100元快充", 9845, "运营商:移动;省份:北京;面额:100"));

        product = createNewProduct("1003", "北京移动100元快充", 9345, "运营商:移动;省份:北京;面额:100");
        queryProductResponse.addProduct(product);
    }

    private DoubleNineProduct createNewProduct(String id, String name, int price, String info) {
        return new DoubleNineProduct(id, name, price, info);
    }

    @Test
    public void testProductsResponseMarshaller() throws Exception {
        log.info("current product size is {}", queryProductResponse.getCount());
        marshaller.marshal(queryProductResponse, System.out);
    }

    @Test
    public void testProductsResponseUnMarshaller() throws Exception {
        queryProductResponse = (QueryProductResponse) unmarshaller.unmarshal(getClass().getResource("ProductsResponse.xml"));
        log.info("current product size is {}", queryProductResponse.getCount());
        for(DoubleNineProduct productTmp : queryProductResponse.getProducts()){
            log.info("product : {}",productTmp.toString());
        }
    }

    @Test
    public void testTimestamp2Date(){
        Date single = new Date(1383025800000L);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        System.out.println(df.format(single));
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE,5);
        System.out.println(df.format(calendar.getTime()));
        System.out.print(calendar.getTime().getTime());
    }
}
