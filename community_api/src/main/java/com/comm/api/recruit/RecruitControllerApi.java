package com.comm.api.recruit;

import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Recruit;
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
@Api(value = "职位管理接口", description = "提供职位的增、删、改、查")
@RequestMapping("recruit")
public interface RecruitControllerApi {


    @ApiOperation("添加职位")
    @PostMapping
    CommonResult add(@RequestBody Recruit recruit);

    @ApiOperation("获取所有职位")
    @GetMapping
    List getALL();

    @ApiOperation("获取推荐职位")
    @GetMapping("recommendList")
    List getRecommendList();

    @ApiOperation("获取最新职位")
    @GetMapping("newList")
    List getNewList();

    @ApiOperation("根据id查询职位")
    @GetMapping("/{id}")
    Recruit getById(@PathVariable("id") String id);

    @ApiOperation("更新职位")
    @PutMapping
    CommonResult update(@RequestBody Recruit recruit);

    @ApiOperation("删除职位")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询职位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "jobName", value = "职位名", example = "hcq", paramType = "query", dataType = "string"),
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "jobName", required = false) String jobName);

}

