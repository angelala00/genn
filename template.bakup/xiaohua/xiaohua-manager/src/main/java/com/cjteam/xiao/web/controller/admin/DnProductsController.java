package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.service.DnProductService;
import com.cjteam.xiao.util.PageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Controller("adminDnProductsController")
@RequestMapping("/admin/dnproducts")
@PreAuthorize(
        "hasRole('admin') ")
public class DnProductsController {
    private static final Logger log = LoggerFactory.getLogger(DnProductsController.class);

    @RequestMapping(value = {"/list", "/", "/all",""})
    public String list(ModelMap map) {
        log.info("user controller get first page data");
        Page pageResult = dnProductService.queryAll(0, PageConstant.PAGE_DEFAULT_SIZE);
        map.put(PageConstant.VIEW_LIST, pageResult != null ? pageResult.getContent() : null);
        return "/admin/dnproducts/list";
    }

    @RequestMapping(value = {"/list/{no:\\d+}", "/{no:\\d+", "/all/{no:\\d+"})
    public String list(@PathVariable(value = "no") int pageNoe, ModelMap map) {
        log.info("user controller list pager");
        map.put("list", dnProductService);
        return "/admin/dnproducts/list";
    }

    @RequestMapping("/view/{id:\\w+}")
    public String view(@PathVariable Integer id, ModelMap map) {
        log.info("view id {} ", id);
        map.put(PageConstant.VIEW_ENTITY, dnProductService.get(id));
        return "/admin/dnproducts/detail";
    }

    @Autowired
    DnProductService dnProductService;
}
