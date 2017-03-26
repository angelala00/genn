package com.cjteam.mrile.api.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.vo.BaseResponseVo;
import com.cjteam.mrile.api.controller.vo.Content;
import com.cjteam.mrile.model.XiaohuaContent;
import com.cjteam.mrile.service.XiaohuaContentService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("/xiaohuaContent")
public class XiaohuaContentController extends BaseController {
	@Autowired
	private XiaohuaContentService xiaohuaContentService;

	@Value("${mrile.PICBasicPath}")
	private String PICBasicPath;
	@Value("${mrile.PICUrlBasic}")
	private String PICUrlBasic;

	/**
	 * @param pageinfo
	 * @param request
	 * @param response
	 * @return TODO 需要重构
	 */
	@RequestMapping(value = "list")
	public @ResponseBody BaseResponseVo list(@ModelAttribute Pager pageinfo, HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(), pageinfo.getSize());
		String type = request.getParameter("type");
		type = "image".equals(type) ? "image" : "text";
		BaseResponseVo baseVo = new BaseResponseVo();
		baseVo.setSuccess(true);
		Page<XiaohuaContent> xiaohuaContentPage = xiaohuaContentService.getByType(type, pageableObj);
		Content content = new Content();
		content.setNumber(xiaohuaContentPage.getNumber());
		content.setTotalPages(xiaohuaContentPage.getTotalPages());
		List<Map<String, Object>> objectList = new ArrayList<Map<String, Object>>();
		for (XiaohuaContent ann : xiaohuaContentPage) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", ann.getId());
			m.put("title", ann.getTitle());
			m.put("bad", ann.getBad());
			m.put("content", ann.getContent());
			m.put("good", ann.getGood());
			m.put("pic", PICUrlBasic + ann.getPic());
			String pic = ann.getPic();
			if (StringUtils.isNotBlank(pic)) {
				try {
					File file = new File(PICBasicPath + pic);
					FileInputStream is = null;
					if (file.exists()) {
						is = new FileInputStream(file);
						BufferedImage src = ImageIO.read(is);
						m.put("wide", src.getWidth());
						m.put("high", src.getHeight());
						is.close();
					}
				} catch (Exception e) {
					logger.warn("has somthing wrong with get pic width the pic path=" + pic);
					e.printStackTrace();
				}
			} else {
				m.put("wide", 0);
				m.put("high", 0);
			}
			objectList.add(m);
		}
		content.setContent(objectList);
		baseVo.setContent(content);
		return baseVo;
	}

	@RequestMapping(value = "clickGood")
	public @ResponseBody String clickGood(int id, @RequestParam(required = false) String userId) {
		xiaohuaContentService.clickGood(id, userId);
		return "";
	}

	@RequestMapping(value = "clickBad")
	public @ResponseBody String clickBad(int id) {
		xiaohuaContentService.clickBad(id);
		return "";
	}

}
