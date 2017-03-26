package com.cjteam.xiao.service.impl;


import com.cjteam.xiao.model.Announcement;
import com.cjteam.xiao.repository.AnnouncementRepository;
import com.cjteam.xiao.service.AnnouncementService;
import com.cjteam.xiao.util.PageBasicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementRepository announcementRepository  ;
    @Override
    public Announcement get(int id) {
        return announcementRepository.findOne(id);
    }
    @Override
    public Announcement getRecommend() {
        return announcementRepository.findByRecommend(Announcement.Recommend.recommend.toString());
    }
    public Page<Announcement> getList(PageBasicInfo pageBasicInfo){
        Pageable pageable =  new PageRequest(pageBasicInfo.getPage(), pageBasicInfo.getSize() , Sort.Direction.DESC   , Announcement.Columns.sort.toString() ,Announcement.Columns.createTime.toString() );
        return announcementRepository.findAll(pageable) ;
    }
}
