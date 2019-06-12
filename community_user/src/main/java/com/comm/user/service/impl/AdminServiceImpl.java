package com.comm.Admin.service.impl;

import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.UserExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.user.Admin;
import com.comm.user.dao.AdminDao;
import com.comm.user.dao.UserDao;
import com.comm.user.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * @author : hcq
 * @date : 2019/6/12
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public Admin getById(String id) {
        Optional<Admin> optional = adminDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Admin admin) {
        Admin base = adminDao.save(admin);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Admin admin) {
        Optional<Admin> optionAdmin = adminDao.findById(admin.getId());
        if (!optionAdmin.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.ADMIN_NOT_EXIST);
        }
        Admin update = adminDao.save(admin);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Admin> admin = adminDao.findById(pageId);
        if (!admin.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.ADMIN_NOT_EXIST);
        }
        adminDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return adminDao.findAll();
    }

    @Override
    public CommonResult search(Integer page, Integer size, String id, String loginName, boolean state) {

        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("login_name", ExampleMatcher.GenericPropertyMatchers.contains());
        Admin admin = new Admin();
        //站点ID
        if (StringUtils.isNotEmpty(id)) {
            admin.setId(id);
        }
        //站点ID
        if (StringUtils.isNotEmpty(loginName)) {
            admin.setLoginName(loginName);
        }
        admin.setState(state);
        //创建条件实例
        Example<Admin> example = Example.of(admin, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Admin> all = adminDao.findAll(example, pageable);

        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }
}
