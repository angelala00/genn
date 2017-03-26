package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Exchange;
import com.cjteam.xiao.model.Product;
import com.cjteam.xiao.service.doublenine.DoubleNineInterfaceException;
import com.cjteam.xiao.service.impl.QueryCondition;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

/**
 * Created by ChenLong Date: 13-9-26
 */
public interface ExchangeService {

    void charge2telphone(String appId, String userId, String productCode, String telphone) throws DoubleNineInterfaceException;

    Product withdrawFromAlipay(String appId, String userId, String productCode, String alipayNo);

    Page<Exchange> getQBAndMobileChargeRecords(String appId, String userId);

    void charge2Qb(String appId, String userId, String productCode, String qqNo) throws DoubleNineInterfaceException;

    Exchange getById(Integer integer);

    void save(Exchange exchange);

    Page<Exchange> queryAll(String appId, int page, int size);

    Page<Exchange> getExchangeRecordsByUserId(String appId, String userId, int pageNo, int size);

    Page<Exchange> query(int pageNo, int pageSize, QueryCondition queryCondition);

    List<Exchange> fetchNews(String appId, int newSize, Date latestFetchTime);

    void saleProduct(String appId, String userId, String productCode, Integer count);

    void consumeProduct(String appId, String userId, String productType, Integer count);

    Integer luckyStars(String appId, String userId, Integer score);
}
