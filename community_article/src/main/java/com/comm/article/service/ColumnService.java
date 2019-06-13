package com.comm.article.service;

import com.comm.common.result.CommonResult;
import com.comm.model.article.Column;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface ColumnService {

    Column getById(String id);

    CommonResult add(Column column);

    CommonResult update(Column column);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String name, Boolean state);

    CommonResult examineColumn(String id);

    List findByUserId(String userId);
}
