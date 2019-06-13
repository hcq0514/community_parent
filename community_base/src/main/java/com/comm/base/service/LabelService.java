package com.comm.base.service;

import com.comm.common.result.CommonResult;
import com.comm.model.base.Label;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface LabelService {

    Label getById(String id);

    CommonResult add(Label label);

    CommonResult update(Label label);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String loginName, boolean state,boolean recommend);

    List getTopList();

    List getValidList();

}
