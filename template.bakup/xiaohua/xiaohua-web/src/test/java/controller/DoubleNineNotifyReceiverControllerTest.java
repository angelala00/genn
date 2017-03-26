package controller;

import com.cjteam.xiao.model.DoubleNineAgent;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wuyimin
 * Date: 13-11-5
 */
public class DoubleNineNotifyReceiverControllerTest {

    @Test
    public void testPost() throws Exception {
        HttpPost post = new HttpPost("http://127.0.0.1:8080/dn/charge-notify");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getKeyValuePair();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }
        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);
    }

    private Map<String, Object> getKeyValuePair() throws IOException, NoSuchAlgorithmException {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("partner", "");
        params.put("result", "0");
        params.put("out_trade_id", "tradeX5");
        params.put("state", DoubleNineAgent.STATE_FAIL);
        params.put("account", "416239820");
        params.put("product_id", "123");
        params.put("quantity", "1");
        params.put("price", "100");
        params.put("product_info", "P4");
        params.put("sign", getSIgn(params));
        return params;
    }

    private String getSIgn(Map<String, Object> param) throws IOException, NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
          for(String key:param.keySet()){
              sb.append(key).append("=").append(param.get(key)).append("&");
          }
        sb.append("");
        return DoubleNineAgent.md5Digest(sb.toString());
    }
}
