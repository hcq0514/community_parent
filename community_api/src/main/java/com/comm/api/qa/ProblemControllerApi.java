package com.comm.api.qa;

import com.comm.common.result.CommonResult;
import com.comm.model.qa.Problem;
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
@Api(value = "问题管理接口", description = "提供问题的增、删、改、查")
@RequestMapping("problem")
public interface ProblemControllerApi {


    @ApiOperation("添加问题")
    @PostMapping
    CommonResult add(@RequestBody Problem problem);

    @ApiOperation("获取所有问题")
    @GetMapping
    List getALL();


    @ApiOperation("根据id查询问题")
    @GetMapping("/{id}")
    Problem getById(@PathVariable("id") String id);

    @ApiOperation("更新问题")
    @PutMapping
    CommonResult update(@RequestBody Problem problem);

    @ApiOperation("删除问题")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询问题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "title", value = "问题标题", example = "hcq", paramType = "query", dataType = "string")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "title", required = false) String title);

}

