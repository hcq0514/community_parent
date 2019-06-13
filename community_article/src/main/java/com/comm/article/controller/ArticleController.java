package com.comm.article.controller;

import com.comm.api.article.ArticleControllerApi;
import com.comm.api.article.ArticleControllerApi;
import com.comm.article.service.ArticleService;
import com.comm.common.result.CommonResult;
import com.comm.model.article.Article;
import com.comm.model.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ArticleController implements ArticleControllerApi {

    @Autowired
    ArticleService articleService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Article article) {
        return articleService.add(article);
    }

    @Override
    @GetMapping
    public List getALL() {
        return articleService.getAll();
    }


    @Override
    @GetMapping("/{id}")
    public Article getById(@PathVariable("id") String id) {
        return articleService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Article article) {
        return articleService.update(article);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return articleService.deleteById(id);
    }


    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "state", required = false) Boolean state) {
        return articleService.search(page, size, id, name, state);
    }


    @Override
    @GetMapping("/channel/{channelId}/{page}/{size}")
    public CommonResult searchByChannelId(@PathVariable("page") Integer page,
                                          @PathVariable("size") Integer size,
                                          @PathVariable("channelId") String channelId) {
        return articleService.searchByChannelId(page, size, channelId);
    }


    @Override
    @GetMapping("/column/{columnId}/{page}/{size}")
    public CommonResult searchByColumnId(@PathVariable("page") Integer page,
                                         @PathVariable("size") Integer size,
                                         @PathVariable("columnId") String columnId) {
        return articleService.searchByColumnId(page, size, columnId);
    }


    @Override
    @PutMapping("/examine/{id}")
    public CommonResult examineArticle(@PathVariable("id") String id) {
        return articleService.examine(id);
    }


    @Override
    @GetMapping("/article/top")
    public List getTopArticles() {
        return articleService.getTopArticles();
    }

}

