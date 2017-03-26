package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.model.Exchange;
import com.cjteam.xiao.service.*;
import com.cjteam.xiao.service.doublenine.DoubleNineInterfaceException;
import com.cjteam.xiao.service.doublenine.SimpleHttpRequest;
import com.cjteam.xiao.service.doublenine.impl.mobile.DirectChargeRequest;
import com.cjteam.xiao.service.doublenine.impl.mobile.DirectChargeResponse;
import com.cjteam.xiao.service.doublenine.impl.mobile.TelephoneRegionQueryRequest;
import com.cjteam.xiao.service.doublenine.impl.mobile.TelephoneRegionResponse;
import com.cjteam.xiao.service.doublenine.impl.qb.QBFillRequest;
import com.cjteam.xiao.service.doublenine.impl.qb.QBFillResponse;
import com.cjteam.xiao.service.doublenine.model.DNMobileNotify;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
@Service
@Transactional
public class DoubleNineServiceImpl implements DoubleNineService {
    private static final Logger LOG = LoggerFactory.getLogger(DoubleNineServiceImpl.class);

    private String tradeIdPrefix = "tradeX";

    @Override
    public void chargeForMobile(Exchange exchange) throws DoubleNineInterfaceException {
        Assert.isTrue(userService.reduceUserScore(exchange.getUser(), exchange.getScoreConsumption()), "减积分失败");
        DirectChargeResponse response = doExchargeForMobile(exchange);
        if (DoubleNineAgent.RESULT_OK.equals(response.getResult())) {
            LOG.debug("user score reduced");
            exchange.setState(stateTypeService.getStateTypeByCode(DoubleNineAgent.STATE_DOING));
            exchangeService.save(exchange);
            LOG.debug("consumer exchange saved");
        } else {
            LOG.info("chargeForMobile response result is {} .consumer is {}, response is {} ", response.getResult(), exchange.getConsumerAccount(), response.toString());
            LOG.info("{},{},{}", exchange.getAppId(), doubleNineAgentService.getAgentPartner(exchange.getAppId()), doubleNineAgentService.getAgentKey(exchange.getAppId()));
            userService.rollbackExchangeConsumptionScore(exchange.getUser(), exchange.getScoreConsumption());
            throw new DoubleNineInterfaceException("charge for mobile.request error." + response.getResultMeaning(response.getResult()));
        }

    }

