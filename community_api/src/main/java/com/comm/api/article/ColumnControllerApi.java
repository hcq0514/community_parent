package com.comm.api.article;

import com.comm.common.result.CommonResult;
import com.comm.model.article.Column;
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
@Api(value = "专栏管理接口", description = "提供专栏的增、删、改、查")
@RequestMapping("column")
public interface ColumnControllerApi {


    @ApiOperation("添加专栏")
    @PostMapping
    CommonResult add(@RequestBody Column column);

    @ApiOperation("获取所有专栏")
    @GetMapping
    List getALL();

    @ApiOperation("根据id查询专栏")
    @GetMapping("/{id}")
    Column getById(@PathVariable("id") String id);

    @ApiOperation("更新专栏")
    @PutMapping
    CommonResult update(@RequestBody Column column);

    @ApiOperation("删除专栏")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);

    @ApiOperation("专栏审核")
    @PutMapping("/examine/{id}")
    CommonResult examineColumn(@PathVariable("id") String id);


    @ApiOperation("分页查询专栏")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "专栏名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "state", required = false) Boolean state);


    @ApiOperation("查询userId的专栏")
    @GetMapping("/user/{userId}")
    List getUserColumns(@PathVariable("userId") String userId);
}

