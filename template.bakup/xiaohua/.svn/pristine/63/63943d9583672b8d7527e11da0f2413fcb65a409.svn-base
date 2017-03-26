package com.cjteam.xiao.xiaohua.service.impl;

import com.cjteam.xiao.xiaohua.model.XiaohuaContent;
import com.cjteam.xiao.xiaohua.model.XiaohuaSpiderConfig;
import com.cjteam.xiao.xiaohua.repository.XiaohuaContentRepository;
import com.cjteam.xiao.xiaohua.repository.XiaohuaSpiderConfigRepository;
import com.cjteam.xiao.xiaohua.service.XiaohuaContentService;
import com.cjteam.xiao.xiaohua.service.XiaohuaSpiderConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;

/**
 * Created by chenlong on 2014/7/23.
 */
@Service
public class XiaohuaSpiderConfigServiceImpl implements XiaohuaSpiderConfigService {
    @Autowired
    private XiaohuaSpiderConfigRepository xiaohuaSpiderConfigRepository ;
    @Transactional
    public void save(XiaohuaSpiderConfig xiaohuaSpiderConfig){
        this.xiaohuaSpiderConfigRepository.save(xiaohuaSpiderConfig) ;
    }

    @Transactional
    public XiaohuaSpiderConfig get(int id){
        XiaohuaSpiderConfig xiaohuaSpiderConfig = null ;
        try {
             xiaohuaSpiderConfig= this.xiaohuaSpiderConfigRepository.findOne( id);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return xiaohuaSpiderConfig ;
    }
}
