package ${targetPackage}.api.controller;

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

import ${targetPackage}.model.${tbinfo.tbnameJavaType};
import ${targetPackage}.service.${tbinfo.tbnameJavaType}Service;
import ${targetPackage}.web.Pager;

@Controller
@RequestMapping("${tbinfo.tbnameJavaVar}")
public class ${tbinfo.tbnameJavaType}Controller extends BaseController {
	@Autowired
	private ${tbinfo.tbnameJavaType}Service service;

	@RequestMapping(value = "/list")
	public String list(@ModelAttribute Pager pageinfo,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),pageinfo.getSize());
		Page<${tbinfo.tbnameJavaType}> ${tbinfo.tbnameJavaVar}Page = service.getAllList(pageableObj);
		request.setAttribute("${tbinfo.tbnameJavaVar}Page", ${tbinfo.tbnameJavaVar}Page);
		return "${tbinfo.tbnameJavaVar}_list";
	}
	@RequestMapping(value = "/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request, HttpServletResponse response) {
		if (iid > 0) {
			${tbinfo.tbnameJavaType} ${tbinfo.tbnameJavaVar} = service.getOne(iid);
			request.setAttribute("${tbinfo.tbnameJavaVar}", ${tbinfo.tbnameJavaVar});
		}
		return "${tbinfo.tbnameJavaVar}_edit";
	}
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute ${tbinfo.tbnameJavaType} ${tbinfo.tbnameJavaVar}Param,HttpServletRequest request, HttpServletResponse response) {
		service.save(${tbinfo.tbnameJavaVar}Param);
		try {
			response.sendRedirect("/list");
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return "error";
		}
	}

}
