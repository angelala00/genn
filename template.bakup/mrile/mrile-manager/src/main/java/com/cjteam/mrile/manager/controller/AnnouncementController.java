package com.cjteam.mrile.manager.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.manager.controller.vo.BaseResponseVo;
import com.cjteam.mrile.model.Announcement;
import com.cjteam.mrile.service.AnnouncementService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController {
	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping("/list")
	public String list(@ModelAttribute Pager pageinfo,
			HttpServletRequest request) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),
				pageinfo.getSize());
		Page<Announcement> items = announcementService.getAllList(pageableObj);
		request.setAttribute("items", items);
		return "announcement/list";
	}

	@RequestMapping("/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request) {
		Announcement item = announcementService.getOne(iid);
		request.setAttribute("item", item);
		return "announcement/edit";
	}

	@RequestMapping("/save")
	public @ResponseBody BaseResponseVo save(@ModelAttribute Announcement itemParam) {
		BaseResponseVo rvo;
		try {
			announcementService.save(itemParam);
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
			announcementService.removeOne(iid);
			rvo = new BaseResponseVo();
		} catch (Exception e) {
			rvo = new BaseResponseVo(false, 2, "异常");
			e.printStackTrace();
		}
		return rvo;
	}
}
