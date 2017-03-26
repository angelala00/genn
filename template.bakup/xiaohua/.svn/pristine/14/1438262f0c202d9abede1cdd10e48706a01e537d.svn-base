package com.cjteam.xiao.web.controller.admin;

import com.cjteam.xiao.model.Product;
import com.cjteam.xiao.service.ProductService;
import com.cjteam.xiao.util.PageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Controller("adminProductController")
@RequestMapping("/admin/products")
@PreAuthorize(
        "hasRole('manager') ")
public class ProductsController {
    private static final Logger log = LoggerFactory.getLogger(ProductsController.class);

    @RequestMapping(value = {"/list", "/", "/all",""})
    public String products(ModelMap map) {
        log.info("list data");
        map.put("list", productService.queryProductsByType(null));
        return "/admin/products/list";
    }

    @RequestMapping("/list/{type:\\w+}")
    public String products(@PathVariable String type, ModelMap map) {
        log.info("product controller query by type {}", type);
        map.put("list", productService.queryProductsByType(type));
        return "/admin/products/list";
    }

    @RequestMapping("/view/{type:\\w+}")
    public String view(@PathVariable Integer id, ModelMap map) {
        log.info("view id is {} ", id);
        map.put(PageConstant.VIEW_ENTITY, productService.getOne(id));
        return "/admin/products/view";
    }

    @RequestMapping("/sale/{id:\\w+}")
    public String sale(@PathVariable Integer id, ModelMap map) {
        log.info("sale product id is {} ", id);
        productService.sale(id,1);
        return "redirect:/admin/products/list";
    }

    @RequestMapping("/unsale/{id:\\w+}")
    public String un_sale(@PathVariable Integer id, ModelMap map) {
        log.info("un-sale product id is {} ", id);
        productService.sale(id,0);
        return "redirect:/admin/products/list";
    }

    @RequestMapping("/edit/{id:\\w+}")
    public String edit(@PathVariable Integer id, ModelMap map) {
        log.info("un-sale product id is {} ", id);
        map.put(PageConstant.VIEW_ENTITY, productService.getOne(id));
        return "/admin/products/edit";
    }

    @RequestMapping("/update")
    public String update(Product product, ModelMap map) {
        log.info("update  product id ");
        log.debug("model attribute product {}",product);
        if (product.getId() != null) {
            log.debug("update product");
            Product dbP = productService.getOne(product.getId());
            dbP.setName(product.getName());
            dbP.setPrice(product.getPrice());
            dbP.setRealPrice(product.getRealPrice());
            dbP.setScore(product.getScore());
            dbP.setInfo(product.getInfo());
//            if (product.getDnProduct() != null && product.getDnProduct().getDnId() != null) {
            dbP.setDnProduct(product.getDnProduct());
            dbP.setQuantity(product.getQuantity());
//            }
            dbP.setValid(product.getValid());
            product = dbP;//data exchange
        } else {
            log.debug("new sale product");
        }
        productService.save(product);
        return "redirect:/admin/products/list";
    }



    @Autowired
    private ProductService productService;
}
