package com.cjteam.xiao.service;

import com.cjteam.xiao.model.Withdraw;
import com.cjteam.xiao.service.impl.QueryCondition;
import org.springframework.data.domain.Page;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
public interface WithdrawService {
    Page<Withdraw> query(String appId, int i, int pageDefaultSize);

    Withdraw findOne(Integer id);

    Page<Withdraw> query(int pageNo, int pageSize, QueryCondition queryCondition);

    Boolean chargedDrawOuts(Integer id);

    Boolean rollbackDrawOuts(Integer id);

    void chargedDrawOuts(Integer[] id);
}
