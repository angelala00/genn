package com.cjteam.xiao.service;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ChenLong on 14-3-13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/service-context.xml"})
@Transactional(rollbackFor = Throwable.class)
public class IOSMessageProviderTest {
    @Test
    public void testPushMessage() throws Exception {
        List<String> tokens = ImmutableList.of(
//                "d45d491cfd3c2236733591c82422d281568d8121f6d31612284ecf236e01bf5f",
//                "d45d491cfd3c2236733591c82422d281568d8121f6d31612284ecf236e01bf5f",
//                "b87785ee1ecf9bf82c5822651ecedb29b247f7696d557d47b3868d1cabf85eb3",
//                "8b0989dadc99229f989a8fc1cf8d0434dbe1f6c734b6a7164f35fe81d669fc10",
//                "4ycjofrgho4qajuqnysjgjoqyok5zehqgwvivyauolegjwmht3g1oa59hqhkxcar",
//                "4f6c8380d4a3addd0c2c9c652157360236cc813b5d706fd17bf4cca1f6b2905f",
//                "a1de3ff9d6424738117792e89db71a094f9742d37c98569c601d508d183c03a3",
                "a1de3ff9d6424738117792e89db71a094f9742d37c98569c601d508d183c03a3");
        for(String token:tokens){
            messageProvider.push("appId", "测试message", token);
        }
    }

    @Autowired
    IOSMessageProvider messageProvider;
}
