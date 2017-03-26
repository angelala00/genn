package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.Advertisement;
import com.cjteam.xiao.repository.AdvertisementsRepository;
import com.cjteam.xiao.service.AdvertisementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong
 * Date: 13-11-4
 */
@Service
@Transactional
public class AdvertisementsServiceImpl implements AdvertisementsService {
    @Override
    public Page<Advertisement> getAdvertisementsByChannel(String channel) {
        return advertisementsRepository.findAll(new PageRequest(0, 100));
    }

    @Autowired
    AdvertisementsRepository advertisementsRepository;
}
