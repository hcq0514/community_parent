package com.comm.article.service.impl;

import com.comm.article.dao.ColumnDao;
import com.comm.article.service.ColumnService;
import com.comm.article.service.ColumnService;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.ArticleExceptionCode;
import com.comm.common.exception.code.BaseExceptionCode;
import com.comm.common.result.CommonCode;
import com.comm.common.result.CommonResult;
import com.comm.model.article.Article;
import com.comm.model.article.Column;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


/**
 * @author : hcq
 * @date : 2019/6/12
 */
@Service
public class ColumnServiceImpl implements ColumnService {

    @Autowired
    ColumnDao columnDao;

    @Override
    public Column getById(String id) {
        Optional<Column> optional = columnDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Column column) {
        Column base = columnDao.save(column);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Column column) {
        Optional<Column> columnOptional = columnDao.findById(column.getId());
        if (!columnOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.COLUMN_NOT_EXIST);
        }
        Column update = columnDao.save(column);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Column> columnOptional = columnDao.findById(pageId);
        if (!columnOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.COLUMN_NOT_EXIST);
        }
        columnDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return columnDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String name, Boolean state) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatchers.contains());
        Column column = new Column();
        if (StringUtils.isNotEmpty(id)) {
            column.setId(id);
        }
        if (StringUtils.isNotEmpty(name)) {
            column.setName(name);
        }
        if (state != null) {
            column.setState(state);
        }
        //创建条件实例
        Example<Column> example = Example.of(column, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Column> all = columnDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }

    @Override
    public CommonResult examineColumn(String id) {
        Optional<Column> columnOptional = columnDao.findById(id);
        if (!columnOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.COLUMN_NOT_EXIST);
        }
        Column column = columnOptional.get();
        column.setState(true);
        column.setCheckTime(LocalDateTime.now());
        Column save = columnDao.save(column);
        return new CommonResult<>(CommonCode.SUCCESS, save);
    }

    @Override
    public List findByUserId(String userId) {
        return columnDao.findByUserId(userId);
    }

}
