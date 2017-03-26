package com.gj.course.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.gj.course.dao.IChapterDao;
import com.gj.course.model.Chapter;
import com.gj.course.model.ChapterExample;
import com.gj.course.persistence.ChapterMapper;

@Component
public class ChapterDaoImpl implements IChapterDao {

	@Autowired
	private ChapterMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Chapter record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Chapter record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<Chapter> getAllList(Pageable pageableObj) {
		ChapterExample example = new ChapterExample();
		example.setOrderByClause(" sort desc, create_time desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<Chapter> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<Chapter> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<Chapter>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public Chapter selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}