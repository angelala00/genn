package com.cjteam.xiao.service.impl;


import com.cjteam.xiao.dao.DailyRegionIntegralLimitDao;
import com.cjteam.xiao.model.DailyRegionIntegralLimit;
import com.cjteam.xiao.repository.DailyRegionIntegralLimitRepository;
import com.cjteam.xiao.service.DailyRegionIntegralLimitService;
import com.cjteam.xiao.util.PageBasicInfo;
import com.cjteam.xiao.util.WhereImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class DailyRegionIntegralLimitServiceImpl extends BasicServiceImpl<DailyRegionIntegralLimit> implements DailyRegionIntegralLimitService {
	@Resource
	DailyRegionIntegralLimitRepository dailyRegionIntegralLimitRepository ; 
	@Autowired
	DailyRegionIntegralLimitServiceImpl(DailyRegionIntegralLimitDao dailyRegionIntegralLimitDao) {
		super(dailyRegionIntegralLimitDao);
	}
	public PageImpl<DailyRegionIntegralLimit> getPage(String appId,PageBasicInfo PageBasicInfo , String cityName){
		WhereImpl where = new WhereImpl() ;
        where.equals("appId",appId);
		if (cityName!=null){
			where.and().like("cityName", cityName) ;
		}
		return this.getPage(PageBasicInfo, where) ;
	}

    @Override
    public void changeLimitValue(Integer id, Integer newLimit) {
        if (id == null || newLimit == null || id <= 0 || newLimit <= 0)
            return;
        DailyRegionIntegralLimit limit = get(id);
        limit.setScore(newLimit);
        dailyRegionIntegralLimitRepository.save(limit);
    }

    @Override
	public DailyRegionIntegralLimit get(Integer id) {
		return this.dailyRegionIntegralLimitRepository.findById(id);
	}
}
