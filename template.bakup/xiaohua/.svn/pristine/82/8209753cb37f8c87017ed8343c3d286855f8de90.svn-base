package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.service.ChannelService;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.impl.ChannelServiceImpl;
import com.cjteam.xiao.service.impl.QueryCondition;
import com.cjteam.xiao.stat.service.StatisticsService;
import com.cjteam.xiao.util.PageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Controller("adminIntegralController")
@RequestMapping("/admin/integrals")
@PreAuthorize(
        "hasRole('manager') ")
public class IntegralsController {
    private static final Logger log = LoggerFactory.getLogger(IntegralsController.class);

    @RequestMapping(value = {"/list", "/", "/all", ""})
    public String list(@RequestParam(value = "appId", required = false) String appId, ModelMap map) {
        log.info("list data");
        Page pageResult = integralService.queryAll(appId,0, PageConstant.PAGE_DEFAULT_SIZE);
        log.info("page number {}",pageResult.getNumber());
        map.put(PageConstant.PAGE, pageResult);
        map.put(PageConstant.VIEW_LIST,channelService.getAllUsedChannels(appId));
        return "/admin/integrals/list";
    }

    @RequestMapping(value = "/query")
    public String list(@ModelAttribute QueryCondition queryCondition, ModelMap map){
        log.info("query integrals : {} ",queryCondition.toString());
        map.put(PageConstant.PAGE_QUERY_CONDITON,queryCondition);
        Page pageResult = integralService.query(queryCondition.getPageNo(), PageConstant.PAGE_DEFAULT_SIZE, queryCondition);
        map.put(PageConstant.PAGE, pageResult);
        map.put(PageConstant.VIEW_LIST,channelService.getAllUsedChannels(queryCondition.getAppId()));
        return "/admin/integrals/list";
    }

    @RequestMapping(value = {"/list/{no:\\d+}", "/{no:\\d+}", "/all/{no:\\d+}"})
    public String list(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "no") int pageNo, ModelMap map) {
        Page pageResult = integralService.queryAll(appId,pageNo < 0 ? 0 : pageNo, PageConstant.PAGE_DEFAULT_SIZE);
        map.put(PageConstant.PAGE, pageResult);
        map.put(PageConstant.VIEW_LIST,channelService.getAllUsedChannels(appId));
        return "/admin/integrals/list";
    }

    @RequestMapping(value = {"/stat", "/stat/"})
    public String stat(@RequestParam(value = "appId", required = false) String appId, ModelMap modelMap) {
        modelMap.put(PageConstant.VIEW_ENTITY + "2", statisticsService.integralScoreCategoryStat(appId));
        return "/admin/integrals/stat";
    }

    @RequestMapping("/user-integrals/{userId:\\w+}/{no:\\d+}")
    public String userIntegrals(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "userId") String userId, @PathVariable(value = "no") int pageNo, ModelMap map) {
        Page pageResult = integralService.queryIntegralsByUser(appId, userId, pageNo < 0 ? 0 : pageNo, PageConstant.PAGE_DEFAULT_SIZE);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/integrals/user-integrals";
    }


    @Autowired
    private ChannelService channelService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private IntegralService integralService;
}
