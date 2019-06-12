package com.comm.user.service;

import com.comm.common.result.CommonResult;
import com.comm.model.user.User;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface UserService {

    User getById(String id);

    CommonResult add(User courseBase);

    CommonResult  update(User courseBase);

    CommonResult  deleteById(String id);
}
