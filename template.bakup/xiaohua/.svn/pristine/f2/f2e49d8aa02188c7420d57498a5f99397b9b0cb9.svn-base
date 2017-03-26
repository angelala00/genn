package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.service.AppService;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.web.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;

/**
 * @author JiangChi
 */
@Controller
@RequestMapping("/scorecallback")
public class StaticChannelScoreCallBackController {
    private static final Logger LOG = LoggerFactory.getLogger(StaticChannelScoreCallBackController.class);

    /**
     * http://os.dzye.net:81/scorecallback/dzye?&point=10&orderid=123&userid=324
     *
     * @param orderid
     * @param userid
     * @param point
     * @param time
     * @param checksum
     * @return
     */
    @RequestMapping(value = {"/dzye"})
    public
    @ResponseBody
    ResponseVo dzye(@RequestParam(value = "appId", required = false) String appId, @RequestParam(required = true) String orderid, @RequestParam(required = true) String userid,
                    @RequestParam(required = true) String point, @RequestParam(required = false) String time,
                    @RequestParam(required = false) String checksum) {
        ResponseVo response = new ResponseVo();
        String channel_meansplatform = "STATIC";
        try {
            String tip = "OK";
            if (appService.onJudgeStatus(appId)||appService.onTestStatus(appId)) {
                integralService.addPoints(appId, point, userid, channel_meansplatform, orderid);
            }
            response.setSuccess(Boolean.TRUE);
            response.setMessage("积分录入成功:" + tip);
        } catch (Exception e) {
            LOG.error(e.getLocalizedMessage(), e);
            response.setSuccess(Boolean.FALSE);
            response.setMessage("积分录入异常");
        }
        return response;
    }

    private String decodeAdName(String adname) {
        adname = StringUtils.trimToEmpty(adname);
        if (StringUtils.isNotBlank(adname)) {
            adname = URLDecoder.decode(adname);
        }
        return adname;
    }

    @Autowired
    private IntegralService integralService;
    @Autowired
    private AppService appService;
}
