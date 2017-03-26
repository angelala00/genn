package com.cjteam.xiao.service.doublenine;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
@Component
public class SimpleHttpRequest {
    private static final Logger log = LoggerFactory.getLogger(SimpleHttpRequest.class);

    public SimpleHttpRequest() {
    }

    public DoubleNineResponse getResponseByGetMothd(DoubleNineRequest request) throws IOException, NoSuchAlgorithmException, JAXBException {
        HttpGet get = new HttpGet(request.getRequestURL());
        log.info("get url = {}", get.getURI());
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(get);
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        httpResponse.getEntity().writeTo(writer);
        String tmp = writer.toString();
        log.warn(tmp);
        return ((DoubleNineResponse) unMarshall(request.getUnMarshaller(), new StringReader(tmp)));
    }

    public DoubleNineResponse getResponse(DoubleNineRequest request) throws IOException, NoSuchAlgorithmException, JAXBException {
        HttpPost post = new HttpPost(request.getInterfaceApiURL());
        post.setEntity(request.getRequestEntity());
        HttpClient client = new DefaultHttpClient();
        HttpResponse httpResponse = client.execute(post);
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        httpResponse.getEntity().writeTo(writer);
        return ((DoubleNineResponse) unMarshall(request.getUnMarshaller(), new StringReader(writer.toString())));
    }

    protected Object unMarshall(Unmarshaller unmarshaller, Reader reader) throws JAXBException {
        return unmarshaller.unmarshal(reader);
    }
}
