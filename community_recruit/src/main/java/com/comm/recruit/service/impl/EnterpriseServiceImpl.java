package com.comm.recruit.service.impl;

import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.RecruitExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Enterprise;
import com.comm.recruit.dao.EnterpriseDao;
import com.comm.recruit.service.EnterpriseService;
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
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    EnterpriseDao enterpriseDao;

    @Override
    public Enterprise getById(String id) {
        Optional<Enterprise> optional = enterpriseDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Enterprise enterprise) {
        Enterprise base = enterpriseDao.save(enterprise);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Enterprise enterprise) {
        Optional<Enterprise> enterpriseOptional = enterpriseDao.findById(enterprise.getId());
        if (!enterpriseOptional.isPresent()) {
            ExceptionCast.cast(RecruitExceptionCode.ENTERPRISE_NOT_EXIST);
        }
        Enterprise update = enterpriseDao.save(enterprise);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Enterprise> enterpriseOptional = enterpriseDao.findById(pageId);
        if (!enterpriseOptional.isPresent()) {
            ExceptionCast.cast(RecruitExceptionCode.ENTERPRISE_NOT_EXIST);
        }
        enterpriseDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return enterpriseDao.findAll();
    }

    @Override
    public List getHotList() {
        return enterpriseDao.findByHot(true);
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String name, Boolean hot) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Enterprise enterprise = new Enterprise();
        if (StringUtils.isNotEmpty(id)) {
            enterprise.setId(id);
        }
        if (StringUtils.isNotEmpty(name)) {
            enterprise.setName(name);
        }
        if (hot != null) {
            enterprise.setHot(hot);
        }
        //创建条件实例
        Example<Enterprise> example = Example.of(enterprise, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Enterprise> all = enterpriseDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}
