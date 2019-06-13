package com.comm.common.exception.code;

import com.comm.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum BaseExceptionCode implements ResultCode {
    //label异常从4101开始
    LABEL_NOT_EXIST(false, 4101, "标签不存在"),
    LABEL_ALREADY_EXIST(false, 4102, "标签已存在！"),
    //city异常从4111开始
    CITY_NOT_EXIST(false, 4111, "城市不存在"),
    CITY_ALREADY_EXIST(false, 4112, "城市已存在！");
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    BaseExceptionCode(boolean success, int code, String message) {
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
