package com.cjteam.xiao.dao;

import com.cjteam.xiao.model.Withdraw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.Map;


public interface WithdrawDao extends Repository<Withdraw, Integer> {
    Page<Withdraw> filter(Map<String, Object> whereHql, Pageable pageable);
}
