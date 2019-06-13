package com.comm.api.article;

import com.comm.common.result.CommonResult;
import com.comm.model.article.Channel;
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
@Api(value = "渠道管理接口", description = "提供渠道的增、删、改、查")
@RequestMapping("channel")
public interface ChannelControllerApi {


    @ApiOperation("添加渠道")
    @PostMapping
    CommonResult add(@RequestBody Channel channel);

    @ApiOperation("获取所有渠道")
    @GetMapping
    List getALL();

    @ApiOperation("根据id查询渠道")
    @GetMapping("/{id}")
    Channel getById(@PathVariable("id") String id);

    @ApiOperation("更新渠道")
    @PutMapping
    CommonResult update(@RequestBody Channel channel);

    @ApiOperation("删除渠道")
    @DeleteMapping("/{id}")
    CommonResult delete(@PathVariable("id") String id);


    @ApiOperation("分页查询渠道")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "name", value = "渠道名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "state", required = false) Boolean state);

}

