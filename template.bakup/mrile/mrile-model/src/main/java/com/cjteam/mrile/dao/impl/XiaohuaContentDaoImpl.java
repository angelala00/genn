package com.cjteam.mrile.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IXiaohuaContentDao;
import com.cjteam.mrile.model.XiaohuaContent;
import com.cjteam.mrile.model.XiaohuaContentExample;
import com.cjteam.mrile.persistence.XiaohuaContentMapper;

@Component
public class XiaohuaContentDaoImpl implements IXiaohuaContentDao {

	@Autowired
	private XiaohuaContentMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(XiaohuaContent record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(XiaohuaContent record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<XiaohuaContent> getAllList(Pageable pageableObj) {
		XiaohuaContentExample example = new XiaohuaContentExample();
		example.setOrderByClause("id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<XiaohuaContent> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<XiaohuaContent> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<XiaohuaContent>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public XiaohuaContent selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<XiaohuaContent> getByType(String type, Pageable pageableObj) {
		XiaohuaContentExample example = new XiaohuaContentExample();
		example.createCriteria().andTypeEqualTo(type);
		example.setOrderByClause("id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<XiaohuaContent> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<XiaohuaContent> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<XiaohuaContent>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public int updateByClickGood(int id) {
		String string = "UPDATE xiaohua_content xc SET xc.good=xc.good+1 WHERE xc.id = " + id;
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", string);
		return mapper.executeUpdateBySql(map);
	}

	@Override
	public int updateByClickBad(int id) {
		String string = "UPDATE xiaohua_content xc SET xc.bad=xc.bad+1 WHERE xc.id = " + id;
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", string);
		return mapper.executeUpdateBySql(map);
	}

	@Override
	public XiaohuaContent selectOneByTitle(String title) {
		XiaohuaContentExample example = new XiaohuaContentExample();
		example.createCriteria().andTitleEqualTo(title);
		List<XiaohuaContent> itemList = mapper.selectByExample(example);
		if (itemList != null && itemList.size() > 0) {
			return itemList.get(0);
		} else {
			return null;
		}
	}

}