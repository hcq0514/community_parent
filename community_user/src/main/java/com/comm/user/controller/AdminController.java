package com.comm.user.controller;


import com.comm.api.user.AdminControllerApi;
import com.comm.common.result.CommonResult;
import com.comm.model.user.Admin;
import com.comm.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController implements AdminControllerApi {

    @Autowired
    AdminService adminService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Admin admin) {
        return adminService.add(admin);
    }

    @Override
    @GetMapping
    public List getALL() {
        return adminService.getAll();
    }

    @Override
    @GetMapping("/{adminId}")
    public Admin getById(@PathVariable("adminId") String id) {
        return adminService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

    @Override
    @DeleteMapping("/{adminId}")
    public CommonResult delete(@PathVariable("adminId") String id) {
        return adminService.deleteById(id);
    }

    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "loginName", required = false) String loginName,
                               @RequestParam(value = "state", required = false, defaultValue = "true") boolean state) {
        return adminService.search(page, size, id, loginName, state);
    }

}

