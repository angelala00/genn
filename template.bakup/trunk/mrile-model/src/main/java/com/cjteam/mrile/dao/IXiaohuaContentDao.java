package com.cjteam.mrile.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cjteam.mrile.model.XiaohuaContent;

public interface IXiaohuaContentDao extends IBaseDao<XiaohuaContent, Integer> {

	Page<XiaohuaContent> getByType(String type, Pageable pageableObj);

	int updateByClickGood(int id);

	int updateByClickBad(int id);

	XiaohuaContent selectOneByTitle(String title);
}