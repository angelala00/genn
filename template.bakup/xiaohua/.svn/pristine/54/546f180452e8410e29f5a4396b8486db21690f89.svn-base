package controller;

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

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wuyimin
 * Date: 13-10-30
 */
public class UserControllerTest {

    @Test
    public void testInitUser() throws IOException {
        HttpPost post = new HttpPost("http://ios.dzye.net:8080/user/init");
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
        params.put("mac", "YX272094FSJUX");
        params.put("openUdid", "208DF3A1JLS808DFASDSAFA");
        params.put("token", "TOKEN_420802AJ5LJLDFA");
        params.put("ip", "192.168.1.102");
        return params;
    }

    @Test
    public void testUpdateUser() throws IOException {
        HttpPost post = new HttpPost("http://ios.dzye.net:8080/user/update");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getKeyValuePairForUpdate();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }
        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);
    }

    public Map<String, Object> getKeyValuePairForUpdate() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("userId", "266b949b85b7c4beb1137963d16ff9bc");
        params.put("telphone", "19876523141");
        params.put("mac", "YX272094FSJUX");
        params.put("openUdid", "208DF3A1JLS808DFASDSAFA");
        params.put("token", "TOKEN_420802AJ5LJLDFA");
        params.put("newpsw", "TOKEN_42080AJLJLDFA1");
        return params;
    }


    @Test
    public void testUserInitLogic() throws IOException {
        creatUser();
        changeOneKey();
        changeTowKey();
        changeThreeKey();
    }

    private void changeThreeKey() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUser();
        tmp.put("mac", "YX272094FSJUXFDAFA-SAFDSFAD32fdsf2");
        tmp.put("openUdid", "208DF3A1JLS808DFASDSAFA322hjjs");
        tmp.put("token", "TOKEN_420802AJ5LJLDFAFHDHJFDjllldjdS");
        sendPost(tmp);
    }

    private void changeTowKey() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUser();
        tmp.put("mac", "YX272094FSJUXFDAFA-SAFDSFAD32232");
        tmp.put("openUdid", "208DF3A1JLS808DFASDSAFA32243");
        sendPost(tmp);
    }

    private void changeOneKey() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUser();
        tmp.put("mac", "YX272094FSJUXfdafa-safds");
        sendPost(tmp);
    }

    private void creatUser() throws IOException {
        sendPost(getKeyValuePairFirstTimeCreateUser());
    }

    private void sendPost(Map<String, Object> tmp) throws IOException {
        HttpPost post = new HttpPost("http://localhost:82/user/init");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }
        HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
        post.setEntity(entity);
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        httpResponse.getEntity().writeTo(System.out);
    }

    public Map<String, Object> getKeyValuePairFirstTimeCreateUser() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("mac", "YX272094FSJUXfdafa");
        params.put("openUdid", "208DF3A1JLS808DFASDSAFA");
        params.put("token", "TOKEN_420802AJ5LJLDFA");
        return params;
    }


    @Test
    public void testUserInitLogicV2() throws IOException {
        creatUserV2();
        changeOneKeyV2();
        changeTowKeyV2();
        changeThreeKeyV2();
    }

    private void changeThreeKeyV2() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUser();
        tmp.put("mac", "YX272094FSJUXFDAFA-SAFDSFAD32fdsf2");
        tmp.put("openUdid", "208DF3A1JLS808DFASDSAFA322hjjs");
        tmp.put("token", "TOKEN_420802AJ5LJLDFAFHDHJFDjllldjdS");
        addNewUserId(tmp);
        sendPost(tmp);
    }

    private void changeTowKeyV2() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUserV2();
        tmp.put("mac", "YX272094FSJUXFDAFA-SAFDSFAD32232");
        tmp.put("openUdid", "208DF3A1JLS808DFASDSAFA32243");
        addNewUserId(tmp);
        sendPost(tmp);
    }

    private void changeOneKeyV2() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUserV2();
        tmp.put("mac", "YX272094FSJUXfdafa-safds");
        addNewUserId(tmp);
        sendPost(tmp);
    }

    private void addNewUserId(Map<String, Object> tmp) {
        tmp.put("userId", DigestUtils.md5Hex(String.valueOf(tmp.get("mac")) + String.valueOf(tmp.get("openUdid")) + String.valueOf(tmp.get("token")) + "yxiyTsjflayiyHttqlfhlkh"));
    }

    private void creatUserV2() throws IOException {
        sendPost(getKeyValuePairFirstTimeCreateUserV2());
    }

    public Map<String, Object> getKeyValuePairFirstTimeCreateUserV2() {
        Map<String, Object> params = new LinkedHashMap<String, Object>(10);
        params.put("mac", "YX272094FSJUXfdafa");
        params.put("openUdid", "208DF3A1JLS808DFASDSAFA");
        params.put("token", "TOKEN_420802AJ5LJLDFA");
        return params;
    }

    @Test
    public void testUserInitLogicV3() throws IOException {
        creatUserV2();
        changeOneKeyV3();
        changeTowKeyV3();
        changeThreeKeyV3();
    }

    private void changeThreeKeyV3() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUser();
        addNewUserId(tmp);
        tmp.put("mac", "YX272094FSJUXFDAFA-SAFDSFAD32fdsf2");
        tmp.put("openUdid", "208DF3A1JLS808DFASDSAFA322hjjs");
        tmp.put("token", "TOKEN_420802AJ5LJLDFAFHDHJFDjllldjdS");
        sendPost(tmp);
    }

    private void changeTowKeyV3() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUserV2();
        addNewUserId(tmp);
        tmp.put("mac", "YX272094FSJUXFDAFA-SAFDSFAD32232");
        tmp.put("openUdid", "208DF3A1JLS808DFASDSAFA32243");
        sendPost(tmp);
    }

    private void changeOneKeyV3() throws IOException {
        Map<String, Object> tmp = getKeyValuePairFirstTimeCreateUserV2();
        addNewUserId(tmp);
        tmp.put("mac", "YX272094FSJUXfdafa-safds");
        sendPost(tmp);
    }
}
