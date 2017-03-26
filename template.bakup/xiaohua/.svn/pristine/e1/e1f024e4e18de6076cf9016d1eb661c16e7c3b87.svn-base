package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.Product;
import com.cjteam.xiao.repository.DnProductRepository;
import com.cjteam.xiao.repository.ProductRepository;
import com.cjteam.xiao.repository.ProductTypeRepository;
import com.cjteam.xiao.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Override
    public Iterable<Product> queryProductsByType(String type) {
        if (StringUtils.isEmpty(type))
            return productRepository.findAll();
        return productRepository.queryProductsByType(type);
    }

    @Override
    public Product getOne(Integer id) {
        return productRepository.findOne(id);
    }

    @Override
    public void sale(Integer id, int newValid) {
        productRepository.updateValid(id,newValid);
    }

    @Override
    public void save(Product product) {
        if (product.getDnProduct() != null && product.getDnProduct().getDnId() != null) {
            product.setDnProduct(dnProductRepository.findByDnId(product.getDnProduct().getDnId()));
            product.setQuantity(product.getQuantity());
        }
        Assert.notNull(product.getProductType(),"product type must not be null");
        Assert.notNull(product.getProductType().getCode(),"product type must not be null");
        product.setProductType(typeRepository.findByCode(product.getProductType().getCode()));
        productRepository.save(product);
    }

    @Override
    public Product findByExchangeMoney(int currentExchangeMoney) {
        return productRepository.findByProductType_CodeAndPriceAndValid("ALIPAY", currentExchangeMoney * 100, 1);
    }

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository typeRepository;
    @Autowired
    DnProductRepository dnProductRepository;
}
