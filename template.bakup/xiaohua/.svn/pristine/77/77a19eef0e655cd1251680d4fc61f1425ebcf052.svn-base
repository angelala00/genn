package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    @Query("from Product p where p.code = :code")
    Product getOne(@Param("code") String code);

    @Query("from Product p where p.productType.code = :type and p.valid=1")
    List<Product> queryProductsByType(@Param("type") String type);

    @Modifying
    @Query(nativeQuery = true, value = "update product set valid=:newValid where id=:id")
    void updateValid(@Param("id") Integer id, @Param("newValid") int newValid);

    List<Product> findByProductType_CodeAndValidAndPriceLessThanOrderByPriceAsc(String alipay, int valid, int price);
    Product findByProductType_CodeAndPriceAndValid(String productCode, int price, int valid);

    @Query(nativeQuery = true, value = "select * from product p where p.type = :ptype and p.valid = :valid limit 1")
    Product findByProductType_CodeAndValid(@Param("ptype") String ptype, @Param("valid") Integer valid);
}
