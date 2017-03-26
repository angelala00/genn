package com.cjteam.xiao.duiba.service.impl;

import cn.com.duiba.credits.sdk.*;
import com.cjteam.xiao.duiba.model.AppDuibaAccountReference;
import com.cjteam.xiao.duiba.model.DuibaCreditConsume;
import com.cjteam.xiao.duiba.repository.AppDuibaAccountReferenceRposiroty;
import com.cjteam.xiao.duiba.repository.DuibaCreditConsumeRepository;
import com.cjteam.xiao.duiba.service.DuiBaCreditService;
import com.cjteam.xiao.model.ExchangeRecord;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.ExchangeRecordService;
import com.cjteam.xiao.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenLong on 2014/7/9.
 */
@Service
@Transactional
public class DuiBaCreditServiceImpl implements DuiBaCreditService {
    private static final Logger LOG = LoggerFactory.getLogger(DuiBaCreditServiceImpl.class);

    @Override
    public CreditTool getCreditTool(String appId) {
        CreditTool tool = creditToolCache.get(appId);
        if (tool == null) {
            AppDuibaAccountReference duibaAccountReference = referenceRposiroty.findByAppId(appId);
            creditToolCache.put(appId, new CreditTool(duibaAccountReference.getDuibaAccount().getAppKey(), duibaAccountReference.getDuibaAccount().getAppSecret()));
            tool = creditToolCache.get(appId);
        }
        if (tool == null) {
            throw new RuntimeException(appId + "\t 无法初始化兑吧的兑换帐号");
        }
        LOG.info(tool.toString());
        return tool;
    }

    @Override
    public String buildCreditAutoLoginRequest(String appId, String userId) {
        CreditTool tool = getCreditTool(appId);
        User user = userService.getOne(appId, userId);
        Assert.notNull(user, "用户不存在");
        return tool.buildCreditAutoLoginRequest(userId, user.getSurplus().intValue());
    }

    @Override
    public CreditQueryResult credit(String appId, CreditQueryParams queryParams) {
        User user = userService.getOne(appId, queryParams.getUid());
        Assert.notNull(user, "用户不存在");
        CreditQueryResult result = new CreditQueryResult(true, user.getSurplus().intValue());
        if (StringUtils.isNotBlank(user.getAlipayNo()))
            result.setAlipay(user.getAlipayNo());
        if (StringUtils.isNotBlank(user.getMobilePhone()))
            result.setPhone(user.getMobilePhone());
        if (StringUtils.isNotBlank(user.getQq()))
            result.setQq(user.getQq());
        return result;
    }
    @Resource
    public ExchangeRecordService exchangeRecordService;
    @Override
    public CreditConsumeResult creditConsume(String appId, CreditConsumeParams consumeParams) {
        CreditConsumeResult result;
        DuibaCreditConsume creditConsume = dozerBeanMapper.map(consumeParams, DuibaCreditConsume.class);
        try {
            saveOrUpdateConsume(creditConsume);

            User user = userService.getOne(appId, consumeParams.getUid());
            Assert.notNull(user, "用户不存在");
            Assert.isTrue(user.getSurplus() > consumeParams.getCredits(), "用户积分不够");
            Assert.isTrue(userService.consumeScore(user.getId(), consumeParams.getCredits()), "扣减用户积分失败");
            ExchangeRecord exchangeRecord = new ExchangeRecord() ;
            exchangeRecord.setContent(consumeParams.getDescription());
            exchangeRecord.setUser(user);
            exchangeRecord.setSurplus(consumeParams.getCredits());
            exchangeRecord.setType(consumeParams.getType());
            this.exchangeRecordService.save(exchangeRecord);
            creditConsume.setConsumeResult(Boolean.TRUE);

            result = new CreditConsumeResult(true);
            result.setBizId(String.valueOf(creditConsume.getId()));

        } catch (Throwable t) {
            LOG.error(t.getLocalizedMessage(), t);
            result = new CreditConsumeResult(false);
            result.setErrorMessage(t.getMessage());
            creditConsume.setConsumeResult(Boolean.FALSE);
            creditConsume.setConsumeMessage(t.getLocalizedMessage());
        }
        saveOrUpdateConsume(creditConsume);
        return result;
    }

    @Override
    public void parseCreditResult(String appId, CreditNotifyParams notifyParams) {
        DuibaCreditConsume creditConsume = consumeRepository.findOne(Long.valueOf(notifyParams.getBizId()));
        creditConsume.setCreditNotifyResult(notifyParams.isSuccess());
        creditConsume.setCreditNotifyTime(notifyParams.getTimestamp());
        if (!notifyParams.isSuccess()) {
            userService.consumeScore(appId, notifyParams.getUid(), 0 - creditConsume.getCredits());
            creditConsume.setCreditNotifyMessage(notifyParams.getErrorMessage());
        }
        saveOrUpdateConsume(creditConsume);
    }

    @Transactional(propagation = Propagation.NESTED)
    @Override
    public void saveOrUpdateConsume(DuibaCreditConsume creditConsume) {
        consumeRepository.save(creditConsume);
    }

    Map<String, CreditTool> creditToolCache = new HashMap<String, CreditTool>();

    @Autowired
    private AppDuibaAccountReferenceRposiroty referenceRposiroty;
    @Autowired
    private UserService userService;
    @Resource(name = "dozerBeanMapper")
    private Mapper dozerBeanMapper;
    @Autowired
    private DuibaCreditConsumeRepository consumeRepository;
}
