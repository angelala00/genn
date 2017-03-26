package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.CrapBet;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ChenLong on 14-3-2.
 */
public class CrapBetsVo extends ResponseVo {
    private List<CrapBetVo> crapBetVos;

    public List<CrapBetVo> getCrapBetVos() {
        return crapBetVos;
    }

    public void setCrapBetVos(List<CrapBetVo> crapBetVos) {
        this.crapBetVos = crapBetVos;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(getCrapBetVos()) ? 0 : crapBetVos.size();
    }

    public void append(List<CrapBet> bets) {
        if (CollectionUtils.isNotEmpty(bets)) {
            crapBetVos = new ArrayList<CrapBetVo>(bets.size());
            for (CrapBet bet : bets) {
                crapBetVos.add(new CrapBetVo(bet));
            }
        }
    }
}
