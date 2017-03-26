package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.stat.model.ChannelsDailyStat;
import com.cjteam.xiao.stat.service.StatisticsService;
import com.cjteam.xiao.web.controller.BaseController;
import com.cjteam.xiao.web.vo.ComprehensiveStatInfosVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-11
 */
@Controller
@RequestMapping(value = "/admin/stat")
@PreAuthorize(
        "isAuthenticated()")
public class StatController  extends BaseController<ChannelsDailyStat>{
    private static final Logger LOG = LoggerFactory.getLogger(StatController.class);
   /* @RequestMapping(value = {"/channel"})
    public
    @ResponseBody
    ChannelsDailyStat channelDaily(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return statisticsService.channelStat(getAppIdParam(request));
    }*/

    @RequestMapping(value = {"/channel-daily/{channel:\\w+}"})
    public
    @ResponseBody
    ChannelsDailyStat dailyByChannel(@PathVariable(value = "channel") String channel, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return statisticsService.channelStat(getAppIdParam(request),channel);
    }

    @RequestMapping(value = {"/channel-monthly/{channel:\\w+}"})
    public
    @ResponseBody
    List<Object[]> mothlyByChannel(@PathVariable(value = "channel") String channel, HttpServletRequest request, HttpServletResponse response) throws IOException {
        return statisticsService.integralScoreMonthlyStat(getAppIdParam(request),channel);
    }

    @RequestMapping(value = {"/comprehensive"})
    public
    @ResponseBody
    ComprehensiveStatInfosVO comprehensive(HttpServletRequest request) throws IOException {
        ComprehensiveStatInfosVO response = new ComprehensiveStatInfosVO();
        response.setComprehensiveStatInfos(statisticsService.comprehensiveStat(getAppIdParam(request)));
        return response;
    }

    @Autowired
    StatisticsService statisticsService;

    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }
}
