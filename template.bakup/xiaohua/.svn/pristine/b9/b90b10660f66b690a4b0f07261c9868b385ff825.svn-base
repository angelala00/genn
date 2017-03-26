package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.service.ChannelService;
import com.cjteam.xiao.service.DailyRegionIntegralLimitService;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.util.PageConstant;
import com.cjteam.xiao.web.controller.BaseController;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@Controller
@RequestMapping("/admin/region")
@PreAuthorize(
        "hasRole('admin') ")
public class DailyRegionIntegralLimitController extends BaseController {
    private final static String INDEX = "/admin/region/index";
    private final static String ADD = "/admin/region/add";

    @SuppressWarnings("static-access")
    @RequestMapping(value = {""})
    public String index(PageBasicInfo pageBasicInfo, Map<String, Object> map, String cityName,HttpServletRequest request) {
        PageImpl<DailyRegionIntegralLimit> pageImpl = dailyRegionIntegralLimitService.getPage(getAppIdParam(request), pageBasicInfo, cityName);
        map.put(PageConstant.PAGE, pageImpl);
        return INDEX;
    }
    

    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request, ModelMap map) {
        map.put(PageConstant.VIEW_LIST, channelService.getAllUsedChannels(getAppIdParam(request)));
        return ADD;
    }

    @RequestMapping(value = "/subAdd")
    public String subAdd(DailyRegionIntegralLimit dailyRegionIntegralLimit) {
        dailyRegionIntegralLimitService.seve(dailyRegionIntegralLimit);
        return INDEX;
    }

    @RequestMapping(value = "/update/{id:\\d+}")
    public String update(@PathVariable(value = "id") int id, Map<String, Object> map) {
        dailyRegionIntegralLimitService.get(id);
        map.put(PageConstant.VIEW_ENTITY, dailyRegionIntegralLimitService.get(id));
        return ADD;
    }

    @RequestMapping(value = "/subUpdate")
    public String subUpdate(DailyRegionIntegralLimit regionIntegralLimit) {
        dailyRegionIntegralLimitService.changeLimitValue(regionIntegralLimit.getId(), regionIntegralLimit.getScore());
        return INDEX;
    }

    @Resource
    private DailyRegionIntegralLimitService dailyRegionIntegralLimitService;
    @Resource
    private ChannelService channelService;

    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }
}
