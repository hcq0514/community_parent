package com.comm.activity.service.impl;

import com.comm.activity.dao.ActivityDao;
import com.comm.activity.service.ActivityService;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.ActivityExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.activity.Activity;
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
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    ActivityDao activityDao;

    @Override
    public Activity getById(String id) {
        Optional<Activity> optional = activityDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Activity activity) {
        Activity base = activityDao.save(activity);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Activity activity) {
        Optional<Activity> activityOptional = activityDao.findById(activity.getId());
        if (!activityOptional.isPresent()) {
            ExceptionCast.cast(ActivityExceptionCode.ACTIVITY_NOT_EXIST);
        }
        Activity update = activityDao.save(activity);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Activity> activityOptional = activityDao.findById(pageId);
        if (!activityOptional.isPresent()) {
            ExceptionCast.cast(ActivityExceptionCode.ACTIVITY_NOT_EXIST);
        }
        activityDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return activityDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String name, String cityId, Boolean state) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Activity activity = new Activity();
        if (StringUtils.isNotEmpty(id)) {
            activity.setId(id);
        }
        if (StringUtils.isNotEmpty(name)) {
            activity.setName(name);
        }
        if (StringUtils.isNotEmpty(cityId)) {
            activity.setCityId(cityId);
        }
        if (state != null) {
            activity.setState(state);
        }
        //创建条件实例
        Example<Activity> example = Example.of(activity, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Activity> all = activityDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}
