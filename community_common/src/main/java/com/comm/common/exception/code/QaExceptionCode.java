package com.comm.common.exception.code;

import com.comm.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum QaExceptionCode implements ResultCode {
    //PROBLEM异常从4501开始
    PROBLEM_NOT_EXIST(false, 4401, "问题不存在"),
    PROBLEM_ALREADY_EXIST(false, 4402, "问题已存在！"),
    //回答异常从4511开始
    ANSWER_NOT_EXIST(false, 4411, "回答不存在"),
    ANSWER_ALREADY_EXIST(false, 4412, "回答已存在！");
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    QaExceptionCode(boolean success, int code, String message) {
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
