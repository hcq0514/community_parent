package com.comm.api.qa;

import com.comm.common.result.CommonResult;
import com.comm.model.qa.Answer;
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
@Api(value = "回答管理接口", description = "提供回答的增、删、改、查")
@RequestMapping("answer")
public interface AnswerControllerApi {


    @ApiOperation("添加回答")
    @PostMapping
    CommonResult add(@RequestBody Answer answer);

    @ApiOperation("获取所有回答")
    @GetMapping
    List getALL();


    @ApiOperation("根据id查询回答")
    @GetMapping("/{id}")
    Answer getById(@PathVariable("id") String id);

    @ApiOperation("更新回答")
    @PutMapping
    CommonResult update(@RequestBody Answer answer);

    @ApiOperation("删除回答")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询回答")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "problemId", value = "问题Id", example = "hcq", paramType = "query", dataType = "string")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "problemId", required = false) String problemId);

}

