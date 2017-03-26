package com.cjteam.xiao.service.impl;

import com.cjteam.xiao.model.FrontApp;
import com.cjteam.xiao.service.AppService;
import com.cjteam.xiao.service.IOSMessageProvider;
import com.cjteam.xiao.xinge.servcie.XinGeService;
import javapns.Push;
import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 14-1-13
 */
@Service
public class IOSMessageProviderImpl implements IOSMessageProvider {
    private static final Logger LOG = LoggerFactory.getLogger(IOSMessageProviderImpl.class);

    @Value("${ios.message.provider.key_store_file}")
    private String keyStoreFilePath;
    @Value("${ios.message.provider.key_pass}")
    private String keyPass;
    @Value("${ios.message.provider.production}")
    private boolean production;

    @Autowired
    private XinGeService xinGeService;

    @Autowired
    private AppService appService;

    @Override
    public void push(String appId, String message, String deviceToken) {
        try {
            FrontApp app = appService.getOne(appId);
            if (StringUtils.equals(FrontApp.notificationType_native, app.getNotificationType())) {

                int badge = 0;//图标小红圈的数值
                String sound = "default";//铃音

                List<String> tokens = new ArrayList<String>();
                tokens.add(deviceToken);
                boolean sendCount = true;

                try
                {
                    PushNotificationPayload payLoad = new PushNotificationPayload();
                    payLoad.addAlert(message); // 消息内容
                    payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
                    if (!StringUtils.isBlank(sound))
                    {
                        payLoad.addSound(sound);//铃音
                    }
                    PushNotificationManager pushManager = new PushNotificationManager();
                    //true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
                    pushManager.initializeConnection(new AppleNotificationServerBasicImpl(keyStoreFilePath, keyPass, false));
                    List<PushedNotification> notifications = new ArrayList<PushedNotification>();
                    // 发送push消息
                    if (sendCount)
                    {
                        Device device = new BasicDevice();
                        device.setToken(tokens.get(0));
                        PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
                        notifications.add(notification);
                    }
                    else
                    {
                        List<Device> device = new ArrayList<Device>();
                        for (String token : tokens)
                        {
                            device.add(new BasicDevice(token));
                        }
                        notifications = pushManager.sendNotifications(payLoad, device);
                    }
                    List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
                    List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
                    int failed = failedNotifications.size();
                    int successful = successfulNotifications.size();
                    pushManager.stopConnection();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }


              //  Push.combined(message, 0, "default", keyStoreFilePath, keyPass, production, deviceToken);
            } else if (StringUtils.equals(FrontApp.notificationType_xinge, app.getNotificationType())) {
                xinGeService.pushMessageIosByXinGe(deviceToken, message, appService.onOnlineStatus(appId));
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOG.warn(e.getLocalizedMessage());
        }
    }
}
