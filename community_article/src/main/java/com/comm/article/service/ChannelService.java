package com.comm.article.service;

import com.comm.common.result.CommonResult;
import com.comm.model.article.Channel;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface ChannelService {

    Channel getById(String id);

    CommonResult add(Channel channel);

    CommonResult update(Channel channel);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String name, Boolean state);



}
