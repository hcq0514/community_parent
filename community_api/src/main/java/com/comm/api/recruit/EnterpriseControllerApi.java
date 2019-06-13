package com.comm.api.recruit;

import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Enterprise;
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
@Api(value = "公司管理接口", description = "提供公司的增、删、改、查")
@RequestMapping("enterprise")
public interface EnterpriseControllerApi {


    @ApiOperation("添加公司")
    @PostMapping
    CommonResult add(@RequestBody Enterprise enterprise);

    @ApiOperation("获取所有公司")
    @GetMapping
    List getALL();

    @ApiOperation("获取热门公司")
    @GetMapping("hotList")
    List getHotList();

    @ApiOperation("根据id查询公司")
    @GetMapping("/{id}")
    Enterprise getById(@PathVariable("id") String id);

    @ApiOperation("更新公司")
    @PutMapping
    CommonResult update(@RequestBody Enterprise enterprise);

    @ApiOperation("删除公司")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询公司")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "公司名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "hot", value = "是否热门", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "hot", required = false) Boolean hot);

}

