package com.cjteam.mrile.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cjteam.mrile.api.controller.vo.IntegralVo;
import com.cjteam.mrile.api.controller.vo.ScoreRecordsResponse;
import com.cjteam.mrile.model.Integral;
import com.cjteam.mrile.model.User;
import com.cjteam.mrile.service.IntegralService;
import com.cjteam.mrile.web.Pager;

@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController {
	@Autowired
	private IntegralService integralService;

	@RequestMapping(value = { "/records" })
	public @ResponseBody ScoreRecordsResponse records(@ModelAttribute Pager pageinfo, String userId) {
		ScoreRecordsResponse response = new ScoreRecordsResponse();
		try {
			Pageable pageableObj = new PageRequest(pageinfo.getPage(), 20);
//			checkModelAttribute(user);
			Page<Integral> sss = integralService.getListByUserId(userId,pageableObj);
			if (sss != null && sss.getContent() != null
					&& sss.getContent().size() > 0) {
				response.setRecords(change(sss.getContent()));
				response.setSuccess(Boolean.TRUE);
				logger.info("records size is {}", response.getRecords().size());
			} else {
				response.setMessage("暂时没有积分记录");
			}
		} catch (Throwable t) {
			logger.error(t.getLocalizedMessage(), t);
			response.setMessage(t.getMessage());
		}
		return response;
	}

	private List<IntegralVo> change(List<Integral> content) {
		List<IntegralVo> s = new ArrayList<IntegralVo>();
		for (Integral c : content) {
			s.add(new IntegralVo(c));
		}
		return s;
	}

	protected void checkModelAttribute(User user) {
		Assert.notNull(user, "invalid param");
		Assert.notNull(user.getUserId(), "missing userId param");
	}
}
