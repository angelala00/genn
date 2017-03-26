package com.cjteam.xiao.web.controller.platform.scoreinterface;

/**
 * Created by ChenLong on 2014/4/3.
 */
public enum ScoreInterfaceEnum {
    OK("OK!", "积分获得成功"),

    USER_INBLACKLIST("UIB", "用户在黑名单"),
    USER_NOTEXIST("UNE", "用户不存在"),

    ORDER_EXIST("OAE", "订单已经能存在"),

    CHANNEL_NOT_EXIST("CNE", "参数对应的渠道不存在"),
    CHANNEL_CODE_PARAM_NOT_EXIST("CPE", "渠道参数错误"),
    APP_CODE_PARAM_NOT_EXIST("APE", "客户端参数错误"),
    CHANNEL_TWICEINTERNAL_LIMIT("CTI", "渠道积分间隔控制"),
    CHANNEL_MAXSCORETIMES_LIMIT("CMT", "渠道单次最大积分控制"),
    CHANNEL_MAXSCOREDAILY_LIMIT("CMD", "渠道每日最多积分控制"),
    CHANNEL_DAILYTIMES_LIMIT("CDT", "每日渠道积分次数控制"),
    CHANNEL_REGION_LIMIT("CRO", "每日渠道积分总量超过区域控制"),

    AD_ALREADY_AWARD("AAA", " 该广告在同一广告平台已经获得过积分"),
    IP_ALREADY_AWARD("IAA", " 同一个IP每个平台每个广告只能得分一次"),

    PARAM_ERROR_ORDERID("PEO", "请求参数异常：没有订单ID"),

    SIGN_NOT_MATCH("SNM", "签名验证不通过"),
    ORDER_ON_PROCESS("OOP", "订单已经在处理中");

    ScoreInterfaceEnum(String code, String meaning) {
        setCode(code);
        setMeaning(meaning);
    }

    private String code;
    private String meaning;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
