package com.comm.recruit.service;

import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Recruit;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface RecruitService {

    Recruit getById(String id);

    CommonResult add(Recruit recruit);

    CommonResult update(Recruit recruit);

    CommonResult deleteById(String id);

    List getAll();

    List getRecommendList();

    List getNewList();

    CommonResult search(Integer page, Integer size, String id, String jobName);


}
