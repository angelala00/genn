package com.cjteam.xiao.repository;

import com.cjteam.xiao.model.StateType;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong
 * Date: 13-12-12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/repository-context.xml" })
@Transactional(rollbackFor = Throwable.class)
public class StateTypeRepositoryTest  extends TestCase{

    @Test
    public void testFindByCode() throws Exception {
        StateType type = stateTypeRepository.findByCode("ok");
        assertEquals(type.getCode(),"ok");
    }
    @Autowired
    StateTypeRepository stateTypeRepository;
}
