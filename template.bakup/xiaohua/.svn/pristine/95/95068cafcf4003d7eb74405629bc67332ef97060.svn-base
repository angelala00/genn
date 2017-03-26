package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.service.ExchangeService;
import com.cjteam.xiao.service.impl.QueryCondition;
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
@Controller("adminExchangeController")
@RequestMapping("/admin/exchanges")
@PreAuthorize(
        "hasRole('manager') ")
public class ExchangeRecordsController {
    private static final Logger log = LoggerFactory.getLogger(ExchangeRecordsController.class);

    @RequestMapping(value = {"/list", "/", "/all",""})
    public String list(@RequestParam(value = "appId", required = false) String appId, ModelMap map) {
        log.info("list data");
        Page pageResult =  exchangeService.queryAll(appId, 0, PageConstant.PAGE_DEFAULT_SIZE);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/exchanges/list";
    }

    public String userExchanges(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "userId") String userId,ModelMap map) {
        log.info("userExchanges");
        Page pageResult =  exchangeService.getExchangeRecordsByUserId(appId, userId,0, PageConstant.PAGE_DEFAULT_SIZE);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/exchanges/user-exchanges";
    }
    @RequestMapping(value = {"/user-exchanges/{userId:\\w+}/{no:\\d+}"})
    public String userExchanges(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "userId") String userId,@PathVariable(value = "no") int pageNo,ModelMap map) {
        log.info("list userExchanges");
        Page pageResult =  exchangeService.getExchangeRecordsByUserId(appId, userId,pageNo < 0 ? 0 : pageNo, PageConstant.PAGE_DEFAULT_SIZE);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/exchanges/user-exchanges";
    }

    @RequestMapping(value = {"/list/{no:\\d+}", "/{no:\\d+}", "/all/{no:\\d+}"})
    public String list(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "no") int pageNo, ModelMap map) {
        map.put(PageConstant.PAGE, exchangeService.queryAll(appId, pageNo < 0 ? 0 : pageNo, PageConstant.PAGE_DEFAULT_SIZE));
        return "/admin/exchanges/list";
    }

    @RequestMapping("/view/{id:\\w+}")
    public String view(@PathVariable Integer id, ModelMap map) {
        log.info("product controller query by type {}", id);
        map.put(PageConstant.VIEW_ENTITY,exchangeService.getById(id));
        return "/admin/exchanges/detail";
    }

    @RequestMapping(value = "/query")
    public String list(@ModelAttribute QueryCondition queryCondition, ModelMap map){
        log.info("query integrals : {} ",queryCondition.toString());
        map.put(PageConstant.PAGE_QUERY_CONDITON,queryCondition);
        Page pageResult = exchangeService.query(queryCondition.getPageNo(), PageConstant.PAGE_DEFAULT_SIZE, queryCondition);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/exchanges/list";
    }

    @Autowired
    private ExchangeService exchangeService;
}
