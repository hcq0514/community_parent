package com.comm.article.service.impl;

import com.comm.article.dao.ChannelDao;
import com.comm.article.service.ChannelService;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.ArticleExceptionCode;
import com.comm.common.exception.code.BaseExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.article.Channel;
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
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelDao channelDao;

    @Override
    public Channel getById(String id) {
        Optional<Channel> optional = channelDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Channel channel) {
        Channel base = channelDao.save(channel);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Channel channel) {
        Optional<Channel> channelOptional = channelDao.findById(channel.getId());
        if (!channelOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.CHANNEL_NOT_EXIST);
        }
        Channel update = channelDao.save(channel);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Channel> channelOptional = channelDao.findById(pageId);
        if (!channelOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.CHANNEL_NOT_EXIST);
        }
        channelDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return channelDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String name, Boolean state) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Channel channel = new Channel();
        if (StringUtils.isNotEmpty(id)) {
            channel.setId(id);
        }
        if (StringUtils.isNotEmpty(name)) {
            channel.setName(name);
        }
        if (state != null) {
            channel.setState(state);
        }
        //创建条件实例
        Example<Channel> example = Example.of(channel, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Channel> all = channelDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}
