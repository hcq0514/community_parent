package com.comm.recruit.service;

import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Enterprise;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface EnterpriseService {

    Enterprise getById(String id);

    CommonResult add(Enterprise enterprise);

    CommonResult update(Enterprise enterprise);

    CommonResult deleteById(String id);

    List getAll();

    List getHotList();

    CommonResult search(Integer page, Integer size, String id, String name,  Boolean hot);


}
