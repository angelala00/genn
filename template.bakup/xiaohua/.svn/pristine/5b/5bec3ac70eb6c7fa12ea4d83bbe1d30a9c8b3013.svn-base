package com.cjteam.xiao.web.controller;

import com.cjteam.xiao.model.Integral;
import com.cjteam.xiao.model.ProductType;
import com.cjteam.xiao.model.User;
import com.cjteam.xiao.service.IntegralService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.web.vo.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong Date: 13-10-8
 */
@Controller
@RequestMapping("/score")
public class ScoreController extends BaseController<Integral> {
    private static final Logger log = LoggerFactory.getLogger(ScoreController.class);

	@RequestMapping(value = { "/", "" })
	public @ResponseBody
    ScoreResponseV2 query(@ModelAttribute User user, @RequestParam(value = "product", required = false) String[] productCode) {
        ScoreResponseV2 response = new ScoreResponseV2();
        try {
            Assert.notNull(user, "user parameter null");
            User u = userService.getOne(user.getAppId(), user.getUserId());
            if (u == null) {
                response.setMessage("user is not exist");
            } else {
                response.setScore(u.getSurplus());
                response.setTotally(u.getTotalPoints());
                response.setSuccess(Boolean.TRUE);

                if (productCode != null && productCode.length > 0) {
                    for (String code : productCode) {
                        if (StringUtils.isNotBlank(code)) {
                            if (StringUtils.equals(ProductType.EYE, code)) {
                                response.append(u.getProductEyeCount());
                            } else if (StringUtils.equals(ProductType.TIME, code)) {
                                response.append(u.getProductTimeCount());
                            }
                        }
                    }
                }
            }
        } catch (Throwable t) {
            log.error(t.getLocalizedMessage(), t);
            response.setMessage(t.getLocalizedMessage());
        }
        return response;
    }

	@RequestMapping(value = { "/records" })
	public @ResponseBody
	ScoreRecordsResponse records(@ModelAttribute(value = "user") User user) {
        ScoreRecordsResponse response = new ScoreRecordsResponse();
        try {
            checkModelAttribute(user);

            Page<Integral> sss = integralService.getByUserId(user.getAppId(),user.getUserId());
            if (sss != null && CollectionUtils.isNotEmpty(sss.getContent())) {
                response.setRecords(change(sss.getContent()));
                response.setSuccess(Boolean.TRUE);
                log.info("records size is {}", response.getRecords().size());
            } else {
                response.setMessage("暂时没有积分记录");
            }
        } catch (Throwable t) {
            log.error(t.getLocalizedMessage(),t);
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

    @Override
    protected String getPrefixPath() {
        //todo
        return null;
    }

    @Autowired
	private UserService userService;
	@Autowired
	private IntegralService integralService;
}
