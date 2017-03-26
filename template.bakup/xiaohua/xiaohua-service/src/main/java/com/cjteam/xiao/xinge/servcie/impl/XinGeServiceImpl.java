package com.cjteam.xiao.xinge.servcie.impl;

import com.cjteam.xiao.xinge.servcie.XinGeService;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;
import com.tencent.xinge.TimeInterval;
import com.tencent.xinge.XingeApp;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ChenLong on 2014/7/16.
 */
@Service
@Transactional(readOnly = true)
public class XinGeServiceImpl implements XinGeService {
    private static final Logger LOG = LoggerFactory.getLogger(XinGeServiceImpl.class);

    @Override
    public void pushMessageIosByXinGe(String token, String message, Boolean isProd) {
        MessageIOS messageEntity = new MessageIOS();
        TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
        messageEntity.addAcceptTime(acceptTime);
        messageEntity.setAlert(message);
        messageEntity.setSound("beep.wav");
        JSONObject result = pushSingleDevice(token, messageEntity, isProd);
        processPushResult(result);
    }

    @Override
    public void pushMessageByXinGe(String token, String title, String message) {
        Message messageEntity = new Message();
        TimeInterval acceptTime = new TimeInterval(0, 0, 23, 59);
        messageEntity.addAcceptTime(acceptTime);
        messageEntity.setTitle(StringUtils.isNoneBlank(title) ? title : "获得积分");
        messageEntity.setContent(message);
        messageEntity.setType(Message.TYPE_MESSAGE);
        JSONObject result = pushSingleDevice(token, messageEntity);
        processPushResult(result);
    }

//    @Override
    public JSONObject pushSingleDevice(String token, Message messageEntity) {
        XingeApp xinge = new XingeApp(2200037981l, "099e380a84db68328a21eb5983d867f1");
        return xinge.pushSingleDevice(token, messageEntity);
    }

//    @Override
    public JSONObject pushSingleDevice(String token, MessageIOS messageEntity, Boolean isProd) {
        XingeApp xinge = new XingeApp(2200037981l, "099e380a84db68328a21eb5983d867f1");
        return xinge.pushSingleDevice(token, messageEntity, isProd ? XingeApp.IOSENV_PROD : XingeApp.IOSENV_DEV);
    }


    private void processPushResult(JSONObject result) {
        try {
			Integer retCode = result.getInt("ret_code");
			if (retCode == null || retCode != 0) {
			    LOG.error("ios message push error: {}", result.toString());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}