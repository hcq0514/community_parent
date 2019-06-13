package com.comm.article.service.impl;

import com.comm.article.dao.ArticleDao;
import com.comm.article.service.ArticleService;
import com.comm.common.exception.ExceptionCast;
import com.comm.common.exception.code.ArticleExceptionCode;
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
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public Article getById(String id) {
        Optional<Article> optional = articleDao.findById(id);
        boolean present = optional.isPresent();
        if (present) {
            return optional.get();
        }
        return null;
    }

    @Override
    public CommonResult add(Article article) {
        Article base = articleDao.save(article);
        return new CommonResult<>(CommonCode.SUCCESS, base);
    }

    @Override
    public CommonResult update(Article article) {
        Optional<Article> articleOptional = articleDao.findById(article.getId());
        if (!articleOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.CHANNEL_NOT_EXIST);
        }
        Article update = articleDao.save(article);
        return new CommonResult<>(CommonCode.SUCCESS, update);
    }

    @Override
    public CommonResult deleteById(String pageId) {
        Optional<Article> articleOptional = articleDao.findById(pageId);
        if (!articleOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.CHANNEL_NOT_EXIST);
        }
        articleDao.deleteById(pageId);
        return new CommonResult<>(CommonCode.SUCCESS, null);
    }

    @Override
    public List getAll() {
        return articleDao.findAll();
    }


    @Override
    public CommonResult search(Integer page, Integer size, String id, String title, Boolean state) {
        //条件匹配器
        //页面名称模糊查询，需要自定义字符串的匹配器实现模糊查询
        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        Article article = new Article();
        if (StringUtils.isNotEmpty(id)) {
            article.setId(id);
        }
        if (StringUtils.isNotEmpty(title)) {
            article.setTitle(title);
        }
        if (state != null) {
            article.setState(state);
        }
        //创建条件实例
        Example<Article> example = Example.of(article, exampleMatcher);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Article> all = articleDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }

    @Override
    public CommonResult searchByChannelId(Integer page, Integer size, String channelId) {
        Article article = new Article();
        article.setChannelId(channelId);
        //创建条件实例
        Example<Article> example = Example.of(article);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Article> all = articleDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }

    @Override
    public CommonResult searchByColumnId(Integer page, Integer size, String columnId) {
        Article article = new Article();
        article.setColumnId(columnId);
        //创建条件实例
        Example<Article> example = Example.of(article);
        //页码
        page = page - 1;
        //分页对象
        Pageable pageable = PageRequest.of(page, size);
        //分页查询
        Page<Article> all = articleDao.findAll(example, pageable);
        //返回结果
        return new CommonResult<>(CommonCode.SUCCESS, all);
    }

    @Override
    public CommonResult examine(String id) {
        Optional<Article> articleOptional = articleDao.findById(id);
        if (!articleOptional.isPresent()) {
            ExceptionCast.cast(ArticleExceptionCode.ARTICLE_NOT_EXIST);
        }
        Article article = articleOptional.get();
        article.setState(true);
        article.setUpdateTime(LocalDateTime.now());
        Article save = articleDao.save(article);
        return new CommonResult<>(CommonCode.SUCCESS, save);
    }

    @Override
    public List getTopArticles() {
        return articleDao.findByTop(true);
    }


}
