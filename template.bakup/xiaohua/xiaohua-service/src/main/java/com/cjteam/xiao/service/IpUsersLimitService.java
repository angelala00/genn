package com.cjteam.xiao.service;

/**
 * Created by ChenLong
 * Date: 13-12-16
 */
public interface IpUsersLimitService {
    boolean control(String xRealIp,String appId, String userId);
}
