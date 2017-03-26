package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.service.WithdrawService;
import com.cjteam.xiao.service.impl.QueryCondition;
import com.cjteam.xiao.util.PageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Controller("adminWithdrawController")
@RequestMapping("/admin/withdraws")
@SessionAttributes(types = {QueryCondition.class})
@PreAuthorize(
        "hasRole('payer') ")
public class WithdrawsController {
    private static final Logger log = LoggerFactory.getLogger(WithdrawsController.class);

    @RequestMapping(value = {"/list", "/", "/all", ""})
    public String list(@RequestParam(value = "appId", required = false) String appId, ModelMap map) {
        log.info("list data");
        map.remove(PageConstant.PAGE_QUERY_CONDITON);
        map.put(PageConstant.PAGE, withdrawService.query(appId, 0, PageConstant.PAGE_BIG_SIZE));
        return "/admin/withdraws/list";
    }

    @RequestMapping(value = {"/list/{no:\\d+}", "/{no:\\d+}", "/all/{no:\\d+}"})
    public String list(@RequestParam(value = "appId", required = false) String appId, @PathVariable(value = "no") int pageNo,HttpSession session,SessionStatus status, ModelMap map) {
        status.setComplete();
        session.removeAttribute(PageConstant.PAGE_QUERY_CONDITON);
        map.remove(PageConstant.PAGE_QUERY_CONDITON);
        map.put(PageConstant.PAGE, withdrawService.query(appId, pageNo, PageConstant.PAGE_BIG_SIZE));
        return "/admin/withdraws/list";
    }

    @RequestMapping(value = "/query")
    public String list(QueryCondition queryCondition, ModelMap map) {
        log.debug("query withdraws : {} ", queryCondition.toString());
        map.put(PageConstant.PAGE_QUERY_CONDITON, queryCondition);
        Page pageResult = withdrawService.query(queryCondition.getPageNo(), PageConstant.PAGE_BIG_SIZE, queryCondition);
        map.put(PageConstant.PAGE, pageResult);
        return "/admin/withdraws/list";
    }

    @RequestMapping("/view/{id:\\w+}")
    public String view(@PathVariable Integer id, ModelMap map) {
        log.info("view id {} ", id);
        map.put(PageConstant.VIEW_ENTITY, withdrawService.findOne(id));
        return "/admin/withdraws/detail";
    }

    @RequestMapping({"/charged/{id:\\d+}"})
    public String charged(@PathVariable(value = "id") Integer id) {
        withdrawService.chargedDrawOuts(id);
        return "redirect:/admin/withdraws/query";
    }

    @RequestMapping({"/rollback/{id:\\d+}"})
    public String rollback(@PathVariable(value = "id") Integer id) {
        withdrawService.rollbackDrawOuts(id);
        return "redirect:/admin/withdraws/query";
    }

    @RequestMapping({"/export"})
    public ModelAndView export(@ModelAttribute QueryCondition queryCondition) {
        log.debug("query withdraws : {} ", queryCondition.toString());
        Page pageResult = withdrawService.query(0, Integer.MAX_VALUE, queryCondition);
        return new ModelAndView("excelViewAlipay", "alipayRecords", pageResult.getContent());
    }

    @RequestMapping({"/batchCharged"})
    public String batchCharged(QueryCondition queryCondition) {
        log.debug("batchCharged   : {} ", queryCondition.toString());
        withdrawService.chargedDrawOuts(queryCondition.getId());
        return "redirect:/admin/withdraws/query";
    }

    @Autowired
    private WithdrawService withdrawService;
}
