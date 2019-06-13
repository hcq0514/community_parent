package com.comm.common.exception.code;

import com.comm.common.result.ResultCode;
import io.swagger.annotations.ApiParam;
import lombok.ToString;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@ToString
public enum RecruitExceptionCode implements ResultCode {
    //ENTERPRISE异常从4401开始
    ENTERPRISE_NOT_EXIST(false, 4401, "公司不存在"),
    ENTERPRISE_ALREADY_EXIST(false, 4402, "公司已存在！"),
    //专栏COLUMN异常从4411开始
    RECRUIT_NOT_EXIST(false, 4411, "职位不存在"),
    RECRUIT_ALREADY_EXIST(false, 4412, "职位已存在！");
    @ApiParam("是否成功")
    boolean success;
    @ApiParam("操作代码")
    int code;
    @ApiParam("提示信息")
    String message;

    RecruitExceptionCode(boolean success, int code, String message) {
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
