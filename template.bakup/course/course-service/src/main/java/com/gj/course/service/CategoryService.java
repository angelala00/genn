package com.gj.course.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gj.course.dao.ICategoryDao;
import com.gj.course.model.Category;

@Service
public class CategoryService extends BaseService {

	@Autowired
	private ICategoryDao dao;

	public Page<Category> getAllList(Pageable pageableObj) {
		return dao.getAllList(pageableObj);
	}

	public Category getOne(int id) {
		return dao.selectOneById(id);
	}

	public void save(Category itemParam) {
		if (itemParam.getId() != null && itemParam.getId() > 0) {
			dao.updateByPrimaryKeySelective(itemParam);
		} else {
			//initoperate
			itemParam.setCreateTime(new Date());
			dao.insert(itemParam);
		}
		
	}

	public void removeOne(int iid) {
		dao.deleteByPrimaryKey(iid);
	}
}
