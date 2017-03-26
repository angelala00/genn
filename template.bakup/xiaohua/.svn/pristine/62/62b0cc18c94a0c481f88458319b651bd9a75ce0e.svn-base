package com.cjteam.xiao.xiaohua.service;


import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.service.BasicService;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.xiaohua.model.XiaohuaContent;

import org.springframework.data.domain.Page;

public interface XiaohuaContentService extends BasicService<XiaohuaContent> {
    XiaohuaContent getByTitle(String title) ;
    public XiaohuaContent getByTargetIdAndXiaohuaSpiderConfigId(String targetId ,int id ) ; 
    void save(XiaohuaContent xiaohuaContent) ;
    Page<XiaohuaContent> getByType(String type ,  PageBasicInfo pageBasicInfo) ;
    public void clickGood(int id  , String userId) ;
    void clickBad(int id) ;
    public Page<XiaohuaContent> getByTitle(String title , PageBasicInfo pageBasicInfo) ;      
}