package com.cjteam.xiao.service;

import com.cjteam.xiao.model.DnProduct;
import org.springframework.data.domain.Page;

/**
 * Created by ChenLong
 * Date: 13-11-10
 */
public interface DnProductService {
    DnProduct get(Integer id);

    Page<DnProduct> queryAll(int page,int size);

    DnProduct getByDnId(String dnId);
}
