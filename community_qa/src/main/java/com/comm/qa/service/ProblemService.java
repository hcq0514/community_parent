package com.comm.qa.service;

import com.comm.common.result.CommonResult;
import com.comm.model.qa.Problem;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface ProblemService {

    Problem getById(String id);

    CommonResult add(Problem problem);

    CommonResult update(Problem problem);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String title);


}
