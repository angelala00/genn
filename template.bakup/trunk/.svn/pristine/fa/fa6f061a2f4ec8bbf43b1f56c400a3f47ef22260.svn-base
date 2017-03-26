package com.cjteam.mrile.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.util.DateUtil;
import com.cjteam.mrile.api.controller.vo.BaseResponseVo;
import com.cjteam.mrile.api.controller.vo.Content;
import com.cjteam.mrile.model.Announcement;
import com.cjteam.mrile.service.AnnouncementService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends BaseController{
	@Autowired
	private AnnouncementService announcementService;

	@RequestMapping("getOne")
	public @ResponseBody BaseResponseVo getOne(@RequestParam(required = true) int id) {
		BaseResponseVo baseVo = new BaseResponseVo();
		Announcement announcement;
		if (id != 0) {
			announcement = announcementService.getOne(id);
		} else {
			announcement = announcementService.getRecommend();
		}
		if (announcement != null) {
			baseVo.setContent(announcement.getContent());
		} else {
			baseVo.setSuccess(Boolean.FALSE);
			baseVo.setMessage("没有公告");
		}
		return baseVo;
	}

	@RequestMapping("list")
	public @ResponseBody BaseResponseVo list(@ModelAttribute Pager pageinfo) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),
				pageinfo.getSize());
		BaseResponseVo baseVo = new BaseResponseVo();
		baseVo.setSuccess(true);
		Content content = new Content();
		Page<Announcement> announcementPage = announcementService
				.getAllList(pageableObj);
		List<Map<String, Object>> objectList = new ArrayList<Map<String, Object>>();
		for (Announcement ann : announcementPage) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", ann.getId());
			m.put("title", ann.getTitle());
			m.put("createTime",
					DateUtil.format(ann.getCreateTime(), "yyyy-MM-dd"));
			objectList.add(m);
		}
		content.setContent(objectList);
		content.setNumber(announcementPage.getNumber());
		content.setTotalPages(announcementPage.getTotalPages());
		baseVo.setContent(content);

		return baseVo;
	}
}
