package com.cjteam.xiao.duiba.controller;

import cn.com.duiba.credits.sdk.CreditConsumeParams;
import cn.com.duiba.credits.sdk.CreditNotifyParams;
import cn.com.duiba.credits.sdk.CreditQueryParams;
import cn.com.duiba.credits.sdk.CreditTool;
import com.cjteam.xiao.duiba.service.DuiBaCreditService;
import com.cjteam.xiao.duiba.service.DuibaAccountService;
import com.cjteam.xiao.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ChenLong on 2014/7/5.
 */
@Controller
@RequestMapping("/dui")
public class DuiBaController extends BaseController<Integer> {
    private static final Logger LOG = LoggerFactory.getLogger(DuiBaController.class);


    @RequestMapping("/creditAutoLoginUrl/")
    public void creditAutoLoginUrl(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.getWriter().write(duiBaCreditService.buildCreditAutoLoginRequest(getAppIdParam(request),request.getParameter("userId")));
        } catch (Exception e) {
            LOG.info(e.getLocalizedMessage(), e);
        }
    }

    @RequestMapping("/credit/")
    public void credit(HttpServletRequest request, HttpServletResponse response) {
        try {
            String appId = this.duibaAccountService.getAppIdByAppKey(request.getParameter("appKey")) ;
            CreditTool tool = duiBaCreditService.getCreditTool(appId);


            CreditQueryParams params = tool.parseCreditQuery(request);//利用tool来解析这个请求

            LOG.info("duiba credit {}", params.toString());
            response.getWriter().write(duiBaCreditService.credit(appId,params).toString());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.getLocalizedMessage(), e);
        }
    }
    @Resource
    private DuibaAccountService duibaAccountService ;
    @RequestMapping("/creditConsume/")
    public void creditConsume(HttpServletRequest request, HttpServletResponse response ,String description) {

        try {
            String appId = this.duibaAccountService.getAppIdByAppKey(request.getParameter("appKey")) ;
            request.setCharacterEncoding("utf-8");
            String str=new String(description.getBytes("ISO-8859-1"),"UTF-8");


            System.out.println(request.getParameter("description"));

            CreditTool tool = duiBaCreditService.getCreditTool(appId);
            CreditConsumeParams params = tool.parseCreditConsume(request);//利用tool来解析这个请求
            LOG.info("duiba creditConsume {}", params.toString());

            response.getWriter().write(duiBaCreditService.creditConsume(appId,params).toString());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.getLocalizedMessage(), e);
        }
    }

    @RequestMapping("/parseCreditNotify/")
    public void parseCreditNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String appId = this.duibaAccountService.getAppIdByAppKey(request.getParameter("appKey")) ;
            CreditTool tool = duiBaCreditService.getCreditTool(appId);
            CreditNotifyParams params = tool.parseCreditNotify(request);//利用tool来解析这个请求
            LOG.info("duiba parseCreditNotify {}", params.toString());
            duiBaCreditService.parseCreditResult(appId,params);
        } catch (Exception e) {
            LOG.info(e.getLocalizedMessage(), e);
        }
    }

    @Autowired
    private DuiBaCreditService duiBaCreditService;

    @Override
    protected String getPrefixPath() {
        return null;
    }
}
