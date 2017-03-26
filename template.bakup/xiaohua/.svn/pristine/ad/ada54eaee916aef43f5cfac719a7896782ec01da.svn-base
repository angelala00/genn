package com.cjteam.xiao.xiaohua.spider;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service("pengfuSpiderImpl")
public class PengfuSpiderImpl extends SpiderImpl implements Spider {
	@Override
	public String getEachPageUrl() {
		  return  xiaohuaSpiderConfig.getUrl() + this.getPage() + ".html" ;
	}
	@Override
	public Elements getElements(Document document) {
		return  document.select(".contFont");
	}

	@Override
	public String getTitle(Document document) {
		return document.select(".tieTitle a").eq(0).html();
	}

	@Override
	public String getContentUrl(Document document) {
		String urlString =  "http://www.pengfu.com/"+document.select(".tieTitle a").attr("href") ; 
		return urlString ;
	}
	@Override
	public String getContent(Document document){
		String contentString  = null; 
	    byte[] bytes;
	    ByteBuffer buffer ; 
		try {
			bytes = document.select(".imgbox").html().replace("<span class=\"watermark\">@捧腹网 </span>", "").getBytes("utf-8");
	        buffer   =     ByteBuffer.allocate(bytes.length);
	        int i = 0;
	        while (i < bytes.length) {
	            short b = bytes[i];
	            if (b > 0) {
	                buffer.put(bytes[i++]);
	                continue;
	            }
	            b += 256;
	            if ((b ^ 0xC0) >> 4 == 0) {
	                buffer.put(bytes, i, 2);
	                i += 2;
	            }
	            else if ((b ^ 0xE0) >> 4 == 0) {
	                buffer.put(bytes, i, 3);
	                i += 3;
	            }
	            else if ((b ^ 0xF0) >> 4 == 0) {
	                i += 4;
	            }
	        }
	        buffer.flip();
	        contentString = new String(buffer.array(), "utf-8") ;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return  contentString ; 
	}
	@Override
	public String getTargetId(Document document) {
		return  document.select("#postHumorId").attr("value");
	}

	@Override
	public String getPic(Document document) {
		return null;
	}

}
