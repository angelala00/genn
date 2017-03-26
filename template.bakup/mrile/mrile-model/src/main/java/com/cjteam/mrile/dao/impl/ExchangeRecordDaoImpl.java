package com.cjteam.mrile.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IExchangeRecordDao;
import com.cjteam.mrile.model.ExchangeRecord;
import com.cjteam.mrile.model.ExchangeRecordExample;
import com.cjteam.mrile.model.ExchangeRecordExample.Criteria;
import com.cjteam.mrile.persistence.ExchangeRecordMapper;

@Component
public class ExchangeRecordDaoImpl implements IExchangeRecordDao {

	@Autowired
	private ExchangeRecordMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ExchangeRecord record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(ExchangeRecord record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<ExchangeRecord> getAllList(Pageable pageableObj) {
		return findByUserId(pageableObj, null);
	}

	@Override
	public ExchangeRecord selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<ExchangeRecord> findByUserId(Pageable pageableObj, String userId) {
		ExchangeRecordExample example = new ExchangeRecordExample();
		Criteria c = example.createCriteria();
		if (StringUtils.isNoneBlank(userId)) {
			c.andUserIdEqualTo(userId);
		}
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		example.setOrderByClause(" create_time desc");
		List<ExchangeRecord> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<ExchangeRecord> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<ExchangeRecord>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

}