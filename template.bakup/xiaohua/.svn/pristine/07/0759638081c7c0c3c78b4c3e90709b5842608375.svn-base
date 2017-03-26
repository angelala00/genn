package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Product;

/**
 * Created by ChenLong
 * Date: 13-11-1
 */
public interface ProductService {
    Iterable<Product> queryProductsByType(String type);

    Product getOne(Integer id);

    void sale(Integer id, int valid);

    void save(Product product);

    Product findByExchangeMoney(int currentExchangeMoney);
}
