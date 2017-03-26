package com.gj.course.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.gj.course.dao.ITagDao;
import com.gj.course.model.Tag;
import com.gj.course.model.TagExample;
import com.gj.course.persistence.TagMapper;

@Component
public class TagDaoImpl implements ITagDao {

	@Autowired
	private TagMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Tag record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Tag record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Tag> getAllList(Pageable pageableObj) {
		TagExample example = new TagExample();
		example.setOrderByClause(" sort desc, create_time desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Tag> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Tag> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Tag>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public Tag selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}