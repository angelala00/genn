package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.DnProduct;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
@Repository
public interface DnProductRepository extends PagingAndSortingRepository<DnProduct,Integer> {
    DnProduct findByDnId(String dnId);
}
