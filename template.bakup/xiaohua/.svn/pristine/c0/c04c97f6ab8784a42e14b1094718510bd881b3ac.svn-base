package com.cjteam.xiao.xiaohua.spider;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjteam.xiao.util.HttpTools;
import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;
import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderException;
import com.cjteam.xiao.xiaohua.service.XiaohuaContentService;
import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderConfigService;
import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderExceptionService;

@Service
public class SpiderBaseImpl implements SpiderBase {
    @Autowired
    private SpiderOnce spiderOnce ;
    @Autowired
    private HttpTools httpTools ;
    @Autowired
    private XiaohuaContentService xiaohuaContentService ;
    @Autowired
    private XiaohuaSpiderConfigService xiaohuaSpiderConfigService ; 
    @Autowired
    private XiaohuaSpiderExceptionService xiaohuaSpiderExceptionService ;
    private final static ThreadLocal<XiaohuaSpiderConfig> threadLocalXiaohuaSpiderConfig = new ThreadLocal<XiaohuaSpiderConfig>();
    private final static ThreadLocal<Spider> threadLocalSpider = new ThreadLocal<Spider>();
    private XiaohuaSpiderConfig xiaohuaSpiderConfig ;
    private Spider spider  ;
	public void setXiaohuaSpiderConfig(XiaohuaSpiderConfig xiaohuaSpiderConfig) {
		this.xiaohuaSpiderConfig = xiaohuaSpiderConfig ; 
	}
	public void setSpider(Spider spider) {
		this.spider = spider ; 
	}
	private XiaohuaSpiderConfig getXiaohuaSpiderConfig(){
		if (SpiderBaseImpl.threadLocalXiaohuaSpiderConfig.get()==null){
			SpiderBaseImpl.threadLocalXiaohuaSpiderConfig.set(this.xiaohuaSpiderConfig);
		}
		return SpiderBaseImpl.threadLocalXiaohuaSpiderConfig.get() ; 
	}
	private Spider getSpider(){
		if (SpiderBaseImpl.threadLocalSpider.get()==null){
			SpiderBaseImpl.threadLocalSpider.set(this.spider);
		}
		return SpiderBaseImpl.threadLocalSpider.get() ;
	}
	@Transactional
    public void run(){
		XiaohuaSpiderConfig xiaohuaSpiderConfig = this.getXiaohuaSpiderConfig()   ;
		Spider spider=this.getSpider()  ; 
		spider.setXiaohuaSpiderConfig(xiaohuaSpiderConfig);
        try {
//            while (xiaohuaSpiderConfig.getPageCurrent() < xiaohuaSpiderConfig.getPageTotal()) {
//            }
            try {
            	this.spiderOnce.once( spider , xiaohuaSpiderConfig);
            } catch (Exception e) {
            	e.printStackTrace();
            }
            xiaohuaSpiderConfig.setEndTime(new Date());
            
        }
        catch (Exception e){
            e.printStackTrace();
        }finally{
        	 this.xiaohuaSpiderConfigSave(xiaohuaSpiderConfig);
        }
    }
    public String getContentFromUrlByGet(String url ,XiaohuaSpiderConfig xiaohuaSpiderConfig ){
        try {
            return this.httpTools.getContentFromUrlByGet(url) ;
        }
        catch (Exception e){
            XiaohuaSpiderException xiaohuaSpiderException = new XiaohuaSpiderException() ;
            xiaohuaSpiderException.setUrl(url);
            xiaohuaSpiderException.setXiaohuaSpiderConfig(xiaohuaSpiderConfig);
            xiaohuaSpiderExceptionService.save(xiaohuaSpiderException);
            return null ;
        }
    }
    public    void xiaohuaSpiderConfigSave(XiaohuaSpiderConfig xiaohuaSpiderConfig){
    		this.xiaohuaSpiderConfigService.save(xiaohuaSpiderConfig);
    }
}
