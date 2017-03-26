package com.gj.course.api.controller;

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

import com.gj.course.model.Course;
import com.gj.course.service.CourseService;
import com.gj.course.web.Pager;

@Controller
@RequestMapping("course")
public class CourseController extends BaseController {
	@Autowired
	private CourseService service;

	@RequestMapping(value = "/list")
	public String list(@ModelAttribute Pager pageinfo,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),pageinfo.getSize());
		Page<Course> coursePage = service.getAllList(pageableObj);
		request.setAttribute("coursePage", coursePage);
		return "course_list";
	}
	@RequestMapping(value = "/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request, HttpServletResponse response) {
		if (iid > 0) {
			Course course = service.getOne(iid);
			request.setAttribute("course", course);
		}
		return "course_edit";
	}
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute Course courseParam,HttpServletRequest request, HttpServletResponse response) {
		service.save(courseParam);
		try {
			response.sendRedirect("/list");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}

}
