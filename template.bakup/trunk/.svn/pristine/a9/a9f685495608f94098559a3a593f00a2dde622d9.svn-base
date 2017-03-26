package com.cjteam.mrile.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cjteam.mrile.dao.IXiaohuaContentDao;
import com.cjteam.mrile.model.XiaohuaContent;

@Service
public class XiaohuaContentService extends BaseService {

	@Autowired
	private IXiaohuaContentDao xiaohuaContentDao;

	@Autowired
	private IntegralService integralService;

	@Autowired
	private UserService userService;

	private int maxscorefromclickgood = 10;
	private int getscorefromclickgood = 1;
	private String channelfromclickgood = "DIANZAN";

	public XiaohuaContent getOne(int id) {
		return xiaohuaContentDao.selectOneById(id);
	}

	@Transactional
	public int clickGood(int id, String userId) {
		int accessNumber = integralService.countByUserIdAndChannelCodeAndCreateTime(userId, channelfromclickgood);
		if (accessNumber < maxscorefromclickgood) {
			integralService.addIntegral(userId, channelfromclickgood, getscorefromclickgood);
			userService.addScore(userId, getscorefromclickgood);
		}
		return xiaohuaContentDao.updateByClickGood(id);
	}

	public int clickBad(int id) {
		return xiaohuaContentDao.updateByClickBad(id);
	}

	public Page<XiaohuaContent> getByType(String type, Pageable pageableObj) {
		return xiaohuaContentDao.getByType(type, pageableObj);
	}

	public void save(XiaohuaContent xc) {
		XiaohuaContent temp = xiaohuaContentDao.selectOneByTitle(xc.getTitle());
		if (temp != null) {
			System.out.println("touch:"+temp);
			temp.setCreateTime(new Date());
			xiaohuaContentDao.updateByPrimaryKeySelective(temp);
		} else {
			System.out.println("insert:"+xc);
			xiaohuaContentDao.insert(xc);
		}
	}
}
