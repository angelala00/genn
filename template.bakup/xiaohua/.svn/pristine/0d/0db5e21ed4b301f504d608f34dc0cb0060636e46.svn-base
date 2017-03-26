package com.cjteam.xiao.web.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
public class ProductsVo extends ResponseVo {
    private List<ProductVo> products;

    public ProductsVo() {
    }

    public List<ProductVo> getProducts() {
        return products;
    }

    public void setProducts(List<ProductVo> products) {
        this.products = products;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(products) ? 0 : 1;
    }

}
