package com.comm.user.controller;


import com.comm.common.result.CommonResult;
import com.comm.model.user.User;
import com.comm.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/get/{id}")
    public User getById(@PathVariable("id") String id) {
        return userService.getById(id);
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody User coursebase) {
        return userService.add(coursebase);
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody User courseBase) {
        return userService.update(courseBase);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return userService.deleteById(id);
    }

}

