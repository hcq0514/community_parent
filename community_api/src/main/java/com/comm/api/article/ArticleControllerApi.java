package com.comm.api.article;

import com.comm.common.result.CommonResult;
import com.comm.model.article.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@Api(value = "文章管理接口", description = "提供文章的增、删、改、查")
@RequestMapping("article")
public interface ArticleControllerApi {


    @ApiOperation("添加文章")
    @PostMapping
    CommonResult add(@RequestBody Article article);

    @ApiOperation("获取所有文章")
    @GetMapping
    List getALL();

    @ApiOperation("根据id查询文章")
    @GetMapping("/{id}")
    Article getById(@PathVariable("id") String id);

    @ApiOperation("更新文章")
    @PutMapping
    CommonResult update(@RequestBody Article article);

    @ApiOperation("删除文章")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "文章名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "state", required = false) Boolean state);

    @ApiOperation("根据专栏渠道id查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "channelId", value = "渠道id", example = "1", paramType = "path", dataType = "string"),
    })
    @GetMapping("/channel/{channelId}/{page}/{size}")
    CommonResult searchByChannelId(@PathVariable("page") Integer page,
                                   @PathVariable("size") Integer size,
                                   @PathVariable("channelId") String channelId);

    @ApiOperation("根据专栏id查询文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "columnId", value = "专栏id", example = "1", paramType = "path", dataType = "string"),
    })
    @GetMapping("/column/{columnId}/{page}/{size}")
    CommonResult searchByColumnId(@PathVariable("page") Integer page,
                                  @PathVariable("size") Integer size,
                                  @PathVariable("columnId") String columnId);


    @ApiOperation("文章审核")
    @PutMapping("/examine/{id}")
    CommonResult examineArticle(@PathVariable("id") String id);


    @ApiOperation("获取置顶文章")
    @GetMapping("/article/top")
    List getTopArticles();
}

