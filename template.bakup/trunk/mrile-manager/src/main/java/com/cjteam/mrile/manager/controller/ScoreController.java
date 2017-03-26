package com.cjteam.mrile.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjteam.mrile.model.Integral;
import com.cjteam.mrile.service.IntegralService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {
	@Autowired
	private IntegralService integralService;

	@RequestMapping(value = { "/list" })
	public String records(@ModelAttribute Pager pageinfo,
			HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(), 20);
		String userid = request.getParameter("userid");
		Page<Integral> sss = integralService.getList(userid,pageableObj);
		request.setAttribute("items", sss);
		return "score/list";
	}
}
