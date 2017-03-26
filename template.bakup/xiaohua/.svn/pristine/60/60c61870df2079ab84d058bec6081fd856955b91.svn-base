package com.cjteam.xiao.service.impl;

import com.google.common.base.Joiner;
import com.cjteam.xiao.dao.WithdrawDao;
import com.cjteam.xiao.model.DoubleNineAgent;
import com.cjteam.xiao.model.Withdraw;
import com.cjteam.xiao.repository.WithdrawRepository;
import com.cjteam.xiao.service.StateTypeService;
import com.cjteam.xiao.service.UserService;
import com.cjteam.xiao.service.WithdrawService;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
@Service("withdrawService")
@Transactional
public class WithdrawServiceImpl implements WithdrawService {
    private static final Logger LOG = LoggerFactory.getLogger(WithdrawServiceImpl.class);

    @Override
    public Page<Withdraw> query(String appId, int pageNo, int pageSize) {
        QueryCondition queryCondition = new QueryCondition();
        queryCondition.setAppId(appId);
        return query(pageNo, pageSize, queryCondition);
    }

    @Override
    public Page<Withdraw> query(int pageNo, int pageSize, QueryCondition queryCondition) {
        Pageable pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "state.order", "createTime");
        if (null == queryCondition){
            LOG.debug("trace route is query condition is null");
            return withdrawRepository.findByAppId(queryCondition.getAppId(),pageable);
        }
        LOG.debug("trace route is query condition is not null");
        Map<String, Object> filterValues = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(queryCondition.getAppId())) {
            filterValues.put(" user.appId=", queryCondition.getAppId());
        }
        if (StringUtils.isNotBlank(queryCondition.getName())) {
            filterValues.put(" user.nickName=", queryCondition.getName());
        }
        if (StringUtils.isNotBlank(queryCondition.getMobile())) {
            filterValues.put(" user.mobilePhone= ", queryCondition.getMobile());
        }
        if (StringUtils.isNotBlank(queryCondition.getPayNo())) {
            filterValues.put(" alipayNo= ", queryCondition.getPayNo());
        }
        if (null != queryCondition.getAfterDate()) {
            filterValues.put(" createTime>= ", queryCondition.getAfterDate());
        }
        if (null != queryCondition.getBeforeDate()) {
            filterValues.put(" createTime< ", queryCondition.getBeforeDate());
        }
        if (queryCondition.isUnPaid()) {
            filterValues.put(" state.code= ", "doing");
            pageable = new PageRequest(pageNo, pageSize, Sort.Direction.DESC, "alipayNo");
        }
        LOG.debug("filter key set is {} ",Joiner.on(",").join(filterValues.keySet().iterator()));
        LOG.debug("filter value collection is {} ",Joiner.on(",").join(filterValues.values().iterator()));
        return withdrawDao.filter(filterValues, pageable);
    }

    @Override
    public Boolean chargedDrawOuts(Integer id) {
        return changeDrawState(id, DoubleNineAgent.STATE_OK);
    }

    private Boolean changeDrawState(Integer id, String state) {
        Withdraw withdraw = withdrawRepository.findOne(id);
        Assert.notNull(withdraw, "draw not exist " + id);
        withdraw.setState(stateTypeService.getStateTypeByCode(state));
        if (null != withdraw.getExchange()) {
            withdraw.getExchange().setState(withdraw.getState());
            withdraw.getExchange().setProcessingTime(new Date());
        }
        withdraw.setCheckTime(new Date());
        withdrawRepository.save(withdraw);
        if (StringUtils.equals(DoubleNineAgent.STATE_RETURN, state)) {
            LOG.info("rollback aplipay draw out,draw id is {}", id);
            Assert.isTrue(userService.reduceUserScore(withdraw.getUser(), 0 - withdraw.getScoreCost()),"减积分失败");
        }
        return true;
    }
    @Override
    public void chargedDrawOuts(Integer[] ids) {
        if (ArrayUtils.isEmpty(ids)) {
            LOG.debug("paras is empty");
        }
        for (Integer id : ids) {
            changeDrawState(id, DoubleNineAgent.STATE_OK);
        }
    }

    @Override
    public Boolean rollbackDrawOuts(Integer id) {
        return changeDrawState(id, DoubleNineAgent.STATE_RETURN);
    }

    @Override
    public Withdraw findOne(Integer id) {
        return withdrawRepository.findOne(id);
    }

    @Resource(name = "withdrawRepository")
    WithdrawRepository withdrawRepository;
    @Autowired
    StateTypeService stateTypeService;
    @Autowired
    UserService userService;
    @Resource(name = "withdrawDao")
    WithdrawDao withdrawDao;
}
