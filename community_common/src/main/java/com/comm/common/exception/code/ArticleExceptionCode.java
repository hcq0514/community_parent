package com.comm.common.exception.code;

import com.comm.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum ArticleExceptionCode implements ResultCode {
    //CHANNEL异常从4201开始
    CHANNEL_NOT_EXIST(false, 4201, "渠道不存在"),
    CHANNEL_ALREADY_EXIST(false, 4202, "渠道已存在！"),
    //专栏COLUMN异常从4111开始
    COLUMN_NOT_EXIST(false, 4211, "专栏不存在"),
    COLUMN_ALREADY_EXIST(false, 4212, "专栏已存在！");
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    ArticleExceptionCode(boolean success, int code, String message) {
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
