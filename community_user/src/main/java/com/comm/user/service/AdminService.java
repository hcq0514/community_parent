package com.comm.user.service;

import com.comm.common.result.CommonResult;
import com.comm.model.user.Admin;
import com.comm.model.user.User;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface AdminService {

    Admin getById(String id);

    CommonResult add(Admin courseBase);

    CommonResult  update(Admin courseBase);

    CommonResult  deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String loginName, boolean state);
}
