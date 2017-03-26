package com.cjteam.xiao.xiaohua.spider;

import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;
import com.cjteam.xiao.xiaohua.service.XiaohuaContentService;
import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderConfigService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * Created by chenlong on 2014/8/28.
 */
@Service
public class SpiderOnceImpl implements  SpiderOnce {
    @Autowired
    private SpiderBase spiderBase;
    @Autowired
    private XiaohuaContentService xiaohuaContentService ;
    @Autowired
    private XiaohuaSpiderConfigService xiaohuaSpiderConfigService ;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void once( Spider spider , XiaohuaSpiderConfig xiaohuaSpiderConfig){
    	try {
	        String eachPageUrl;
	        String html;
	        Document document;
	        Elements elements;
	        eachPageUrl = spider.getEachPageUrl();
	        xiaohuaSpiderConfig.setPageCurrent(xiaohuaSpiderConfig.getPageCurrent() + 1);
	        html = this.spiderBase.getContentFromUrlByGet(eachPageUrl , xiaohuaSpiderConfig);
	        document = Jsoup.parse(html);
	        elements =  spider.getElements(document);
	        Document document1;
            XiaohuaContent xiaohuaContent;
            String title;
            String html1;
            String content;
            Document document2;
            String pic  = null ;
            String infoUrlString = null ;
            String infoHtmlString = null;
	        for (Element element : elements) {
	        	try {
	        		infoHtmlString = element.html(); 
		            document1 = Jsoup.parse(infoHtmlString);
		            title = spider.getTitle(document1);
		            xiaohuaContent = new XiaohuaContent();
		            xiaohuaContent.setTitle(title);
		            xiaohuaContent.setXiaohuaSpiderConfig(xiaohuaSpiderConfig);
		            infoUrlString = spider.getContentUrl(document1) ; 
		            html1 = this.spiderBase.getContentFromUrlByGet(infoUrlString, xiaohuaSpiderConfig);
		            if (html1 != null && !html1.equals("")) {
		                document2 = Jsoup.parse(html1);
		                content = spider.getContent(document2);
		                xiaohuaContent.setContent(content);
		                xiaohuaContent.setTargetId(spider.getTargetId(document2));
		                pic = spider.getPic(document2); 
		                xiaohuaContent.setPic(pic);
		                this.xiaohuaContentSave(xiaohuaContent);
		            }
	        	} catch (Exception e) {
	        		e.printStackTrace();
				}
	        }
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    	finally{
    		this.spiderBase.xiaohuaSpiderConfigSave(xiaohuaSpiderConfig);
    	}
    }
    public void xiaohuaContentSave(XiaohuaContent xiaohuaContent){
    	try {
    		String targetId = xiaohuaContent.getTargetId() ;
    		if(targetId==null  || "".equals(targetId)){
    			throw new Exception("targetId不能为空");
    		}
	    	if (this.xiaohuaContentService.getByTargetIdAndXiaohuaSpiderConfigId(xiaohuaContent.getTargetId() , xiaohuaContent.getXiaohuaSpiderConfig().getId())==null) { 
		        Random random = new Random();
		        xiaohuaContent.setBad(random.nextInt(1000)) ;
		        xiaohuaContent.setGood(random.nextInt(1000));
		        xiaohuaContent.setSort(0);
		        String pic  = xiaohuaContent.getPic() ; 
		        if (pic!=null && !"".equals(pic)){
		            xiaohuaContent.setType(XiaohuaContent.Type.image.toString());
		        }
				else{
					xiaohuaContent.setType(XiaohuaContent.Type.text.toString());
				}
		        this.xiaohuaContentService.save(xiaohuaContent);
	    	}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
