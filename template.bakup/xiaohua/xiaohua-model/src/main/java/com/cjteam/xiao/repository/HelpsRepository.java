package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.Helps;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-27
 */
@Repository
public interface HelpsRepository extends PagingAndSortingRepository<Helps,Integer> {
    List<Helps> findByAppIdAndValidTrueOrderByOrderDesc(String appId);
}
