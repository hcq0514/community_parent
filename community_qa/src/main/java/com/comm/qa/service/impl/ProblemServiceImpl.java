package com.comm.qa.service.impl;

import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.QaExceptionCode;
import com.comm.common.exception.code.RecruitExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.qa.Problem;
import com.comm.qa.dao.ProblemDao;
import com.comm.qa.service.ProblemService;
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
public class ProblemServiceImpl implements ProblemService {

    @Autowired
    ProblemDao problemDao;

    @Override
    public Problem getById(String id) {
        Optional<Problem> optional = problemDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Problem problem) {
        Problem base = problemDao.save(problem);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Problem problem) {
        Optional<Problem> problemOptional = problemDao.findById(problem.getId());
        if (!problemOptional.isPresent()) {
            ExceptionCast.cast(QaExceptionCode.PROBLEM_NOT_EXIST);
        }
        Problem update = problemDao.save(problem);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Problem> problemOptional = problemDao.findById(pageId);
        if (!problemOptional.isPresent()) {
            ExceptionCast.cast(QaExceptionCode.PROBLEM_NOT_EXIST);
        }
        problemDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return problemDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String title) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        Problem problem = new Problem();
        if (StringUtils.isNotEmpty(id)) {
            problem.setId(id);
        }
        if (StringUtils.isNotEmpty(title)) {
            problem.setTitle(title);
        }
        //创建条件实例
        Example<Problem> example = Example.of(problem, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Problem> all = problemDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}
