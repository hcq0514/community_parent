package com.comm.api.base;

import com.comm.common.result.CommonResult;
import com.comm.model.base.City;
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
@Api(value = "城市管理接口", description = "提供城市的增、删、改、查")
@RequestMapping("city")
public interface CityControllerApi {


    @ApiOperation("添加城市")
    @PostMapping
    CommonResult add(@RequestBody City city);

    @ApiOperation("获取所有城市")
    @GetMapping
    List getALL();

    @ApiOperation("根据id查询城市")
    @GetMapping("/{id}")
    City getById(@PathVariable("id") String id);

    @ApiOperation("更新城市")
    @PutMapping
    CommonResult update(@RequestBody City city);

    @ApiOperation("删除城市")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询城市")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "城市名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "hot", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "hot", required = false ) Boolean hot);

}

