package com.cjteam.xiao.service;


import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.util.PageBasicInfo;
import org.springframework.data.domain.PageImpl;

public interface DailyRegionIntegralLimitService extends BasicService<DailyRegionIntegralLimit> {
	public PageImpl<DailyRegionIntegralLimit> getPage(String appId, PageBasicInfo PageBasicInfo, String cityName) ;
	public DailyRegionIntegralLimit get(Integer id);

    void changeLimitValue(Integer id, Integer score);
}
