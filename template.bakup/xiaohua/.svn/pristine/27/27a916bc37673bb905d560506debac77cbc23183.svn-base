package controller;

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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wuyimin
 * Date: 13-11-2
 */
public class ExchangeControllerTest {

    @Test
    public void testChargeMobile() throws IOException {
        HttpPost post = new HttpPost("http://127.0.0.1:8080/exchange/telphone");
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

    public Map<String, Object> getKeyValuePair() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", "62b1f4b41f711835dadc5cb5bab3460b");
        params.put("mac", "YX7209FSJUX");
        params.put("token", "TOKEN_42080AJLJLDFA");
        params.put("openUdid", "208DFAJLS808DFASDSAFA");
        params.put("telphone", "13621391346");
        params.put("amount", "100");
        params.put("cost", "100");
        params.put("product", "P4");
        return params;
    }


    @Test
    public void testChargeQB() throws IOException {
        HttpPost post = new HttpPost("http://127.0.0.1:8080/exchange/qb");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getQBKeyValuePair();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }

        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);

    }

    public Map<String,Object> getQBKeyValuePair() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", "62b1f4b41f711835dadc5cb5bab3460b");
        params.put("mac", "YX7209FSJUX");
        params.put("token", "TOKEN_42080AJLJLDFA");
        params.put("openUdid", "208DFAJLS808DFASDSAFA");

        params.put("qqNumber", "8991994");
        params.put("amount", "100");
        params.put("cost", "100");
        params.put("product", "P3");
        return params;
    }

    @Test
    public void testChargeAlipay() throws IOException {
        HttpPost post = new HttpPost("http://127.0.0.1:8080/exchange/alipay");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getAlipayKeyValuePair();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }

        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);

    }

    public Map<String,Object> getAlipayKeyValuePair() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", "62b1f4b41f711835dadc5cb5bab3460b");
        params.put("mac", "YX7209FSJUX");
        params.put("token", "TOKEN_42080AJLJLDFA");
        params.put("openUdid", "208DFAJLS808DFASDSAFA");

        params.put("alipayNo", "13621391346");
        params.put("amount", "100");
        params.put("cost", "100");
        params.put("product", "P5");
        return params;
    }
}
