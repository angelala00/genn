package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.FrontApp;
import com.cjteam.xiao.repository.FrontAppRepository;
import com.cjteam.xiao.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenLong on 2014/5/6.
 */
@Service
@Transactional(readOnly = true)
public class AppServiceImpl implements AppService {
    private static final Map<String, FrontApp> appCachePool = new HashMap<String, FrontApp>();

    @Override
    public FrontApp getOne(String appId) {
        Assert.notNull(appId, "前端唯一标识码无效为空");
        Assert.hasText(appId, "前端唯一标识码无效为空");
        FrontApp one = null;
        if (!appCachePool.containsKey(appId)) {
           reloadAppInfo(appId);
        }
        one = appCachePool.get(appId);
        return one;
    }

    private void reloadAppInfo(String appId) {
        FrontApp one = appRepository.findById(appId);
        Assert.notNull(one, "代码 " + appId + " 对应的前端目前不支持");
        appCachePool.put(appId, one);
    }

    @Override
    public boolean onOnlineStatus(String appId) {
        FrontApp app = getOne(appId);
        return app != null && app.getStatus() == FrontApp.ONLINE;
    }

    @Override
    public boolean onJudgeStatus(String appId) {
        FrontApp app = getOne(appId);
        return app != null && app.getStatus() == FrontApp.JUDGE;
    }

    @Override
    public boolean onTestStatus(String appId) {
        FrontApp app = getOne(appId);
        return app != null && app.getStatus() == FrontApp.TEST;
    }

    @Override
    public boolean onAdTestStatus(String appId) {
        FrontApp app = getOne(appId);
        return app != null && app.getStatus() == FrontApp.ADTEST;
    }

    @Override
    public void cleanCache(String appId) {
        reloadAppInfo(appId);
    }

    @Autowired
    FrontAppRepository appRepository;
}
