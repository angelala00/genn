package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.Announcement;
import com.cjteam.xiao.model.ExchangeRecord;
import com.cjteam.xiao.repository.AnnouncementRepository;
import com.cjteam.xiao.repository.ExchangeRecordRepository;
import com.cjteam.xiao.service.ExchangeRecordService;
import com.cjteam.xiao.util.PageBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by chenlong on 2014/8/13.
 */
@Service
public class ExchangeRecordServiceImpl  implements ExchangeRecordService{
    @Autowired
    private ExchangeRecordRepository exchangeRecordRepository  ;
    @Override
    public Page<ExchangeRecord> getListByUserId(PageBasicInfo pageBasicInfo, String userId) {
        Pageable pageable =  new PageRequest(pageBasicInfo.getPage(), pageBasicInfo.getSize() , Sort.Direction.DESC    ,ExchangeRecord.Columns.createTime.toString() );
        return exchangeRecordRepository.findByUserUserId(pageable , userId);
    }
    public void save(ExchangeRecord exchangeRecord){
        exchangeRecord.setCreateTime(new Date());
        this.exchangeRecordRepository.save(exchangeRecord) ;
    }
}
