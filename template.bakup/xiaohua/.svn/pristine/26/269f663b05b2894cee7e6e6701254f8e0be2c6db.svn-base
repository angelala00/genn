package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Product;
import com.cjteam.xiao.model.ProductType;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
@Repository
public interface ProductTypeRepository extends PagingAndSortingRepository<ProductType,Integer> {
    ProductType findByCode(String code);
}
