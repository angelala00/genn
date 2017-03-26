package com.cjteam.xiao.web.vo;

/**
 * Created by ChenLong on 2014/7/1.
 */
public class ResponseIntegerVo {
    private Integer success = 1;
    private String message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
