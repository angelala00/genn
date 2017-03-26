package com.cjteam.mrile.api.controller.duiba;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.duiba.credits.sdk.CreditConsumeParams;
import cn.com.duiba.credits.sdk.CreditNotifyParams;
import cn.com.duiba.credits.sdk.CreditQueryParams;
import cn.com.duiba.credits.sdk.CreditTool;

import com.cjteam.mrile.api.controller.BaseController;
import com.cjteam.mrile.service.duiba.DuibaCreditService;

/**
 * Created by ChenLong on 2014/7/5.
 */
@Controller
@RequestMapping("/dui")
public class DuiBaController extends BaseController {

	@Autowired
	private DuibaCreditService duibaCreditService;
	@Value("${mrile.duiba.appKey}")
	private String appKey;
	@Value("${mrile.duiba.appSecret}")
	private String appSecret;

	@RequestMapping("/creditAutoLoginUrl")
	public void creditAutoLoginUrl(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			PrintWriter writer = response.getWriter();
			String userId = request.getParameter("userId");
			String r = duibaCreditService.buildCreditAutoLoginRequest(appKey, appSecret, userId);
			writer.write(r);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage(), e);
		}
	}

	@RequestMapping("/credit")
	public void credit(HttpServletRequest request, HttpServletResponse response) {
		try {
			CreditTool tool = duibaCreditService.getCreditTool(appKey, appSecret);

			CreditQueryParams params = tool.parseCreditQuery(request);// 利用tool来解析这个请求

			logger.info("duiba credit {}", params.toString());
			response.getWriter().write(
					duibaCreditService.credit(params).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getLocalizedMessage(), e);
		}
	}

	@RequestMapping("/creditConsume")
	public void creditConsume(HttpServletRequest request,
			HttpServletResponse response, String description) {

		try {
			request.setCharacterEncoding("utf-8");
			String str = new String(description.getBytes("ISO-8859-1"), "UTF-8");

			System.out.println(request.getParameter("description"));

			CreditTool tool = duibaCreditService.getCreditTool(appKey, appSecret);
			CreditConsumeParams params = tool.parseCreditConsume(request);// 利用tool来解析这个请求
			logger.info("duiba creditConsume {}", params.toString());

			response.getWriter().write(
					duibaCreditService.creditConsume(params).toString());
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getLocalizedMessage(), e);
            try {
				response.getWriter().write(e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping("/parseCreditNotify")
	public void parseCreditNotify(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			CreditTool tool = duibaCreditService.getCreditTool(appKey, appSecret);
			CreditNotifyParams params = tool.parseCreditNotify(request);// 利用tool来解析这个请求
			logger.info("duiba parseCreditNotify {}", params.toString());
			duibaCreditService.parseCreditResult(params);
		} catch (Exception e) {
			logger.info(e.getLocalizedMessage(), e);
		}
	}

}
