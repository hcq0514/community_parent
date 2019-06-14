package com.comm.qa.service.impl;

import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.QaExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.qa.Answer;
import com.comm.qa.dao.AnswerDao;
import com.comm.qa.service.AnswerService;
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
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    AnswerDao answerDao;

    @Override
    public Answer getById(String id) {
        Optional<Answer> optional = answerDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Answer answer) {
        Answer base = answerDao.save(answer);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Answer answer) {
        Optional<Answer> answerOptional = answerDao.findById(answer.getId());
        if (!answerOptional.isPresent()) {
            ExceptionCast.cast(QaExceptionCode.ANSWER_NOT_EXIST);
        }
        Answer update = answerDao.save(answer);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Answer> answerOptional = answerDao.findById(pageId);
        if (!answerOptional.isPresent()) {
            ExceptionCast.cast(QaExceptionCode.ANSWER_NOT_EXIST);
        }
        answerDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return answerDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String problemId) {
        Answer answer = new Answer();
        if (StringUtils.isNotEmpty(id)) {
            answer.setId(id);
        }
        if (StringUtils.isNotEmpty(problemId)) {
            answer.setProblemId(problemId);
        }
        //创建条件实例
        Example<Answer> example = Example.of(answer);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Answer> all = answerDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }


}
