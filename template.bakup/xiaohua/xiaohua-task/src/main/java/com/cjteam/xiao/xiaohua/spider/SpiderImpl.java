package com.cjteam.xiao.xiaohua.spider;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.cjteam.xiao.util.HttpTools;
import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;

public class SpiderImpl {
    @Autowired
    public HttpTools httpTools ;
    @Value("${SpiderPICRootPath}")
    private String spiderPICRootPath ;  
    @Value("${SpiderPICPath}")
    private String spiderPICPath ;
    public  XiaohuaSpiderConfig xiaohuaSpiderConfig  ;
    public void setXiaohuaSpiderConfig(XiaohuaSpiderConfig xiaohuaSpiderConfig){
        this.xiaohuaSpiderConfig = xiaohuaSpiderConfig ;
    }
    public int getPage(){
    	return xiaohuaSpiderConfig.getPageTotal() - xiaohuaSpiderConfig.getPageCurrent() ; 
    }
    public String getPicBase(String picUrl){
        String[] tmp  = picUrl.split("\\.");
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHH");
        String  fileBase = this.spiderPICPath+formatter.format(currentTime) ;
        String  fileName = System.currentTimeMillis()+"." +tmp[tmp.length-1] ;
        this.httpTools.download(picUrl , fileName , this.spiderPICRootPath+fileBase);
        String picFilePath =   "/"+(fileBase+"/"+fileName).replaceAll("\\\\","/") ;
        return picFilePath ;
    }
}
