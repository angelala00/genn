package com.cjteam.mrile.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cjteam.mrile.model.Integral;

public interface IIntegralDao extends IBaseDao<Integral, Integer> {

	/**
	 * 如果userId为空，查询全部
	 * @param userId
	 * @param object
	 * @return
	 */
	Page<Integral> getListByUserId(String userId, Pageable object);

}