package com.cjteam.xiao.service.doublenine;

import com.cjteam.xiao.model.DoubleNineAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
public abstract class DoubleNineRequest {
    protected static String interfaceAPI = null;
    protected DoubleNineAgent agent;
    public String getPartner() {
        return agent.getPartner();
    }

    public String getKey() {
        return agent.getKey();
    }

    public DoubleNineAgent getAgent() {
        return agent;
    }

    public void setAgent(DoubleNineAgent agent) {
        this.agent = agent;
    }

    public abstract Map<String, Object> getKeyValuePair();

    public String getSign() throws NoSuchAlgorithmException, IOException {
        StringBuilder sb = new StringBuilder();
        Map<String, Object> tmp = getKeyValuePair();
        for (String key : tmp.keySet()) {
            if (StringUtils.equals("charset", key))
                continue;
            sb.append(key).append("=").append(tmp.get(key)).append("&");
        }
        sb.append(getKey());
        return DoubleNineAgent.md5Digest(sb.toString());
    }

    public UrlEncodedFormEntity getRequestEntity() throws IOException, NoSuchAlgorithmException {
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        Map<String, Object> tmp = getKeyValuePair();
        for (String key : tmp.keySet()) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(tmp.get(key))));
        }
        nvps.add(new BasicNameValuePair("sign", getSign()));
        return new UrlEncodedFormEntity(nvps, Consts.UTF_8);
    }

    public String getRequestURL() throws IOException, NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder(getInterfaceApiURL() + "?sign=" + getSign());
        Map<String, Object> tmp = getKeyValuePair();
        for (String key : tmp.keySet()) {
            sb.append("&").append(key).append("=").append(tmp.get(key));
        }
        return sb.toString();
    }

    public String getInterfaceApiURL() {
        return interfaceAPI;
    }

    protected JAXBContext jc;
    protected Marshaller marshaller;
    protected Unmarshaller unmarshaller;

    public Unmarshaller getUnMarshaller() {
        return unmarshaller;
    }
}
