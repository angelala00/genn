package com.cjteam.xiao.xiaohua.spider;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
@Service("pengfuPicSpiderImpl")
public class PengfuPicSpiderImpl extends PengfuSpiderImpl implements Spider {
	@Override
	public Elements getElements(Document document) {
		return  document.select(".pics");
	}

	@Override
	public String getTitle(Document document) {
		return document.select("dl dt a").attr("title") ;
	}

	@Override
	public String getContentUrl(Document document) {
		String url =  "http://www.pengfu.com"+document.select("dl dt a").attr("href") ; 
		return url ;
	}
	@Override
	public String getContent(Document document) {
		return  null;
	}
	@Override
	public String getPic(Document document) {
		String picUrl =document.select(".smallPic").attr("src") ;
		return this.getPicBase(picUrl) ; 
	}

}
