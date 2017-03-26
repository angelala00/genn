package com.gj.course.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBaseDao<T, K> {

	Page<T> getAllList(Pageable pageableObj);
	
	int deleteByPrimaryKey(K id);

	int insert(T record);

	T selectOneById(K id);

	int updateByPrimaryKeySelective(T p);
}