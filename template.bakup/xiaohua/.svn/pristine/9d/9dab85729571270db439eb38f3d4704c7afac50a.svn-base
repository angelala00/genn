package com.cjteam.xiao.web.vo;

import com.cjteam.xiao.stat.model.ComprehensiveStatInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-11-23
 */
public class ComprehensiveStatInfosVO {
    List<ComprehensiveStatInfo> comprehensiveStatInfos;

    public ComprehensiveStatInfosVO() {
        comprehensiveStatInfos = new ArrayList<ComprehensiveStatInfo>();
    }

    public int getCount() {
        return CollectionUtils.isEmpty(comprehensiveStatInfos) ? 0 : comprehensiveStatInfos.size();
    }

    public List<ComprehensiveStatInfo> getComprehensiveStatInfos() {
        return comprehensiveStatInfos;
    }

    public void setComprehensiveStatInfos(List<ComprehensiveStatInfo> comprehensiveStatInfos) {
        this.comprehensiveStatInfos = comprehensiveStatInfos;
    }

    public List<ComprehensiveStatInfo> appendComprehensiveStatInfos(ComprehensiveStatInfo comprehensiveStatInfo) {
        comprehensiveStatInfos.add(comprehensiveStatInfo);
        return comprehensiveStatInfos;
    }
}
