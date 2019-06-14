package com.comm.recruit.service.impl;

import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.RecruitExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Recruit;
import com.comm.recruit.dao.RecruitDao;
import com.comm.recruit.service.RecruitService;
import com.comm.recruit.service.RecruitService;
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
public class RecruitServiceImpl implements RecruitService {

    @Autowired
    RecruitDao recruitDao;

    @Override
    public Recruit getById(String id) {
        Optional<Recruit> optional = recruitDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Recruit recruit) {
        Recruit base = recruitDao.save(recruit);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Recruit recruit) {
        Optional<Recruit> recruitOptional = recruitDao.findById(recruit.getId());
        if (!recruitOptional.isPresent()) {
            ExceptionCast.cast(RecruitExceptionCode.RECRUIT_NOT_EXIST);
        }
        Recruit update = recruitDao.save(recruit);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Recruit> recruitOptional = recruitDao.findById(pageId);
        if (!recruitOptional.isPresent()) {
            ExceptionCast.cast(RecruitExceptionCode.RECRUIT_NOT_EXIST);
        }
        recruitDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return recruitDao.findAll();
    }

    @Override
    public List getRecommendList() {
        return recruitDao.findByRecommend(true);
    }

    @Override
    public List getNewList() {
        return recruitDao.findTop5ByOrderByCreateTimeDesc();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String jobName) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("job_name", ExampleMatcher.GenericPropertyMatchers.contains());
        Recruit recruit = new Recruit();
        if (StringUtils.isNotEmpty(id)) {
            recruit.setId(id);
        }
        if (StringUtils.isNotEmpty(jobName)) {
            recruit.setJobName(jobName);
        }

        //创建条件实例
        Example<Recruit> example = Example.of(recruit, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Recruit> all = recruitDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}
