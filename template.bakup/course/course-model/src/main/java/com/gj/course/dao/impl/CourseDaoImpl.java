package com.gj.course.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.gj.course.dao.ICourseDao;
import com.gj.course.model.Course;
import com.gj.course.model.CourseExample;
import com.gj.course.persistence.CourseMapper;

@Component
public class CourseDaoImpl implements ICourseDao {

	@Autowired
	private CourseMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Course record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Course record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Course> getAllList(Pageable pageableObj) {
		CourseExample example = new CourseExample();
		example.setOrderByClause(" sort desc, create_time desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Course> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Course> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Course>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public Course selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}