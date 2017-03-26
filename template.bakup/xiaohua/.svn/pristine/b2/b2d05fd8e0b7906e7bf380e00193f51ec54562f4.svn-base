package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.service.UserService;
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

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Controller("adminUserController")
@RequestMapping("/admin/users")
@PreAuthorize(
        "hasRole('manager') ")
public class UsersController {
    private static final Logger log = LoggerFactory.getLogger(UsersController.class);

    @RequestMapping(value = {"/list", "/", "/all", ""})
    public String list(@RequestParam(value = "appId", required = false) String appId, ModelMap map) {
        Page pageResult = userService.queryAll(appId, PageConstant.PAGE_DEFAULT_SIZE, null, 0);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/users/list";
    }

    //上下两个方法合并
    @RequestMapping(value = "/query")
    public String list(@ModelAttribute QueryCondition queryCondition, ModelMap map) {
        map.put(PageConstant.PAGE_QUERY_CONDITON, queryCondition);
        Page pageResult = userService.queryAll(queryCondition.getAppId(), PageConstant.PAGE_DEFAULT_SIZE, queryCondition, queryCondition.getPageNo());
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/users/list";
    }

    @RequestMapping(value = {"/black/{id:\\d+}", "/white/{id:\\d+}"})
    public String black(@PathVariable(value = "id") Integer id, HttpServletRequest request) {
        if (request.getRequestURI().contains("/black"))
            userService.blackUser(id);
        else if (request.getRequestURI().contains("/white"))
            userService.whiteUser(id);
        return "redirect:" + request.getHeader("Referer");
    }

    @RequestMapping(value = {"/stat/{id:\\w+}"})
    public String stat(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "id") String userId, HttpServletRequest request, ModelMap modelMap) {
        modelMap.put(PageConstant.VIEW_ENTITY, statisticsService.statUser(appId, userId));
        return "/admin/users/stat";
    }

    @RequestMapping(value = {"/list/{no:\\d+}", "/{no:\\d+}", "/all/{no:\\d+}"})
    public String list(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "no") int pageNo, ModelMap map) {
        Page pageResult = userService.queryAll(appId, PageConstant.PAGE_DEFAULT_SIZE, null, pageNo < 0 ? 0 : pageNo);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/users/list";
    }

    @PreAuthorize(
            "hasRole('admin') ")
    @RequestMapping(value = {"/blacklist"})
    public String blacklist(QueryCondition queryCondition, ModelMap map) {
        map.put(PageConstant.PAGE_QUERY_CONDITON, queryCondition);
        Page pageResult = userService.getBlackListUsers(queryCondition.getAppId(), PageConstant.PAGE_DEFAULT_SIZE, queryCondition, 0);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/users/blacklist";
    }

    @PreAuthorize(
            "hasRole('admin') ")
    @RequestMapping(value = {"/blacklist/{no:\\d+}"})
    public String blacklist(@PathVariable(value = "no") int pageNo, QueryCondition queryCondition, ModelMap map) {
        map.put(PageConstant.PAGE_QUERY_CONDITON, queryCondition);
        Page pageResult = userService.getBlackListUsers(queryCondition.getAppId(), PageConstant.PAGE_DEFAULT_SIZE, queryCondition, pageNo < 0 ? 0 : pageNo);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/users/blacklist";
    }

    @RequestMapping("/view/{type:\\w+}")
    public String view(@RequestParam(value = "appId", required = false) String appId, @PathVariable String id, ModelMap map) {
        map.put(PageConstant.VIEW_ENTITY, userService.getOne(appId, id));
        return "/admin/users/view";
    }

    @RequestMapping("/top/{no:\\d+}")
    public String viewTopTotallyScore(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "no") int pageNo, ModelMap map) {
        map.put(PageConstant.PAGE, userService.getTopTotallyScoredUserByPage(appId, PageConstant.PAGE_DEFAULT_SIZE, pageNo < 0 ? 0 : pageNo));
        return "/admin/users/tops";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private StatisticsService statisticsService;
}
