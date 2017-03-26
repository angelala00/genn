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

import com.gj.course.model.Category;
import com.gj.course.service.CategoryService;
import com.gj.course.web.Pager;

@Controller
@RequestMapping("category")
public class CategoryController extends BaseController {
	@Autowired
	private CategoryService service;

	@RequestMapping(value = "/list")
	public String list(@ModelAttribute Pager pageinfo,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),pageinfo.getSize());
		Page<Category> categoryPage = service.getAllList(pageableObj);
		request.setAttribute("categoryPage", categoryPage);
		return "Category_list";
	}
	@RequestMapping(value = "/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request, HttpServletResponse response) {
		if (iid > 0) {
			Category category = service.getOne(iid);
			request.setAttribute("category", category);
		}
		return "Category_edit";
	}
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute Category categoryParam,HttpServletRequest request, HttpServletResponse response) {
		service.save(categoryParam);
		try {
			response.sendRedirect("/list");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}

}
