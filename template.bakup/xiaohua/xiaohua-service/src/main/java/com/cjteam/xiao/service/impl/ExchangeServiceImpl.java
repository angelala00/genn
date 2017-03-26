package com.cjteam.xiao.service.impl;

import com.google.common.base.Joiner;
import com.cjteam.xiao.dao.ExchangeDao;
import com.cjteam.xiao.model.*;
import com.cjteam.xiao.repository.*;
import com.cjteam.xiao.service.*;
import com.cjteam.xiao.service.doublenine.DoubleNineInterfaceException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by ChenLong Date: 13-9-27
 */
@Service
@Transactional
public class ExchangeServiceImpl implements ExchangeService {
    private static final Logger LOG = LoggerFactory.getLogger(ExchangeServiceImpl.class);
    private static final Logger LOG_EXCHANGE = LoggerFactory.getLogger(ExchangeServiceImpl.class.getName() + ".EXCHANGE");

    @Value("${sys.conf.one.day.times.alipaydrawout}")
    private String alipay_drwaout_times_oneday;
    @Value("${sys.conf.one.day.times.telphone.exchange}")
    private String telphone_charge_times_oneday;
    @Value("${sys.conf.one.day.times.qb.exchange}")
    private String qb_exchange_times_oneday;

    @Override
    public void charge2telphone(String appId, String userId, String productCode, String telphone) throws DoubleNineInterfaceException {
        User operator = userRepository.findByAppIdAndUserId(appId, userId);
        Assert.isTrue(!operator.getIsBlack(), "用户 " + operator.getNickName() + " 已在黑名单，不能消费积分");
        Assert.isTrue(StringUtils.isNotBlank(telphone), "手机号码 " + telphone + " 无效");
        Assert.isTrue(isInTimesToExchangeTelphone(operator.getAppId(), telphone), "当天兑换次数已用完，请明日再来 ");
        Exchange exchange = new Exchange();
        exchange.setUser(operator);
        Product product = productRepository.getOne(productCode);
        Assert.notNull(product,"兑换的礼品不存在");
        exchange.setProduct(product);
        exchange.setMoneyExchange(product.getPrice());
        exchange.setScoreConsumption(product.getScore());
        exchange.setConsumerAccount(telphone);

        Assert.isTrue(exchange.getUser().exchangeInRemainingLimit(exchange.getScoreConsumption()), "当天兑换次数已用完，请明日再来");

        doubleNineService.chargeForMobile(exchange);
        exchangeRepository.save(exchange);
        LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},chargeAccount:{},amount:{},cost:{}}",
                ExchangeSituation.SUCCESS, ExchangeSituation.Type.MOBILE, DateTime.now().toString("yyyy-MM-dd"),
                userId, productCode, telphone, product.getPrice(), product.getScore());
    }

    private boolean isInTimesToExchangeTelphone(String appId, String consumerAccount) {
        Integer limitTimes = (Integer) sysConfService.getConfigValueByConfigCode(appId, telphone_charge_times_oneday, new Integer(0));
        if (null == limitTimes || limitTimes < 0) {
            LOG.debug("sys.conf.one.day.times.telphone.exchange not set up, or value is not a number type");
            return true;
        }
        Calendar deadline = Calendar.getInstance();
        deadline.set(Calendar.HOUR_OF_DAY, 0);
        deadline.set(Calendar.MINUTE, 0);
        deadline.set(Calendar.SECOND, 0);
        deadline.set(Calendar.MILLISECOND, 0);
        return limitTimes > exchangeRepository.countByAppIdAndConsumerAccountAndProduct_ProductType_CodeAndCreateTimeAfter(appId, consumerAccount, "MOBILE", deadline.getTime());
    }

    @Override
    public Product withdrawFromAlipay(String appId, String userId, String productCode, String alipayNo) {
        User operator = userRepository.findByAppIdAndUserId(appId, userId);
        if (StringUtils.isBlank(alipayNo)) {
            alipayNo = operator.getAlipayNo();
            Assert.hasText(alipayNo, "请首先进入帮助区完善个人信息");
        }

        Assert.isTrue(!operator.getIsBlack(), "user " + operator.getNickName() + "  ");
        Assert.isTrue(isInTimesToExchangeAlipay(operator.getAppId(), alipayNo), " 当天兑换次数已用完，请明日再来");
        Product product = productRepository.getOne(productCode);
        Assert.notNull(product, "兑换的礼品不存在");

        Exchange exchange = new Exchange();
        exchange.setUser(operator);
        exchange.setProduct(product);
        exchange.setMoneyExchange(product.getPrice());
        exchange.setScoreConsumption(product.getScore());
        exchange.setConsumerAccount(alipayNo);

        Assert.isTrue(exchange.getUser().getSurplus() >= exchange.getScoreConsumption(), "积分不够了哦");

        exchange.setState(stateTypeService.getStateTypeByCode(DoubleNineAgent.STATE_DOING));
        Assert.isTrue(userService.reduceUserScore(exchange.getUser(), exchange.getScoreConsumption()), "减积分失败");
        exchangeRepository.save(exchange);
        recordAlipayDraw(exchange);

        LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},chargeAccount:{},amount:{},cost:{}}",
                ExchangeSituation.SUCCESS, ExchangeSituation.Type.ZHIFUBAO, DateTime.now().toString("yyyy-MM-dd"),
                userId, productCode, alipayNo, product.getPrice(), product.getScore());
        return product;
    }

    private boolean isInTimesToExchangeAlipay(String appId, String alipayNo) {
        if (alipayNoIsInWhiteList(alipayNo)) {
            return true;
        }
        Integer limitTimes = (Integer) sysConfService.getConfigValueByConfigCode(appId, alipay_drwaout_times_oneday, new Integer(0));
        if (null == limitTimes || limitTimes < 0) {
            LOG.debug("sys.conf.one.day.times.alipaydrawout not set up, or value is not a number type");
            return true;
        }
        Calendar deadline = Calendar.getInstance();
        deadline.set(Calendar.HOUR_OF_DAY, 0);
        deadline.set(Calendar.MINUTE, 0);
        deadline.set(Calendar.SECOND, 0);
        deadline.set(Calendar.MILLISECOND, 0);
        return limitTimes > exchangeRepository.countByAppIdAndConsumerAccountAndProduct_ProductType_CodeAndCreateTimeAfter(appId, alipayNo, "ALIPAY", deadline.getTime());
    }

    private boolean alipayNoIsInWhiteList(String alipayNo) {
        AlipayWhiteList alipayWhiteList = alipayWhiteListRepository.findOne(alipayNo);
        return alipayWhiteList != null && alipayWhiteList.getValid();
    }

    private void recordAlipayDraw(Exchange exchange) {
        Withdraw draw = new Withdraw(exchange);
        draw.setAlipayNo(exchange.getConsumerAccount());
        draw.setUser(exchange.getUser());
        draw.setScoreCost(exchange.getProduct().getPrice());
        draw.setAmountMoney(new Float(exchange.getProduct().getPrice() / DoubleNineAgent.RATE));
        draw.setState(stateTypeService.getStateTypeByCode(DoubleNineAgent.STATE_DOING));
        withdrawRepository.save(draw);
    }

    @Override
    public void charge2Qb(String appId, String userId, String productCode, String qqNo) throws DoubleNineInterfaceException {
        User operator = userRepository.findByAppIdAndUserId(appId, userId);
        Assert.isTrue(!operator.getIsBlack(), "用户 " + operator.getNickName() + " 已在黑名单，不能消费积分");
        Assert.isTrue(StringUtils.isNotBlank(qqNo), "parameter consumerAccount " + qqNo + " is invalid");
        Assert.isTrue(isInTimesToExchangeQb(operator.getAppId(), qqNo), "当天兑换次数已用完，请明日再来 ");
        Exchange exchange = new Exchange();
        exchange.setUser(operator);
        Product product = productRepository.getOne(productCode);
        Assert.notNull(product, "兑换的礼品不存在");
        exchange.setProduct(product);
        exchange.setMoneyExchange(product.getPrice());
        exchange.setScoreConsumption(product.getScore());
        exchange.setConsumerAccount(qqNo);

        Assert.isTrue(exchange.getUser().getSurplus() >= exchange.getScoreConsumption(), "积分不够了哦");
        Assert.isTrue(exchange.getUser().exchangeInRemainingLimit(exchange.getScoreConsumption()), "当天兑换次数已用完，请明日再来");

        doubleNineService.chargeForQb(exchange);
        exchangeRepository.save(exchange);
        LOG_EXCHANGE.warn("{}-{}-{}:{userId:{},productCode:{},chargeAccount:{}}",
                ExchangeSituation.SUCCESS, ExchangeSituation.Type.QB, DateTime.now().toString("yyyy-MM-dd"),
                userId, productCode, qqNo);
    }

    private boolean isInTimesToExchangeQb(String appId, String consumerAccount) {
        Integer limitTimes = (Integer) sysConfService.getConfigValueByConfigCode(appId, qb_exchange_times_oneday, new Integer(0));
        if (null == limitTimes || limitTimes < 0) {
            return true;
        }

        Calendar deadline = Calendar.getInstance();
        deadline.set(Calendar.HOUR_OF_DAY, 0);
        deadline.set(Calendar.MINUTE, 0);
        deadline.set(Calendar.SECOND, 0);
        deadline.set(Calendar.MILLISECOND, 0);
        return limitTimes > exchangeRepository.countByAppIdAndConsumerAccountAndProduct_ProductType_CodeAndCreateTimeAfter(appId, consumerAccount, "QB", deadline.getTime());
    }

    @Override
    public Exchange getById(Integer id) {
        return exchangeRepository.findOne(id);
    }

    @Override
    public void save(Exchange exchange) {
        exchangeRepository.save(exchange);
    }

    @Override
    public Page<Exchange> getQBAndMobileChargeRecords(String appId, String userId) {
        return exchangeRepository.findByAppIdAndUser_UserIdAndProduct_ProductType_CodeIn(appId, userId, new String[]{"QB", "MOBILE", "ALIPAY"}, new PageRequest(0, 50, Sort.Direction.DESC, "state.order", "createTime"));
    }

    @Override
    public Page<Exchange> queryAll(String appId, int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "state.order", "createTime");
        return exchangeRepository.findByAppIdAndProduct_ProductType_CodeIn(appId, new String[]{"QB", "MOBILE"}, pageable);
    }

    @Override
    public Page<Exchange> getExchangeRecordsByUserId(String appId, String userId, int page, int size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.DESC, "state.order", "createTime");
        return exchangeRepository.findByAppIdAndProduct_ProductType_CodeInAndUser_UserId(appId, new String[]{"QB", "MOBILE", "ALIPAY"}, userId, pageable);
    }

    @Override
    public Page<Exchange> query(int pageNo, int pageSize, QueryCondition queryCondition) {
        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, Integral.filedCreateTime);
        if (null == queryCondition)
            return exchangeRepository.findAll(pageable);
        Map<String, Object> filterValues = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(queryCondition.getName())) {
            filterValues.put(" user.nickName=", queryCondition.getName());
        }
        if (StringUtils.isNotBlank(queryCondition.getPayNo())) {
            filterValues.put(" consumerAccount= ", queryCondition.getPayNo());
        }
        if (StringUtils.isNotBlank(queryCondition.getProductType())) {
            filterValues.put(" product.productType.code= ", queryCondition.getProductType());
        }
        if (null != queryCondition.getAfterDate()) {
            filterValues.put(" createTime>= ", queryCondition.getAfterDate());
        }
        if (null != queryCondition.getBeforeDate()) {
            filterValues.put(" createTime< ", queryCondition.getBeforeDate());
        }
        if (queryCondition.isUnPaid()) {
            filterValues.put(" state.code= ", "doing");
        }
        LOG.debug("filter key set is {} ", Joiner.on(",").join(filterValues.keySet().iterator()));
        LOG.debug("filter value collection is {} ", Joiner.on(",").join(filterValues.values().iterator()));
        return exchangeDao.filter(filterValues, pageable);
    }

    @Override
    public List<Exchange> fetchNews(String appId, int newSize, Date latestFetchTime) {
        return exchangeRepository.findAppIdAndByTime(appId, latestFetchTime, newSize);
    }

    @Override
    public Integer luckyStars(String appId, String userId, Integer score) {
        Assert.isTrue(score != null && score >= 500, "积分太少，至少500分哦");
        Assert.isTrue(userService.consumeScore(appId, userId, score), "积分不够了哦");
        User operator = userRepository.findByAppIdAndUserId(appId, userId);
        String alipayNo = operator.getAlipayNo();
        Assert.hasText(alipayNo, "支付宝帐号参数为空，并且用户没有设置自己的支付宝帐号，无法完成提现");

        Assert.isTrue(!operator.getIsBlack(), "用户  " + operator.getNickName() + " 已在黑名单，不能消费积分");
        Assert.isTrue(isInTimesToExchangeAlipay(operator.getAppId(), alipayNo), "该支付宝帐号提现次数超限制 " + alipay_drwaout_times_oneday);
        Withdraw draw = new Withdraw();
        draw.setAlipayNo(alipayNo);
        draw.setUser(operator);
        draw.setScoreCost(score);
        draw.setAmountMoney(new Double(Math.floor(score / DoubleNineAgent.RATE)).floatValue());
        draw.setState(stateTypeService.getStateTypeByCode(DoubleNineAgent.STATE_DOING));
        withdrawRepository.save(draw);
        return draw.getAmountMoney().intValue();
    }

    @Override
    public void saleProduct(String appId, String userId, String productCode, Integer count) {
        if (count == null || count <= 0)
            return;
        Product product = productRepository.getOne(productCode);
        Assert.notNull(product, "没有该产品销售");
        int sumCost = product.getScore() * count;
        Assert.isTrue(userService.consumeScore(appId, userId, sumCost), "积分不够了哦");
        recordSale(appId, userId, product.getProductType().getCode(), count);
    }

    @Override
    public void consumeProduct(String appId, String userId, String productType, Integer count) {
        if (count == null || count <= 0)
            return;
        recordSale(appId, userId, productType, 0 - count);
    }

    private void recordSale(String appId, String userId, String productType, Integer count) {
        if (StringUtils.equals(ProductType.EYE, productType)) {
            Assert.isTrue(userService.recordEyeCountIncrease(appId, userId, count), "数量不够");
        } else if (StringUtils.equals(ProductType.TIME, productType)) {
            Assert.isTrue(userService.recordTimeCountIncrease(appId, userId, count), "数量不够");
        } else {
            LOG.info("not support product type {}， or there is no need to record on user info ", productType);
        }
    }

    @Resource(name = "exchangeDao")
    private ExchangeDao exchangeDao;
    @Autowired
    private WithdrawRepository withdrawRepository;
    @Autowired
    private ExchangeRepository exchangeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DoubleNineService doubleNineService;
    @Autowired
    private StateTypeService stateTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysConfService sysConfService;
    @Autowired
    private AlipayWhiteListRepository alipayWhiteListRepository;
}
