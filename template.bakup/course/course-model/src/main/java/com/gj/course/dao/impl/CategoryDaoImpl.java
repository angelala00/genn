package com.gj.course.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.gj.course.dao.ICategoryDao;
import com.gj.course.model.Category;
import com.gj.course.model.CategoryExample;
import com.gj.course.persistence.CategoryMapper;

@Component
public class CategoryDaoImpl implements ICategoryDao {

	@Autowired
	private CategoryMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Category record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Category record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Category> getAllList(Pageable pageableObj) {
		CategoryExample example = new CategoryExample();
		example.setOrderByClause(" sort desc, create_time desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Category> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Category> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Category>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public Category selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}