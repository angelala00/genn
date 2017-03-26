package com.cjteam.xiao.xiaohua.spider;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

/**
 * Created by chenlong on 2014/8/17.
 */
@Service("zolPicSpiderImpl")
public class ZolPicSpiderImpl extends ZolSpiderImpl implements Spider  {
    public String getContent(Document document){
        return  "";
    }
    public String getTargetId(Document document){
        return document.select(".article-commentbar").attr("data-id") ;
    }
    public String getPic(Document document)  {
        String picUrl =document.select(".article-text img").attr("src") ;
        String picString  =  this.getPicBase(picUrl) ;
        return picString ;
    }
}
