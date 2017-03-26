package com.cjteam.mrile.task;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.select.Elements;

public class HttpTools {
	public  String getContentFromUrlByPost(String url, Map<String, String> paramMap, Map<String, String> headerMap, String encode) {
		long d = System.currentTimeMillis();
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		if (MapUtils.isNotEmpty(paramMap)) {
			for (String key : paramMap.keySet()) {
				nameValuePairs.add(new BasicNameValuePair(key, paramMap.get(key)));
			}
		}
		if (MapUtils.isNotEmpty(headerMap)) {
			for (String key : headerMap.keySet()) {
				httpPost.setHeader(key,headerMap.get(key));
			}
		}
		System.out.println("param:"+nameValuePairs);
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String result = null;
		try {
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (StringUtils.isBlank(encode)) {
				encode = "UTF-8";
			}
			result = EntityUtils.toString(entity, encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("cost:"+(System.currentTimeMillis() - d));
		return result;
	}
	public   String getContentFromUrlByGet(String url, Map<String, String> headerMap, String encode) {
		HttpEntity entity = getHttpEntityContentFromUrlByGet(url,  headerMap);
		String result = null;
		try {
			if (StringUtils.isBlank(encode)) {
				encode = "UTF-8";
			}
			result = EntityUtils.toString(entity, encode);
		} catch (Exception e) {
			//System.out.println("出错了，跳过===");
			e.printStackTrace();
		}
		return result;
	}
    public String getContentFromUrlByGet(String url ,  Map<String, String> headerMap){
        return  this.getContentFromUrlByGet(url , headerMap , null) ;
    }
    public String getContentFromUrlByGet(String url){
        return this.getContentFromUrlByGet(url , null)  ;
    }
	public  byte[] getByteFromUrlByGet(String url, Map<String, String> paramMap, Map<String, String> headerMap, String encode) {
		HttpEntity entity = getHttpEntityContentFromUrlByGet(url,  headerMap);
		byte[] result = null;
		try {
			if (StringUtils.isBlank(encode)) {
				encode = "UTF-8";
			}
			result = EntityUtils.toByteArray(entity);
		} catch (Exception e) {
			System.out.println("出错了，跳过===");
			e.printStackTrace();
		}
		return result;
	}
	private  HttpEntity getHttpEntityContentFromUrlByGet(String url,
			Map<String, String> headerMap) {
		long d = System.currentTimeMillis();
		HttpGet httpGet = new HttpGet(url);
	
		if (MapUtils.isNotEmpty(headerMap)) {
			for (String key : headerMap.keySet()) {
				httpGet.setHeader(key,headerMap.get(key));
			}
		}
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = null;
		for (int i = 0;response == null&&i<3;i++) {
			response = dorequest(httpclient,httpGet);
			if (i > 0) {
				System.out.println("链接失败，重试"+i+"次");
			}
		}
		//System.out.println("cost:"+(System.currentTimeMillis() - d));
		return response.getEntity();
	}
	private  HttpResponse dorequest(DefaultHttpClient httpclient, HttpGet httpGet) {
		try {
			return httpclient.execute(httpGet);
		} catch (Exception e) {
			return null;
		}
	}
	public  void downloadFile(String url, String savePath) throws IOException{
		File file = new File(savePath);
		if (file.exists()) {
			System.out.println("文件已经存在，跳过");
			return;
		}
		byte[] data = getByteFromUrlByGet(url,null,null,null);
		FileUtils.writeByteArrayToFile(file , data);
	}
	public String[] elementsToString (Elements elements){
		int number= elements.size();
		String string [] = new String [number] ;  
		int i=0;
		while (i < number) {
			string[i] = elements.get(i).html();
			i++;
		}
		return string;
	}

    public  void download(String urlString, String filename,String savePath)   {
        // 构造URL
        URL url = null;
        try {
            url = new URL(urlString);
            // 打开连接
            URLConnection con = null;
            con = url.openConnection();
            //设置请求超时为5s
            con.setConnectTimeout(5 * 1000);
            // 输入流
            InputStream is = null;
            is = con.getInputStream();
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流
            File sf = new File(savePath);
            if (!sf.exists()) {
                sf.mkdirs();
            }
            OutputStream os = null;
            String newfilename = sf.getPath()+ "/"+filename ;
            os = new FileOutputStream( newfilename);
            File sf1 = new File(newfilename);
          //   开始读取
            System.out.println(newfilename);
            while ((len = is.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
            InputStream iis = new FileInputStream(newfilename);
            BufferedImage srcImg = null;
            srcImg = ImageIO.read(iis);

            String[] tmp  = filename.split("\\.");
            int newHeight   =(int)(srcImg.getHeight()/ (srcImg.getWidth()/310.00000000000000)) ;
            char tmpa = tmp[1].charAt(0) ;
            if (tmpa=='g' || tmpa=='G'){
            }else {
                BufferedImage buffImg = null;
                buffImg = new BufferedImage(310, newHeight, BufferedImage.TYPE_INT_RGB);
                buffImg.getGraphics().drawImage(srcImg.getScaledInstance(310, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
                ImageIO.write(buffImg, tmp[1], sf1);
            }


            // 完毕，关闭所有链接
            os.close();
            is.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
