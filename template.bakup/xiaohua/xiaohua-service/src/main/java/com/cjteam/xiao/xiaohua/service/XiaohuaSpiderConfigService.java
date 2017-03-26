package com.cjteam.xiao.xiaohua.service;


import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;

public interface XiaohuaSpiderConfigService {
    XiaohuaSpiderConfig get(int id) ;
    public void save(XiaohuaSpiderConfig xiaohuaSpiderConfig) ;
}