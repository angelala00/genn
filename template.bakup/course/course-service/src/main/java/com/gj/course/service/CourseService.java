package com.gj.course.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gj.course.dao.ICourseDao;
import com.gj.course.model.Course;

@Service
public class CourseService extends BaseService {

	@Autowired
	private ICourseDao dao;

	public Page<Course> getAllList(Pageable pageableObj) {
		return dao.getAllList(pageableObj);
	}

	public Course getOne(int id) {
		return dao.selectOneById(id);
	}

	public void save(Course itemParam) {
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
