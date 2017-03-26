package com.cjteam.xiao.service;

import com.cjteam.xiao.model.IpLocation;

import java.io.IOException;

/**
 * Created by ChenLong on 14-3-17.
 */
public interface RegionService {
    boolean check(String appId, String ip, String channelCode, Integer score);

    IpLocation getIpLocationByIp(String ip) throws IOException;

    IpLocation queryLocationInfoFromTaobaoIPInterface(String ip) throws IOException;

    IpLocation queryLocationInfoFromSinaIPInterface(String ip) throws IOException;
}
