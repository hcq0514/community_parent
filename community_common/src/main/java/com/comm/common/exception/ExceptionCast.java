package com.comm.common.exception;


import com.comm.common.result.ResultCode;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
public class ExceptionCast {
    /**
     *使用此静态方法抛出自定义异常
     */
    public static void cast(ResultCode resultCode){
        throw new CommonException(resultCode);
    }

}
