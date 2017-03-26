package com.cjteam.xiao.duiba.repository;

import com.cjteam.xiao.duiba.model.AppDuibaAccountReference;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ChenLong on 2014/7/9.
 */
@Repository
public interface AppDuibaAccountReferenceRposiroty  extends PagingAndSortingRepository<AppDuibaAccountReference,Integer>{
    AppDuibaAccountReference findByAppId(String appId);

}
