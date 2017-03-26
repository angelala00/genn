package com.gj.course.manager.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gj.course.manager.controller.vo.BaseResponseVo;
import com.gj.course.model.Tag;
import com.gj.course.service.TagService;
import com.gj.course.web.Pager;

@Controller
@RequestMapping("/tag")
public class TagController extends BaseController {

	@Autowired
	private TagService itemService;

	@RequestMapping(value = "/list")
	public String list(@ModelAttribute Pager pageinfo,
			HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),
				pageinfo.getSize());
		Page<Tag> items = itemService.getAllList(pageableObj);
		request.setAttribute("items", items);
		return "tag_list";
	}

	@RequestMapping("/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request) {
		if (iid > 0) {
			Tag item = itemService.getOne(iid);
			request.setAttribute("item", item);
		}
		return "tag_edit";
	}

	@RequestMapping("/save")
	public @ResponseBody BaseResponseVo save(@ModelAttribute Tag itemParam) {
		BaseResponseVo rvo;
		try {
			itemService.save(itemParam);
			rvo = new BaseResponseVo();
		} catch (Exception e) {
			rvo = new BaseResponseVo(false, 2, "异常");
			e.printStackTrace();
		}
		return rvo;
	}

	@RequestMapping("/remove/{iid}")
	public @ResponseBody BaseResponseVo remove(@PathVariable int iid, HttpServletRequest request) {
		BaseResponseVo rvo;
		try {
			itemService.removeOne(iid);
			rvo = new BaseResponseVo();
		} catch (Exception e) {
			rvo = new BaseResponseVo(false, 2, "异常");
			e.printStackTrace();
		}
		return rvo;
	}
}
