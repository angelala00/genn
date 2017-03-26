package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IDuibaCreditConsumeDao;
import com.cjteam.mrile.model.DuibaCreditConsume;
import com.cjteam.mrile.model.DuibaCreditConsumeExample;
import com.cjteam.mrile.persistence.DuibaCreditConsumeMapper;

@Component
public class DuibaCreditConsumeDaoImpl implements IDuibaCreditConsumeDao {

	@Autowired
	private DuibaCreditConsumeMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DuibaCreditConsume record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(DuibaCreditConsume record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<DuibaCreditConsume> getAllList(Pageable pageableObj) {
		DuibaCreditConsumeExample example = new DuibaCreditConsumeExample();
		example.setOrderByClause("id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<DuibaCreditConsume> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<DuibaCreditConsume> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<DuibaCreditConsume>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public DuibaCreditConsume selectOneById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

}