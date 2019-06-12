package com.comm.user.service.impl;

import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.UserExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.user.User;
import com.comm.user.dao.UserDao;
import com.comm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author : hcq
 * @date : 2019/6/12
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User getById(String id) {
        Optional<User> optional = userDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }


    @Override
    public CommonResult add(User user) {
        User base = userDao.save(user);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(User user) {
        Optional<User> optionUser = userDao.findById(user.getId());
        if (!optionUser.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.USER_NOT_EXIST);
        }
        user.setUpdateTime(LocalDateTime.now());
        User update = userDao.save(user);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<User> user = userDao.findById(pageId);
        if (!user.isPresent()) {
            ExceptionCast.cast(UserExceptionCode.USER_ALREADY_EXIST);
        }
        userDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }
}
