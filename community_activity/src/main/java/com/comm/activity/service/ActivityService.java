package com.comm.activity.service;

import com.comm.common.result.CommonResult;
import com.comm.model.activity.Activity;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface ActivityService {

    Activity getById(String id);

    CommonResult add(Activity activity);

    CommonResult update(Activity activity);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String name,String cityId, Boolean state);


}
