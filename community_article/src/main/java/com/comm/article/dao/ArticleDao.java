package com.comm.article.dao;

import com.comm.model.article.Article;
import com.comm.model.article.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/6/4
 */

public interface ArticleDao extends JpaRepository<Article,String> {

    List findByTop(Boolean b);


}
