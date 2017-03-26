package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IDuibaAccountDao;
import com.cjteam.mrile.model.DuibaAccount;
import com.cjteam.mrile.model.DuibaAccountExample;
import com.cjteam.mrile.persistence.DuibaAccountMapper;

@Component
public class DuibaAccountDaoImpl implements IDuibaAccountDao {

	@Autowired
	private DuibaAccountMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(DuibaAccount record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(DuibaAccount record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<DuibaAccount> getAllList(Pageable pageableObj) {
		DuibaAccountExample example = new DuibaAccountExample();
		example.setOrderByClause("id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<DuibaAccount> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<DuibaAccount> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<DuibaAccount>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public DuibaAccount selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

}