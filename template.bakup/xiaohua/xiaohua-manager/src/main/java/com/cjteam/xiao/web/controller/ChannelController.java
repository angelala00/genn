package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.Advertisement;
import com.cjteam.xiao.model.Channel;
import com.cjteam.xiao.model.IpLocation;
import com.cjteam.xiao.service.AdvertisementsService;
import com.cjteam.xiao.service.ChannelService;
import com.cjteam.xiao.service.RegionService;
import com.cjteam.xiao.web.vo.AdvertisementsResponse;
import com.cjteam.xiao.web.vo.ChannelResponse;
import com.cjteam.xiao.web.vo.ChannelVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * User: wuyimin Date: 13-10-8
 */
@Controller
public class ChannelController extends BaseController<Channel> {
    private static final Logger log = LoggerFactory.getLogger(ChannelController.class);

    @RequestMapping(value = {"/channels"})
    public
    @ResponseBody
    ChannelResponse channels(@RequestParam("appId") String appId, HttpServletRequest request) {
        ChannelResponse response = new ChannelResponse();
        try {
            String clientIp = request.getHeader("X-Real-IP");
            Boolean isLimitRegion = Boolean.FALSE;
            if (StringUtils.isNotBlank(clientIp)) {
                IpLocation ipLocation = regionService.getIpLocationByIp(clientIp);
                if (null != ipLocation) {
                    if (StringUtils.equals("HK", ipLocation.getCountryId()) || StringUtils.equals("US", ipLocation.getCountryId())) {
                        isLimitRegion = Boolean.TRUE;
                    }
                }
            }
            List<Channel> channels = null;
            if (isLimitRegion) {
                Channel channel = channelService.findOneByCode(appId, "STATIC");
                if (null != channel) {
                    channels = new ArrayList<Channel>(1);
                    channels.add(channel);
                }
            } else {
                channels = channelService.getValidChannels(appId);
            }
            if (CollectionUtils.isNotEmpty(channels)) {
                response.setChannels(change(channels));
                response.setSuccess(Boolean.TRUE);
            } else {
                response.setMessage("no channel data");
            }
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        }
        return response;
    }

    @RequestMapping(value = {"/channelss"})
    public
    @ResponseBody
    ChannelResponse channelss(@RequestParam("appId") String appId, HttpServletRequest request) {
        ChannelResponse response = new ChannelResponse();
        try {
            String clientIp = request.getHeader("X-Real-IP");
            Boolean isLimitRegion = Boolean.FALSE;
            if (StringUtils.isNotBlank(clientIp)) {
                IpLocation ipLocation = regionService.getIpLocationByIp(clientIp);
                if (null != ipLocation) {
                    if (StringUtils.equals("HK", ipLocation.getCountryId()) || StringUtils.equals("US", ipLocation.getCountryId())) {
                        isLimitRegion = Boolean.TRUE;
                    }
                }
            }
            List<Channel> channels = null;
            if (isLimitRegion) {
                Channel channel = channelService.findOneByCode(appId, "STATIC");
                if (null != channel) {
                    channels = new ArrayList<Channel>(1);
                    channels.add(channel);
                }
            } else {
            	channels = new ArrayList<Channel>();
//                channels = channelService.getValid2Channels(appId);
            }

            if (CollectionUtils.isNotEmpty(channels)) {
                response.setChannels(change(channels));
                response.setSuccess(Boolean.TRUE);
            } else {
                response.setMessage("no channel data");
            }
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        }
        return response;
    }

    @RequestMapping(value = "/channels-used")
    public
    @ResponseBody
    List<ChannelVo> used(HttpServletRequest request) {
        return change(channelService.getAllUsedChannels(getAppIdParam(request)));
    }
    @RequestMapping(value = {"/advertisements"})
    public
    @ResponseBody
    AdvertisementsResponse advertisements(@RequestParam(value = "channel", defaultValue = "STATIC") String channel) {
        AdvertisementsResponse response = new AdvertisementsResponse();
        try {
            Page<Advertisement> advertisements = advertisementsService.getAdvertisementsByChannel(channel);
            if (advertisements != null && CollectionUtils.isNotEmpty(advertisements.getContent())) {
                response.fillAdvertisements(advertisements.getContent());
                response.setSuccess(Boolean.TRUE);
            } else {
                response.setMessage("no advertisements data");
            }
        } catch (Throwable throwable) {
            response.setMessage(throwable.getMessage());
            log.error(throwable.getLocalizedMessage(), throwable);
        }
        return response;
    }

    private List<ChannelVo> change(List<Channel> channels) {
        List<ChannelVo> s = new ArrayList<ChannelVo>();
        for (Channel c : channels) {
            s.add(new ChannelVo(c));
        }
        return s;
    }
    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }
    @Autowired
    private ChannelService channelService;
    @Autowired
    private AdvertisementsService advertisementsService;
    @Autowired
    private RegionService regionService;
}