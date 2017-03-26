package com.cjteam.xiao.service.doublenine;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ChenLong
 * Date: 13-10-22
 */
public abstract class DoubleNineResponse {
    public abstract DoubleNineRequest getRequest();

    public String getResultMeaning(Integer result) {
        return Result.getResultState(result).getMeaning();
    }

    private enum Result {
        SUCCESS(0, "成功"),
        PARAM_ERROR(1, "参数错误"),
        PARTNER_NOT_EXIST(2, "代理商ID 不存在"),
        ACCESS_FORBID(3, "访问被拒绝"),
        TRADE_DUPLICATE(4, "交易序号重复"),
        SIGN_INVALID(5, "签名验证失败"),
        LACK_BALANCE(6, "代理商余额不足"),
        MOBILE_NUMBER(7, "不能对该手机号进行充值"),
        FAIL(8, "充值失败"),
        LACK_POWER(9, "没有权限访问该服务"),
        TIME_OUT(10, "超时取消充值"),
        NO_DENOMINATION(11, "面额不存在"),
        TRADE_NOT_EXIST(12, "交易订单不存在"),
        OVERSTEP(13, "该手机号超过24 小时内最大充值金额");

        private Integer code;
        private String meaning;
        private static List<Result> container = new ArrayList<Result>();

        static {
            container.add(SUCCESS);
            container.add(PARAM_ERROR);
            container.add(PARTNER_NOT_EXIST);
            container.add(ACCESS_FORBID);
            container.add(TRADE_DUPLICATE);

            container.add(SIGN_INVALID);
            container.add(LACK_BALANCE);
            container.add(MOBILE_NUMBER);
            container.add(FAIL);

            container.add(LACK_POWER);
            container.add(TIME_OUT);
            container.add(NO_DENOMINATION);
            container.add(TRADE_NOT_EXIST);
            container.add(OVERSTEP);
        }

        Result(Integer code, String meaning) {
            this.code = code;
            this.meaning = meaning;
        }

        public static Result getResultState(Integer code) {
            if (null != code && code >= 0 && code < container.size()) {
                return container.get(code);
            } else {
                throw new RuntimeException("充值查询接口返回的状态码错误，无法识别充值结果状态. state=" + code);
            }
        }

        private String getMeaning() {
            return meaning;
        }

        private Integer getCode() {
            return code;
        }
    }
}
