package com.comm.base.service;

import com.comm.common.result.CommonResult;
import com.comm.model.base.City;
import com.comm.model.base.Label;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface CityService {

    City getById(String id);

    CommonResult add(City label);

    CommonResult update(City label);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String name,Boolean hot);



}
