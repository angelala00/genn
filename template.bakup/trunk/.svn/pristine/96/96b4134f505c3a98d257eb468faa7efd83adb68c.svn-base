package com.cjteam.mrile.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.cjteam.mrile.dao.IUserDao;
import com.cjteam.mrile.model.User;
import com.cjteam.mrile.model.UserExample;
import com.cjteam.mrile.persistence.UserMapper;

@Component
public class UserDaoImpl implements IUserDao {

	@Autowired
	private UserMapper mapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User record) {
		return mapper.insert(record);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Page<User> getAllList(Pageable pageableObj) {
		UserExample example = new UserExample();
		example.setOrderByClause("id desc");
		long total = 0;
		if (pageableObj != null) {
			total = mapper.countByExample(example);
			example.setLimitClause("LIMIT " + pageableObj.getOffset() + ", " + pageableObj.getPageSize());
		}
		List<User> itemList = mapper.selectByExample(example);
		if (pageableObj == null) {
			total = itemList.size();
		}
		Page<User> itemsPage = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			itemsPage = new PageImpl<User>(itemList, pageableObj, total);
		}
		return itemsPage;
	}

	@Override
	public User selectOneById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public User selectOneByClientUserId(String userId) {
		UserExample example = new UserExample();
		example.createCriteria().andClientUserIdEqualTo(userId);
		example.setLimitClause("LIMIT 1");
		List<User> itemList = mapper.selectByExample(example);
		User item = null;
		if (!CollectionUtils.isEmpty(itemList)) {
			item = itemList.get(0);
		}
		return item;
	}

	@Override
	public int consumeScore(String userId, int consumeScore) {
		String string = "UPDATE user u SET u.surplus = u.surplus - " + consumeScore + " WHERE u.id=" + userId + " and (u.surplus - " + consumeScore + ")  > 0";
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", string);
		return mapper.executeUpdateBySql(map);
	}

	@Override
	public int addScore(String id, int newScore) {
		String string = "UPDATE user u SET u.total_points = u.total_points + " + newScore + ",u.surplus = u.surplus + " + newScore + " WHERE u.client_user_id=" + id;
		Map<String, String> map = new HashMap<String, String>();
		map.put("sql", string);
		return mapper.executeUpdateBySql(map);
	}
}