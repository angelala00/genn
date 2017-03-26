package com.cjteam.xiao.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by chenlong on 2014/8/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class ExchangeRecordServiceImplTest {
    @Autowired
    private ExchangeRecordService exchangeRecordService;
    @Test
    public void a(){
    }
}
