package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.model.CrapBet;
import com.cjteam.xiao.model.CrapIssue;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong on 14-3-2.
 */
public class CrapIssuesVo extends ResponseVo {
    private List<CrapIssueVo> crapIssueVos;

    public List<CrapIssueVo> getCrapIssueVos() {
        return crapIssueVos;
    }

    public void setCrapIssueVos(List<CrapIssueVo> crapIssueVos) {
        this.crapIssueVos = crapIssueVos;
    }

    @Override
    public int getCount() {
        return CollectionUtils.isEmpty(getCrapIssueVos()) ? 0 : crapIssueVos.size();
    }

    public void append(List<CrapIssue> issues) {
        if (CollectionUtils.isNotEmpty(issues)) {
            crapIssueVos = new ArrayList<CrapIssueVo>(issues.size());
            for (CrapIssue issue : issues) {
                crapIssueVos.add(new CrapIssueVo(issue));
            }
        }
    }
}
