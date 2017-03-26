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
public class TopControllerTest {

    @Test
    public void testChargeMobile() throws IOException {
        HttpPost post = new HttpPost("http://ios.dzye.net:81/top/score");
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
       /* params.put("userId", "34cdf6b46615c4b1846d7233cf3eed41");
        params.put("mac", "A1E7180B-CDD5-4DD3-9E91-9AD642BF67D2");
        params.put("token", "2cb8c8c777df8f43abecfec9acf42a60988246107f8e31a4263b9b7c948e243b");
        params.put("openUdid", "2f1602b13f2ddd102792e8152ca5450c506b6331");*/
        return params;
    }
}
