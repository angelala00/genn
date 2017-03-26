package com.cjteam.xiao.dao;

import com.cjteam.xiao.model.Exchange;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Map;


public interface ExchangeDao extends Repository<Exchange, Integer> {
    Page<Exchange> filter(Map<String, Object> whereCondition, Pageable pageable);
}
