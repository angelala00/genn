package com.cjteam.xiao.duiba.service;

import cn.com.duiba.credits.sdk.*;
import com.cjteam.xiao.duiba.model.DuibaCreditConsume;

/**
 * Created by ChenLong on 2014/7/9.
 */
public interface DuiBaCreditService {
    /**
     * 构建兑吧的tool工具，如果找不到 appId对应的兑吧账户，则抛出异常；
     *
     * @param appId
     * @return
     */
    CreditTool getCreditTool(String appId);

    /**
     * 构建 客户端使用的针对用户的 免登录兑换页面的URL
     *
     * @param appId
     * @param userId
     * @return
     */
    String buildCreditAutoLoginRequest(String appId, String userId);

    /**
     * 查询用户剩余积分
     *
     * @param appId
     * @param queryParams @return
     */
    CreditQueryResult credit(String appId, CreditQueryParams queryParams);

    /**
     * 积分消费
     *
     *
     * @param appId
     * @param consumeParams
     * @return
     */
    CreditConsumeResult creditConsume(String appId, CreditConsumeParams consumeParams);

    void saveOrUpdateConsume(DuibaCreditConsume creditConsume);

    /**
     * 消费处理结果
     *
     * @param appId
     * @param notifyParams
     */
    void parseCreditResult(String appId, CreditNotifyParams notifyParams);
}
