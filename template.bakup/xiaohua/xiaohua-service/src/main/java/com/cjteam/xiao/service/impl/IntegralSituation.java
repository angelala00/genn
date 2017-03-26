package com.cjteam.xiao.service.impl;

/**
 * Created by ChenLong
 * Date: 14-1-15
 */
public interface IntegralSituation {
    String ORDER_EXIST = "OAE";                                                                         //订单已经能存在
    String CHANNEL_TWICEINTERNAL_LIMIT = "CTI";                             //渠道积分间隔控制
    String CHANNEL_MAXSCORETIMES_LIMIT = "CMT";                       //渠道单次最大积分控制
    String CHANNEL_MAXSCOREDAILY_LIMIT = "CMD";                       //渠道每日最多积分控制
    String CHANNEL_REGION_LIMIT = "CRO";                                    //每日渠道积分总量超过区域控制
    String CHANNEL_DAILYTIMES_LIMIT = "CDT";                                    //每日渠道积分次数控制
    String USER_INBLACKLIST = "UIB";                                                            //用户在黑名单
    String USER_NOTEXIST = "UNE";                                                                //用户不存在
    String OK = "OK!";                                                                                              //积分获得成功
    String SIGN_NOT_MATCH="SNM";                                                         // SIGN 表示不匹配
    String SDK_PARAM_ISBLANK="SPB";                                                         // SDK的参数没有，找不到用户
    String APPID_NOT_MACTH="ANM";                                                         // 应用 ID不匹配

    String AD_ALREADY_AWARD = "AAA";                                                         // 该广告在同一广告平台已经获得过积分
    String IP_ALREADY_AWARD = "IAA";                                                         // 同一个IP每个平台每个广告只能得分一次
    String UDID_ADID_EXIST = "UAE";        //该设备号已经完成此广告
}
