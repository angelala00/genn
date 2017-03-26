package com.cjteam.mrile.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cjteam.mrile.model.XiaohuaContent;
import com.cjteam.mrile.service.XiaohuaContentService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("")
public class XiaohuaContentController extends BaseController {
	@Autowired
	private XiaohuaContentService xiaohuaContentService;

	@Value("${mrile.PICBasicPath}")
	private String PICBasicPath;
	@Value("${mrile.PICUrlBasic}")
	private String PICUrlBasic;

	@RequestMapping(value = "/")
	public String list(@ModelAttribute Pager pageinfo,
			HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),
				pageinfo.getSize());
		Page<XiaohuaContent> xiaohuaContentPage = xiaohuaContentService
				.getByType("text", pageableObj);
		request.setAttribute("items", xiaohuaContentPage);
		return "xiaohua";
	}
	@RequestMapping(value = "/qutu")
	public String qutu(@ModelAttribute Pager pageinfo,
			HttpServletRequest request, HttpServletResponse response) {
		return "qutu";
	}
	@RequestMapping(value = "/download")
	public String download(@ModelAttribute Pager pageinfo,
			HttpServletRequest request, HttpServletResponse response) {
		return "download";
	}

}
