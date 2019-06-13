package com.comm.recruit.controller;

import com.comm.api.recruit.EnterpriseControllerApi;
import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Enterprise;
import com.comm.recruit.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enterprise")
public class EnterpriseController implements EnterpriseControllerApi {

    @Autowired
    EnterpriseService enterpriseService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Enterprise enterprise) {
        return enterpriseService.add(enterprise);
    }

    @Override
    @GetMapping
    public List getALL() {
        return enterpriseService.getAll();
    }

    @Override
    @GetMapping("hotList")
    public List getHotList() {
        return enterpriseService.getHotList();
    }


    @Override
    @GetMapping("/{id}")
    public Enterprise getById(@PathVariable("id") String id) {
        return enterpriseService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Enterprise enterprise) {
        return enterpriseService.update(enterprise);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return enterpriseService.deleteById(id);
    }

    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "hot", required = false) Boolean hot) {
        return enterpriseService.search(page, size, id, name, hot);
    }



}

