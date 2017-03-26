package ${targetPackage}.manager.controller;

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

import ${targetPackage}.manager.controller.vo.BaseResponseVo;
import ${targetPackage}.model.${tbinfo.tbnameJavaType};
import ${targetPackage}.service.${tbinfo.tbnameJavaType}Service;
import ${targetPackage}.web.Pager;

@Controller
@RequestMapping("/${tbinfo.tbnameJavaVar}")
public class ${tbinfo.tbnameJavaType}Controller extends BaseController {

	@Autowired
	private ${tbinfo.tbnameJavaType}Service itemService;

	@RequestMapping(value = "/list")
	public String list(@ModelAttribute Pager pageinfo,
			HttpServletRequest request, HttpServletResponse response) {
		Pageable pageableObj = new PageRequest(pageinfo.getPage(),
				pageinfo.getSize());
		Page<${tbinfo.tbnameJavaType}> items = itemService.getAllList(pageableObj);
		request.setAttribute("items", items);
		return "${tbinfo.tbnameJavaVar}_list";
	}

	@RequestMapping("/edit/{iid}")
	public String edit(@PathVariable int iid, HttpServletRequest request) {
		if (iid > 0) {
			${tbinfo.tbnameJavaType} item = itemService.getOne(iid);
			request.setAttribute("item", item);
		}
		return "${tbinfo.tbnameJavaVar}_edit";
	}

	@RequestMapping("/save")
	public @ResponseBody BaseResponseVo save(@ModelAttribute ${tbinfo.tbnameJavaType} itemParam) {
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
