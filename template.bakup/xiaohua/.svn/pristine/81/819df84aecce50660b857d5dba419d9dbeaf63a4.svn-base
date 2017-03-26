package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.Exchange;
import com.cjteam.xiao.model.Product;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.ExchangeService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.service.impl.ExchangeSituation;
import com.cjteam.xiao.web.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: wuyimin Date: 13-10-8
 */
@Controller
@RequestMapping("/exchange")
public class ExchangeController extends BaseController<Exchange> {
    private static final Logger log = LoggerFactory.getLogger(ExchangeController.class);
    private static final Logger LOG_EXCHANGE = LoggerFactory.getLogger(ExchangeController.class.getName() + ".INTEGRAL");

    private static Map<String, String> pool = new HashMap<String, String>(100);

    @RequestMapping(value = {"/telphone"})
    public
    @ResponseBody
    ResponseVo telephoneCharge(@ModelAttribute User user,
                               @RequestParam(required = true) String telphone,
                               @RequestParam(required = true) String product,
                               HttpServletRequest request) {
        ResponseVo response = new ResponseVo();
        try {
            checkModelAttribute(user);
            onceExchangeAtOneTime(user);


            exchangeService.charge2telphone(user.getAppId(), user.getUserId(), product, telphone);
            response.setSuccess(Boolean.TRUE);

        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},chargeAccount:{}},{msg:{}}",
                    ExchangeSituation.FAILURE, ExchangeSituation.Type.MOBILE, DateTime.now().toString("yyyy-MM-dd"),
                    user.getUserId(), product, telphone,  throwable.getLocalizedMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        } finally {
            pool.remove(user.getAppId() + user.getUserId());
        }
        return response;
    }

    @RequestMapping(value = {"/alipay"})
    public
    @ResponseBody
    ResponseVo alipay(@ModelAttribute User user, @RequestParam(required = false) String alipayNo,
                       @RequestParam(required = true) String product,
                       HttpServletRequest request) {
        SaleResponseVo response = new SaleResponseVo();
        try {
            checkModelAttribute(user);
            onceExchangeAtOneTime(user);

            Product saleProduct = exchangeService.withdrawFromAlipay(user.getAppId(), user.getUserId(), product, alipayNo);
            response.setQuantity(saleProduct.getQuantity());
            response.setType(saleProduct.getProductType().getCode());

            response.setSuccess(Boolean.TRUE);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},chargeAccount:{},amount:{},cost:{}},{msg:{}}",
                    ExchangeSituation.FAILURE, ExchangeSituation.Type.ZHIFUBAO, DateTime.now().toString("yyyy-MM-dd"),
                    user.getUserId(), product, alipayNo, throwable.getLocalizedMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        } finally {
            pool.remove(user.getAppId() + user.getUserId());
        }
        return response;
    }

    @RequestMapping(value = {"/luckystar"})
    public
    @ResponseBody
    ResponseVo luckyStar(@ModelAttribute User user, @RequestParam(required = true) Integer score) {
        SaleResponseVo response = new SaleResponseVo();
        try {
            checkModelAttribute(user);
            onceExchangeAtOneTime(user);

            response.setQuantity(exchangeService.luckyStars(user.getAppId(), user.getUserId(), score));
            response.setType("ALIPAY");

            response.setSuccess(Boolean.TRUE);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},score:{}},{msg:{}}",
                    ExchangeSituation.FAILURE, ExchangeSituation.Type.ZHIFUBAO, DateTime.now().toString("yyyy-MM-dd"),
                    user.getUserId(), "luckyStars", score, throwable.getLocalizedMessage());
        } finally {
            pool.remove(user.getAppId() + user.getUserId());
        }
        return response;
    }

    @RequestMapping(value = {"/qb"})
    public
    @ResponseBody
    ResponseVo qbCharge(@ModelAttribute User user, @RequestParam(required = true) String qqNumber,
                        @RequestParam(required = true) String product,
                        HttpServletRequest request) {
        ResponseVo response = new ResponseVo();
        try {
            checkModelAttribute(user);
            onceExchangeAtOneTime(user);

            exchangeService.charge2Qb(user.getAppId(), user.getUserId(), product, qqNumber);
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},chargeAccount:{}},{msg:{}}",
                    ExchangeSituation.FAILURE, ExchangeSituation.Type.QB, DateTime.now().toString("yyyy-MM-dd"),
                    user.getUserId(), product, qqNumber,  throwable.getLocalizedMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        } finally {
            pool.remove(user.getAppId() + user.getUserId());
        }
        return response;
    }

    @RequestMapping(value = {"/records"})
    public
    @ResponseBody
    ExchangeRecordsResponse records(@ModelAttribute User user) {
        ExchangeRecordsResponse response = new ExchangeRecordsResponse();
        try {
            checkModelAttribute(user);

            Page<Exchange> records = exchangeService.getQBAndMobileChargeRecords(user.getAppId(), user.getUserId());

            if (records != null && CollectionUtils.isNotEmpty(records.getContent())) {
                response.setRecords(change(records.getContent()));
                response.setSuccess(Boolean.TRUE);
            } else {
                response.setSuccess(Boolean.FALSE);
                response.setMessage("您还没有兑换记录");
            }
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        }
        return response;
    }

    @RequestMapping(value = {"/sale"})
    public
    @ResponseBody
    ResponseVo sale(@ModelAttribute User user, @RequestParam(required = true) String product, @RequestParam(required = true, defaultValue = "1") Integer count) {
        ResponseVo response = new ResponseVo();
        try {
            checkModelAttribute(user);
            onceExchangeAtOneTime(user);

            exchangeService.saleProduct(user.getAppId(), user.getUserId(), product, count);
            response.setSuccess(Boolean.TRUE);
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},count:{}},{msg:{}}",
                    ExchangeSituation.FAILURE, ExchangeSituation.Type.SALE, DateTime.now().toString("yyyy-MM-dd"),
                    user.getUserId(), product, count, throwable.getLocalizedMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        } finally {
            pool.remove(user.getAppId() + user.getUserId());
        }
        return response;
    }

    @RequestMapping(value = "/consume")
    public  @ResponseBody
    ResponseVo  consumeProduct(@ModelAttribute User user, @RequestParam String product, @RequestParam(defaultValue = "1", required = true) Integer count) {
        ResponseVo response = new ResponseVo();
        try {
            checkModelAttribute(user);
            onceExchangeAtOneTime(user);

            exchangeService.consumeProduct(user.getAppId(),user.getUserId(),product,count);

            response.setSuccess(Boolean.TRUE);
        }catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},count:{}},{msg:{}}",
                    ExchangeSituation.FAILURE, ExchangeSituation.Type.CONSUME, DateTime.now().toString("yyyy-MM-dd"),
                    user.getUserId(), product, count, throwable.getLocalizedMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        } finally {
            pool.remove(user.getAppId() + user.getUserId());
        }
        return response;
    }


    private void onceExchangeAtOneTime(User user) {
        synchronized (pool) {
            Assert.isTrue(!pool.containsKey(user.getAppId() + user.getUserId()), "您兑换得也太快了吧亲");
            pool.put(user.getAppId() + user.getUserId(), user.getAppId() + user.getUserId());
        }
    }

    private List<ExchangeVo> change(List<Exchange> content) {
        List<ExchangeVo> s = new ArrayList<ExchangeVo>();
        for (Exchange c : content) {
            s.add(new ExchangeVo(c));
        }
        return s;
    }

    @Autowired
    private ExchangeService exchangeService;
    @Autowired
    private UserService userService;

    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }
}
