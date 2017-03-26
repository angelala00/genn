package com.cjteam.xiao.service.impl;

/**
 * Created by ChenLong
 * Date: 14-1-19
 */
public interface ExchangeSituation {
    public static final String SUCCESS = "OKK";
    public static final String FAILURE = "FAI";

    interface Type {
        public static final String MOBILE = "M";
        public static final String QB = "Q";
        public static final String ZHIFUBAO = "Z";
        public static final String CONSUME = "C";
        public static final String SALE = "S";
    }
}
