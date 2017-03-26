package com.cjteam.xiao.dao;

import com.cjteam.xiao.model.Integral;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Map;


public interface IntegralDao extends Repository<Integral, Integer> {
    Page<Integral> filter(Map<String, Object> filterValues, Pageable pageable);
}
