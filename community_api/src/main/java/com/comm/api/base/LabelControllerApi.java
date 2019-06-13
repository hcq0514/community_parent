package com.comm.api.base;

import com.comm.common.result.CommonResult;
import com.comm.model.base.Label;
import com.comm.model.user.Admin;
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
@Api(value = "标签管理接口", description = "提供标签的增、删、改、查")
@RequestMapping("label")
public interface LabelControllerApi {


    @ApiOperation("添加标签")
    @PostMapping
    CommonResult add(@RequestBody Label label);

    @ApiOperation("获取所有标签")
    @GetMapping
    List getALL();

    @ApiOperation("获取推荐标签")
    @GetMapping("topList")
    List getTopList();

    @ApiOperation("获取有效标签")
    @GetMapping("validList")
    List getValidList();

    @ApiOperation("根据id查询标签")
    @GetMapping("/{id}")
    Label getById(@PathVariable("id") String id);

    @ApiOperation("更新标签")
    @PutMapping
    CommonResult update(@RequestBody Label label);

    @ApiOperation("删除标签")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询标签")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "labelName", value = "标签名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "labelName", required = false) String labelName,
                        @RequestParam(value = "state", required = false, defaultValue = "true") boolean state,
                        @RequestParam(value = "recommend", required = false, defaultValue = "true") boolean recommend);

}

