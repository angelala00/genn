package com.cjteam.xiao.web.vo;

import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
public class ProductTypesVo extends ResponseVo {
    private List<ProductTypeVo> productTypeVos;

    public ProductTypesVo() {
    }

    public List<ProductTypeVo> getProductTypeVos() {
        return productTypeVos;
    }

    public void setProductTypeVos(List<ProductTypeVo> productTypeVos) {
        this.productTypeVos = productTypeVos;
    }

    public int getCount() {
        return CollectionUtils.isEmpty(productTypeVos) ? 0 : 1;
    }
}
