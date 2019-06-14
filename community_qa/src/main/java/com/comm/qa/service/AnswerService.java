package com.comm.qa.service;

import com.comm.common.result.CommonResult;
import com.comm.model.qa.Answer;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface AnswerService {

    Answer getById(String id);

    CommonResult add(Answer answer);

    CommonResult update(Answer answer);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String problemId);


}
