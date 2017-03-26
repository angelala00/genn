package com.cjteam.xiao.xiaohua.spider;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;
import com.cjteam.xiao.xiaohua.repository.XiaohuaSpiderConfigRepository;
import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderConfigService;

/**
 * Created by chenlong on 2014/8/28.
 */
@Service
public class TaskImpl implements  Task  {
	public static enum  SpiderConfig {
		ZOL(1) , ZOL_PIC(2), PENGFU(4) ,PENGFU_PIC(5) ;
		public int xiaohuaSpiderConfigId ; 
		SpiderConfig(int xiaohuaSpiderConfigId){
			this.xiaohuaSpiderConfigId = xiaohuaSpiderConfigId ; 
		}
	}
    @Autowired
    private XiaohuaSpiderConfigService xiaohuaSpiderConfigService ;
    @Autowired
    private SpiderBase spiderBase;
    public Map<TaskImpl.SpiderConfig, Spider> spiderMap = new HashMap<TaskImpl.SpiderConfig, Spider> () ; 
    @Resource(name="zolSpiderImpl")
    public void setZolSpiderImpl(Spider spider){
    	this.spiderMap.put(TaskImpl.SpiderConfig.ZOL,spider) ; 
    }
    @Resource(name="zolPicSpiderImpl")
    public void setZolPicSpiderImpl(Spider spider){
    	this.spiderMap.put(TaskImpl.SpiderConfig.ZOL_PIC,spider) ; 
    }
    @Resource(name="pengfuSpiderImpl")
    public void setPengfuSpiderImpl(Spider spider){
    	this.spiderMap.put(TaskImpl.SpiderConfig.PENGFU,spider) ; 
    }
    @Resource(name="pengfuPicSpiderImpl")
    public void setPengfuPicSpiderImpl(Spider spider){
    	this.spiderMap.put(TaskImpl.SpiderConfig.PENGFU_PIC,spider) ; 
    }
    @Autowired
    private XiaohuaSpiderConfigRepository xiaohuaSpiderConfigRepository ;
    public void spider(TaskImpl.SpiderConfig spiderConfig){
        XiaohuaSpiderConfig xiaohuaSpiderConfig = xiaohuaSpiderConfigService.get(spiderConfig.xiaohuaSpiderConfigId) ;
        
        if (xiaohuaSpiderConfig.getPageCurrent().equals(xiaohuaSpiderConfig.getPageTotal())){
        	xiaohuaSpiderConfig.setPageCurrent(0);
        }
        xiaohuaSpiderConfig.setStartTime(new Date());
        this.spiderBase.setXiaohuaSpiderConfig(xiaohuaSpiderConfig);
        Spider spider = this.spiderMap.get(spiderConfig) ; 
        this.spiderBase.setSpider(spider);
        Thread t = new Thread(this.spiderBase , spiderConfig.toString());
      	t.start(); 
    }
  
}
