package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.impl.QueryCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface IntegralService {
    void save(Integral integral) ;
	Page<Integral> getByUserId(String appId, String userId, Pageable pageable);

	Page<Integral> getByUserId(String appId, String userId);

	Integral findOneByOrderId(String appId, String orderid);

    Integral findOneByUserIdAndChannel(String appId, String userId, String channelCode);

    Page<Integral> queryAll(String appId,int page,int size);

    Page<Integral> query(int pageNo, int size, QueryCondition queryCondition);

    String addPoints(String appId,String pointsstr, String userId, String channelCode, String orderid);

    String addPoints(String appId, String points, String userId, String channelCode, String orderid, String adid, String adName);

    String addPoints(String appId, String points, String userId, String channelCode, String order, String adsid, String s, Long timestamp);

    Page<Integral> queryIntegralsByUser(String appId, String userId, int page, int pageDefaultSize);

    String recordincome(User user, String channelCode, Date date, Integer points, String orderId, String adid, String adName, Boolean status, String msg);

    String recordincome(Integral integral);

    boolean hasIntegralByType(String appId, String userId, String integralType);

    List<Integral> fetchNews(String appId, int newSize, Date latestFetchTime);
}
