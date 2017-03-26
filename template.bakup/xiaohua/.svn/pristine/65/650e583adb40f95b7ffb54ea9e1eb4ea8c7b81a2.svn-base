package com.cjteam.xiao.xiaohua.spider;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * Created by chenlong on 2014/8/17.
 */
@Service("zolSpiderImpl")
public class ZolSpiderImpl extends SpiderImpl implements Spider  {
    public String getEachPageUrl(){
        return  xiaohuaSpiderConfig.getUrl() + this.getPage() + ".html" ;
    }
    public Elements getElements(Document document){
        return  document.select(".article-summary");
    }
    public String getTitle(Document document){
        return  document.select(".article-title a").html() ;
    }
    public String getContentUrl(Document document){
        return  "http://xiaohua.zol.com.cn" + document.select(".all-read").attr("href") ;
    }
    public String getContent(Document document){
        return  document.select(".article-text").html().replaceAll("<span.*?>", "").replaceAll("</span>", "").replaceAll("<div.*?>", "").replaceAll("</div>", "").replaceAll("<em.*?>", "").replaceAll("</em>", "").replaceAll("<i.*?>", "").replaceAll("</i>", "").replaceAll("<a.*?>", "").replaceAll("</a>", "").replaceAll("/div>", "").replaceAll(" ", "");
    }
    public String getTargetId(Document document){
        return document.select(".article-commentbar").attr("data-id") ;
    }
    public String getPic(Document document)  {

        return "" ;
    }
}
