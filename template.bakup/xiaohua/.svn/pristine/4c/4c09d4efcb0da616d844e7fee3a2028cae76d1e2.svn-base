package doublenine;


import org.apache.commons.io.IOUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 13-9-17
 */
public class RechargeResponseBindTest {
    private static final Logger log = LoggerFactory.getLogger(RechargeResponseBindTest.class);

    @Test
    public void rechargeResponseXmlBind() throws JAXBException, IOException {
        JAXBContext jc = JAXBContext.newInstance(RechargeResponse.class);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        String rechargeResponseXml = IOUtils.toString(getClass().getResource("RechargeResponse.xml"), "UTF-8");
        log.info("recharge reponse xml format value is :\n{}", rechargeResponseXml);

        RechargeResponse rechargeResponse = (RechargeResponse) unmarshaller.unmarshal(getClass().getResource("RechargeResponse.xml"));
        log.info("unmarshaller result is :\n{}", rechargeResponse.toString());

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter msg = new StringWriter();
        marshaller.marshal(rechargeResponse, msg);
        log.info("marshal an object to xml result is :\n{}", msg.toString());
    }

    String key = "6KEq6BoB9KeKXJVgqpHM35Z6qMTltqYS8wPnuBsDRexsjND3mviOjtyOv0ZD6mq6";
    String partner = "11908";
    String interfaceAPI = "http://api.99dou.com/mobile/direct.aspx/";

    Map<String, String> params = new LinkedHashMap<String, String>();

    @Before
    public void setUp() {
        params.put("partner", partner);
        params.put("out_trade_id", "100sdf1");
        params.put("mobile", "13621391346");
        params.put("value", "10");
        params.put("type", "0");
        params.put("notify_url", "");
    }

    @Test
    public void rechargeInterfaceTest() throws NoSuchAlgorithmException, IOException {
        HttpPost post = new HttpPost(interfaceAPI);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        fillNvps(nvps);
        nvps.add(new BasicNameValuePair("sign", getMD5Sign()));
        log.info("param size : {}", nvps.size());
        post.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        HttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(post);
        response.getEntity().writeTo(System.out);
    }

    private void fillNvps(List<NameValuePair> nvps) {
        for (String key : params.keySet()) {
            nvps.add(new BasicNameValuePair(key, params.get(key)));
        }
    }

    public String getMD5Sign() throws NoSuchAlgorithmException, IOException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();
        for (String key : params.keySet()) {
            sb.append(key).append("=").append(params.get(key)).append("&");
        }
        sb.append(key);
        log.info("pack result for sign:\n{}",sb.toString());
        byte[] md5Array = md5.digest(sb.toString().getBytes("UTF-8"));
        return bytesToHex(md5Array);
    }


    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
