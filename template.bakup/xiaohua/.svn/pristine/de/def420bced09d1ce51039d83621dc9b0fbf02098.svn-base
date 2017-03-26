package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.repository.DoubleNineAgentRepository;
import com.cjteam.xiao.service.DoubleNineAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenLong on 2014/5/5.
 */
@Service
@Transactional(readOnly = true)
public class DoubleNineAgentServiceImpl implements DoubleNineAgentService {
    private static final Map<String, DoubleNineAgent> appAgentsPool = new HashMap<String, DoubleNineAgent>();

    @Override
    public String getAgentPartner(String appId) {
        return getAgent(appId).getPartner();
    }

    @Override
    public String getAgentKey(String appId) {
        return getAgent(appId).getKey();
    }

    @Override
    public DoubleNineAgent getAgent(String appId) {
        if (!appAgentsPool.containsKey(appId)) {
            appAgentsPool.put(appId, loadAgentForApp(appId));
        }
        return appAgentsPool.get(appId);
    }

    private DoubleNineAgent loadAgentForApp(String appId) {
        DoubleNineAgent agent = doubleNineAgentRepository.findByAppId(appId);
        Assert.notNull(agent,appId +"的 久久平台partner信息未设置，不能提供手机充值和Q币兑换");
        return agent;
    }

    @Override
    public String getTradePrefix(String appId) {
        return getAgent(appId).getOrderIdPrefix();
    }

    @Override
    public String getNotifyURL(String appId) {
        return getAgent(appId).getNotifyUrl();
    }

    @Autowired
    private DoubleNineAgentRepository doubleNineAgentRepository;
}
