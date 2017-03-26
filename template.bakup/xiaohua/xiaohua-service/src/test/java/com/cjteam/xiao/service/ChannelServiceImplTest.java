package com.cjteam.xiao.service;

import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.cjteam.xiao.model.Channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class ChannelServiceImplTest extends TestCase {
    private static final Logger log = LoggerFactory.getLogger(ChannelServiceImplTest.class);

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @Test
    public void testGetAllChannels() {
        Page<Channel> s = channelServiceImpl.getAllChannels(null);
        log.info("testGetAllChannels:" + s.getContent().size());
        assertTrue("error query by isValid field", 9 == s.getContent().size());
    }

    @Test
    public void testGetValidChannels() throws Exception {
        List<Channel> channelList = null;
        Map<String, Integer> recordOccurTimes = new HashMap<String, Integer>();
        int times = 10000;
        while (times-- > 0) {
            channelList = channelServiceImpl.getValidChannels("3njsp");
            assertEquals(2, channelList.size());
            for (Channel c : channelList) {
                if (!recordOccurTimes.containsKey(c.getCode())) {
                    recordOccurTimes.put(c.getCode(), 1);
                } else {
                    recordOccurTimes.put(c.getCode(), recordOccurTimes.get(c.getCode()) + 1);
                }
            }
        }

        for (String channelCode : recordOccurTimes.keySet()) {
            System.out.printf("\n\t%s\t\t%d\n", channelCode, recordOccurTimes.get(channelCode));
        }
    }

    @Autowired
    private ChannelService channelServiceImpl;
}
