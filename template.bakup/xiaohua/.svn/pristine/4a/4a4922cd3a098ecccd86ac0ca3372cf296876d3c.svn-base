package com.cjteam.xiao.service;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * Created by WuYimin on 2014/4/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional
public class AdministratorServiceTest extends TestCase {

    @Test
    public void testFindByUsername() throws Exception {
        String username = "phoenix";
        Assert.notNull(administratorService.findByUsername(username));
    }

    @Autowired
    AdministratorService administratorService;
}
