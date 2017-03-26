package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.DnProduct;
import com.cjteam.xiao.repository.DnProductRepository;
import com.cjteam.xiao.service.DnProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong
 * Date: 13-11-10
 */
@Service
@Transactional
public class DnProductServiceImpl implements DnProductService {
    @Override
    public DnProduct get(Integer id) {
        return dnProductRepository.findOne(id);
    }

    @Override
    public Page<DnProduct> queryAll(int page,int size) {
        Pageable pageable = new PageRequest(page,size);
        return dnProductRepository.findAll(pageable);
    }

    @Override
    public DnProduct getByDnId(String dnId) {
        return dnProductRepository.findByDnId(dnId);
    }

    @Autowired
    private DnProductRepository dnProductRepository;
}
