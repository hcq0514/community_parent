package com.comm.article.service;

import com.comm.common.result.CommonResult;
import com.comm.model.article.Article;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */
public interface ArticleService {

    Article getById(String id);

    CommonResult add(Article article);

    CommonResult update(Article article);

    CommonResult deleteById(String id);

    List getAll();

    CommonResult search(Integer page, Integer size, String id, String title, Boolean state);

    CommonResult searchByChannelId(Integer page, Integer size, String channelId);

    CommonResult searchByColumnId(Integer page, Integer size, String columnId);

    CommonResult examine(String id);

    List getTopArticles();




}
