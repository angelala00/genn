package com.gj.course.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gj.course.model.Chapter;
import com.gj.course.service.ChapterService;
import com.gj.course.web.Pager;

@Controller
@RequestMapping("chapter")
public class ChapterController extends BaseController {
	@Autowired
	private ChapterService service;

	@RequestMapping(value = "/list")
	public String list(@ModelAttribute Pager pageinfo,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),pageinfo.getSize());
		Page<Chapter> chapterPage = service.getAllList(pageableObj);
		request.setAttribute("chapterPage", chapterPage);
		return "Chapter_list";
	}
	@RequestMapping(value = "/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request, HttpServletResponse response) {
		if (iid > 0) {
			Chapter chapter = service.getOne(iid);
			request.setAttribute("chapter", chapter);
		}
		return "Chapter_edit";
	}
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute Chapter chapterParam,HttpServletRequest request, HttpServletResponse response) {
		service.save(chapterParam);
		try {
			response.sendRedirect("/list");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}

}
