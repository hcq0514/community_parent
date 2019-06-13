package com.comm.api.activity;

import com.comm.common.result.CommonResult;
import com.comm.model.activity.Activity;
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
@Api(value = "活动管理接口", description = "提供活动的增、删、改、查")
@RequestMapping("activity")
public interface ActivityControllerApi {


    @ApiOperation("添加活动")
    @PostMapping
    CommonResult add(@RequestBody Activity activity);

    @ApiOperation("获取所有活动")
    @GetMapping
    List getALL();

    @ApiOperation("根据id查询活动")
    @GetMapping("/{id}")
    Activity getById(@PathVariable("id") String id);

    @ApiOperation("更新活动")
    @PutMapping
    CommonResult update(@RequestBody Activity activity);

    @ApiOperation("删除活动")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "活动名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "state", required = false) Boolean hot);


    @ApiOperation("根据城市查询活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityId", value = "cityId", example = "1", paramType = "path", dataType = "string"),
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
    })
    @GetMapping("/city/{cityId}/{page}/{size}")
    CommonResult searchByCityId(@PathVariable("cityId") String cityId,
                                @PathVariable("page") Integer page,
                                @PathVariable("size") Integer size);

}

