package com.comm.api.user;

import com.comm.common.result.CommonResult;
import com.comm.model.user.Admin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author : hcq
 * @date : 2019/5/28
 */
@Api(value = "admin管理接口", description = "提供admin的增、删、改、查")
@RequestMapping("admin")
public interface AdminControllerApi {

    @ApiOperation("分页查询管理员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码", required = true, example = "0", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "size", value = "每页记录数", required = true, example = "10", paramType = "path", dataType = "int"),
            @ApiImplicitParam(name = "id", value = "id", example = "1", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "loginName", value = "登录名", example = "hcq", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "state", value = "是否激活", example = "true", paramType = "query", dataType = "boolean")
    })
    @GetMapping("/search/{page}/{size}")
    CommonResult search(@PathVariable("page") Integer page,
                        @PathVariable("size") Integer size,
                        @RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "loginName", required = false) String loginName,
                        @RequestParam(value = "state", required = false,defaultValue = "true") boolean state);

    @ApiOperation("根据id查询管理员")
    @GetMapping("/{adminId}")
    Admin getById(@PathVariable("adminId") String id);

    @ApiOperation("新增管理员")
    @PostMapping
    CommonResult add(@RequestBody Admin cmsPage);

    @ApiOperation("修改管理员")
    @PutMapping
    CommonResult update(@RequestBody Admin cmsPage);

    @ApiOperation("删除管理员")
    @DeleteMapping("/{adminId}")
    CommonResult delete(@PathVariable("adminId") String id);

    @ApiOperation("获取所有管理员")
    @GetMapping
    List getALL();

}

