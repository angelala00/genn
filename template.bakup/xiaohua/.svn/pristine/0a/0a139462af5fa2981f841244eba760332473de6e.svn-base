package controller;

import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.UserService;
import junit.framework.TestCase;
import org.apache.commons.codec.digest.DigestUtils;
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
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.*;

/**
 * User: wuyimin
 * Date: 13-11-2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
public class IntegralControllerTest  extends TestCase{
    private static final Logger log = LoggerFactory.getLogger(IntegralControllerTest.class);

    private String userId="b8e86f7f68dba04329c65ed2cb76de4f";
    private String appId;

    @Test
    public void testTapjoyIntegral() throws IOException {
//        HttpPost post = new HttpPost("http://ios.dzye.net:81/score/records");
        userId = "b8e86f7f68dba04329c65ed2cb76de4f";
        HttpPost post = new HttpPost("http://localhost:82/scorecallback/tapjoy");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getTapjoyParams();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }

        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);
    }

    @Test
    public void testIntegralRecords() throws IOException {
//        HttpPost post = new HttpPost("http://ios.dzye.net:81/score/records");
        HttpPost post = new HttpPost("http://127.0.0.1:8080/score/records");
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

    @Test
    public void testExchangeRecords() throws IOException {
        HttpPost post = new HttpPost("http://ios.dzye.net:81/exchange/records");
//        HttpPost post = new HttpPost("http://127.0.0.1:8080/exchange/records");
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

    @Test
    public void testDzyeIntegral() throws IOException {
        HttpPost post = new HttpPost("http://ios.dzye.net:81/scorecallback/dzye");
//        HttpPost post = new HttpPost("http://127.0.0.1:8080/exchange/records");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getKeyValuePairDzye();
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
        params.put("userId", "b8e86f7f68dba04329c65ed2cb76de4f");
        params.put("mac", "B1D4B5AC-C4CC-4053-9656-4C6B61E3352C");
        params.put("token", "89bcf3e5d219b4e067a34af9b78a9b033aecc1dc94a0159639a621cfd7a3c1d1");
        params.put("openUdid", "063c28926bc640ac61a2247c018c4292106255b6");
        return params;
    }

    public Map<String, Object> getKeyValuePairDzye() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("point", "10");
        params.put("orderid", "67958-C4CC-4053-9656-4C6B61E3352C");
        params.put("userid", "b8e86f7f68dba04329c65ed2cb76de4f");
        return params;
    }

    public Map<String, Object> getTapjoyParams() {
        User user = userService.getOne(appId,userId);
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", user.getUserId());
        params.put("mac", user.getMac());
        params.put("token", user.getOpenUdid());
        params.put("openUdid", user.getToken());
        params.put("score", 100);
        params.put("timestamp", new Date().getTime());
        String source= "yifa(0*6_fahj+fal*fa918mikn" + user.getSce() + user.getUserId() + params.get("timestamp") + params.get("score") + "yifa(0*6_fahj+fal*fa918mikn";
        log.info("digest source {}",source);
        params.put("sign", DigestUtils.md5Hex(source));
        log.info("user info is {} ",user);
        return params;
    }

    @Test
    public void testTapjoyIntegral2() throws IOException {
//        HttpPost post = new HttpPost("http://ios.dzye.net:81/score/records");
        userId = "b8e86f7f68dba04329c65ed2cb76de4f";
        HttpPost post = new HttpPost("http://ios.dzye.net:81/scorecallback/tapjoy");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getTapjoyParams2();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }

        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);
    }

    public Map<String, Object> getTapjoyParams2() {
        User user = userService.getOne(appId,userId);
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", user.getUserId());
        params.put("mac", user.getMac());
        params.put("token", user.getOpenUdid());
        params.put("openUdid", user.getToken());
        params.put("score", 1);
        params.put("timestamp", new Date().getTime());
        String source= "yifa(0*6_fahj+fal*fa918mikn" + "21385217664238" + user.getUserId() + params.get("timestamp") + params.get("score") + "yifa(0*6_fahj+fal*fa918mikn";
        log.info("digest source {}",source);
        params.put("sign", DigestUtils.md5Hex(source));
        log.info("user info is {} ",user);
        return params;
    }

    @Test
    public void sign(){
        //scorecallback/tapjoy?
        // userid=ee1d01b622375661c28ed9be72e90c74&
        // mac=206155A9-1154-4253-A1FA-BE725A22CC97&
        // token=229a58a89d3dcd7d6ce620404c4339130abcbe24&
        // openUdid=3fb1d9e9e8d4106e62abcb52129a2875cb67a6f1b1ab7debc7bd4c1f90364a42&
        // score=0&
        // timestamp=1385353521&
        // sign=84a0677b69455186f4a904138129e403
        String actual = "6fbfbda668b14fe73fbc9a3b5e0636ca";
        User user = userService.getOne(appId,userId);
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", "ee1d01b622375661c28ed9be72e90c74");
        params.put("mac", "206155A9-1154-4253-A1FA-BE725A22CC97");
        params.put("openUdid", "229a58a89d3dcd7d6ce620404c4339130abcbe24");
        params.put("token", "3fb1d9e9e8d4106e62abcb52129a2875cb67a6f1b1ab7debc7bd4c1f90364a42");
        params.put("score", 0);
        params.put("timestamp", "1385353521");
        String source= "yifa(0*6_fahj+fal*fa918mikn"  +"11385351355214"+ user.getUserId() + params.get("timestamp") + params.get("score") + "yifa(0*6_fahj+fal*fa918mikn";
        String excepted = DigestUtils.md5Hex(source);
        log.info(excepted);
        assertEquals(excepted, actual);

    }

    @Test
    public void testMd5Diggest(){
        log.info(DigestUtils.md5Hex("(null)" + "(null)" + "(null)" + "yxiyTsjflayiyHttqlfhlkh"));
    }

    @Autowired
    UserService userService;
}
