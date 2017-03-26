package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.About;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * Created by ChenLong on 2014/7/7.
 */
public interface AboutRepository  extends PagingAndSortingRepository<About,Integer>{
    List<About> findByAppId(String appId);
}
