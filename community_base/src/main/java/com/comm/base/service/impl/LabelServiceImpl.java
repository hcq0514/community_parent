package com.comm.base.service.impl;

import com.comm.base.dao.LabelDao;
import com.comm.base.service.LabelService;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.BaseExceptionCode;
import com.comm.common.exception.code.UserExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.base.Label;
import com.comm.model.user.Admin;
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
public class LabelServiceImpl implements LabelService {

    @Autowired
    LabelDao labelDao;

    @Override
    public Label getById(String id) {
        Optional<Label> optional = labelDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Label label) {
        Label base = labelDao.save(label);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Label label) {
        Optional<Label> labelOption = labelDao.findById(label.getId());
        if (!labelOption.isPresent()) {
            ExceptionCast.cast(BaseExceptionCode.LABEL_NOT_EXIST);
        }
        Label update = labelDao.save(label);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Label> labelOptional = labelDao.findById(pageId);
        if (!labelOptional.isPresent()) {
            ExceptionCast.cast(BaseExceptionCode.LABEL_ALREADY_EXIST);
        }
        labelDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return labelDao.findAll();
    }

    @Override
    public CommonResult search(Integer page, Integer size, String id, String loginName, boolean state, boolean recommend) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("label_name", ExampleMatcher.GenericPropertyMatchers.contains());
        Label label = new Label();
        if (StringUtils.isNotEmpty(id)) {
            label.setId(id);
        }
        if (StringUtils.isNotEmpty(loginName)) {
            label.setLabelName(loginName);
        }
        label.setState(state);
        label.setState(recommend);
        //创建条件实例
        Example<Label> example = Example.of(label, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Label> all = labelDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }

    @Override
    public List getTopList() {
        return labelDao.findByRecommend(true);
    }

    @Override
    public List getValidList() {
        return labelDao.findByState(false);
    }

}
