package com.comm.common.exception.code;

import com.comm.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum SmsExceptionCode implements ResultCode {
    //专栏COLUMN异常从4411开始
    SMS_APP_NOT_EXIST(false, 4601, "短信应用不存在"),
    SMS_APP_ALREADY_EXIST(false, 4602, "短信应用已不存在！"),
    //专栏COLUMN异常从4411开始
    SMS_TEMPLATE_NOT_EXIST(false, 4611, "短信模版不存在"),
    SMS_TEMPLATE_ALREADY_EXIST(false, 4612, "短信模版已存在！");
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    SmsExceptionCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
    }