    private DirectChargeResponse doExchargeForMobile(Exchange exchange) throws DoubleNineInterfaceException{
        try {
            DirectChargeRequest request = new DirectChargeRequest(doubleNineAgentService.getAgent(exchange.getAppId()));
            request.setOutTradeId(doubleNineAgentService.getTradePrefix(exchange.getAppId()) + exchange.getUser().getId() + System.currentTimeMillis());
            request.setMobile(exchange.getConsumerAccount());
            request.setValue(exchange.getMoneyExchange());
            request.setType(Exchange.ExchangeType.QUICK.getCode());
            request.setNotifyUrl(doubleNineAgentService.getNotifyURL(exchange.getAppId()));

            DirectChargeResponse response = (DirectChargeResponse) simpleHttpRequest.getResponse(request);
            Assert.notNull(response, "interface for mobile charge occur an error");
            return response;
        } catch (Throwable e) {
            LOG.error("charge for mobile occur an error.");
            userService.rollbackExchangeConsumptionScore(exchange.getUser(), exchange.getScoreConsumption());
            throw new DoubleNineInterfaceException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void chargeForQb(Exchange exchange) throws DoubleNineInterfaceException {
        Assert.isTrue(userService.reduceUserScore(exchange.getUser(), exchange.getScoreConsumption()), "减积分失败");
        QBFillResponse response = doExchangeForQQ(exchange);
        if (DoubleNineAgent.RESULT_OK.equals(response.getResult())) {
            LOG.debug("user score reduced");
            exchange.setState(stateTypeService.getStateTypeByCode(DoubleNineAgent.STATE_DOING));
            exchangeService.save(exchange);
            LOG.debug("consumer exchange saved");
        } else {
            LOG.info("chargeForMobile response result is {} .consumer is {}, response is {} ", response.getResult(), exchange.getConsumerAccount(), response.toString());
            userService.rollbackExchangeConsumptionScore(exchange.getUser(), exchange.getScoreConsumption());
            throw new DoubleNineInterfaceException("charge for mobile.request error." + response.getResultMeaning(response.getResult()));
        }
    }

    private QBFillResponse doExchangeForQQ(Exchange exchange) throws DoubleNineInterfaceException {
        try {
            QBFillRequest request = new QBFillRequest(doubleNineAgentService.getAgent(exchange.getAppId()));
            request.setOutTradeId(doubleNineAgentService.getTradePrefix(exchange.getAppId()) + exchange.getUser().getId() + System.currentTimeMillis());
            request.setAccount(exchange.getConsumerAccount());
            request.setProductId(exchange.getProduct().getDnProduct().getDnId());
            request.setNotifyUrl(doubleNineAgentService.getNotifyURL(exchange.getAppId()));
            request.setQuantity(exchange.getProduct().getQuantity());

            QBFillResponse response = (QBFillResponse) simpleHttpRequest.getResponse(request);
            Assert.notNull(response, "interface for mobile charge occur an error");
            return response;
        } catch (Throwable e) {
            LOG.error("charge for mobile occur an error.");
            userService.rollbackExchangeConsumptionScore(exchange.getUser(), exchange.getScoreConsumption());
            throw new DoubleNineInterfaceException("charge for mobile occur an error\n" + e.getLocalizedMessage(), e);
        }
    }

    @Override
    public void progressChargeResult(String appId,DNMobileNotify notify) throws DoubleNineInterfaceException, IOException, NoSuchAlgorithmException {
        Assert.isTrue(notifyIsValid(appId,notify), "notify request params is invalid");
        LOG.debug("notify information are valid");
        processNotify(notify);
    }

    private void processNotify(DNMobileNotify notify) {
        Exchange exchange = exchangeService.getById(unParseTradeId(notify.getOut_trade_id()));
        if (null != exchange && exchange.getConsumerAccount().equals(notify.getAccount()) && DNMobileNotify.RESULT_OK.equals(notify.getResult())) {
            LOG.info("charge notify check success ");
            if (DNMobileNotify.STATE_OK.equals(notify.getState())) {
                LOG.info("charge for mobile {} success", notify.getAccount());
                exchangeSuccess(exchange);
            } else if (DNMobileNotify.STATE_DOING.equals(notify.getState())) {
                LOG.info("charge for mobile {} is doing now", notify.getAccount());
            } else if (DNMobileNotify.STATE_FAIL.equals(notify.getState())) {
                LOG.info("charge for mobile {} war failed", notify.getAccount());
                rollbackExchange(exchange,DNMobileNotify.STATE_FAIL);
            } else if (DNMobileNotify.STATE_RETURN.equals(notify.getState())) {
                LOG.info("charge for mobile {} war returned", notify.getAccount());
                rollbackExchange(exchange,DNMobileNotify.STATE_RETURN);
            } else {
                LOG.info("unsupported state  {} of charge  notify", notify.getState());
            }
        } else {
            LOG.debug("something not match for thi notify and exchange");
            LOG.debug("notify is {}", notify);
            LOG.debug("exchange is {} ", exchange);
        }
    }

    private void exchangeSuccess(Exchange exchange) {
        updateExchangeState(exchange, DNMobileNotify.STATE_OK);
    }

    private void rollbackExchange(Exchange exchange, String state) {
        userService.rollbackExchangeConsumptionScore(exchange.getUser(), exchange.getScoreConsumption());
        updateExchangeState(exchange, state);
    }

    private void updateExchangeState(Exchange exchange, String state) {
        exchange.setState(stateTypeService.getStateTypeByCode(state));
        exchange.setProcessingTime(new Date());
        exchangeService.save(exchange);
    }

    private Integer unParseTradeId(String outTradeId) {
        if (StringUtils.isNotBlank(outTradeId)) {
            String exchangeIdStr = outTradeId.substring(tradeIdPrefix.length());
            if (StringUtils.isNumeric(exchangeIdStr)) {
                return Integer.valueOf(exchangeIdStr);
            }
        }
        return null;
    }

    private boolean notifyIsValid(String appId,DNMobileNotify notify) throws IOException, NoSuchAlgorithmException {
        return notify != null && StringUtils.equals(doubleNineAgentService.getAgentPartner(appId), notify.getPartner()) && signIsValid(appId,notify);
    }

    private boolean signIsValid(String appId,DNMobileNotify notify) throws IOException, NoSuchAlgorithmException {
        StringBuilder sb = new StringBuilder();
        sb.append("partner=").append(doubleNineAgentService.getAgentPartner(appId)).append("&");
        sb.append("result=").append(notify.getResult()).append("&");
        sb.append("out_trade_id=").append(notify.getOut_trade_id()).append("&");
        sb.append("state=").append(notify.getState()).append("&");
        sb.append("account=").append(notify.getAccount()).append("&");
        sb.append("product_id=").append(notify.getProduct_id()).append("&");
        sb.append("quantity=").append(notify.getQuantity()).append("&");
        sb.append("price=").append(notify.getPrice()).append("&");
        sb.append("product_info=").append(notify.getProduct_info()).append("&");
        sb.append(doubleNineAgentService.getAgentKey(appId));
        String signCheck =   DoubleNineAgent.md5Digest(sb.toString());
        LOG.debug("notify sign result {} ", signCheck);
        LOG.debug("notify sign is {} ", notify.getSign());
        return StringUtils.equals(signCheck, notify.getSign());
    }

    @Override
    public TelephoneRegionResponse queryRegion(String appId,String mobile) throws NoSuchAlgorithmException, JAXBException, IOException {
        if(StringUtils.isBlank(mobile))
            return null;
        return  (TelephoneRegionResponse) simpleHttpRequest.getResponse(new TelephoneRegionQueryRequest(doubleNineAgentService.getAgent(appId),mobile));
    }

    @Autowired
    private SimpleHttpRequest simpleHttpRequest;
    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;
    @Autowired
    private StateTypeService stateTypeService;
    @Autowired
    private DoubleNineAgentService doubleNineAgentService;
}
