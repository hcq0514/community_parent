package com.comm.recruit.controller;

import com.comm.api.recruit.RecruitControllerApi;
import com.comm.common.result.CommonResult;
import com.comm.model.recruit.Recruit;
import com.comm.recruit.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("recruit")
public class RecruitController implements RecruitControllerApi {

    @Autowired
    RecruitService recruitService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Recruit recruit) {
        return recruitService.add(recruit);
    }

    @Override
    @GetMapping
    public List getALL() {
        return recruitService.getAll();
    }

    @Override
    @GetMapping("recommendList")
    public List getRecommendList() {
        return recruitService.getRecommendList();
    }

    @Override
    @GetMapping("newList")
    public List getNewList() {
        return recruitService.getNewList();
    }


    @Override
    @GetMapping("/{id}")
    public Recruit getById(@PathVariable("id") String id) {
        return recruitService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Recruit recruit) {
        return recruitService.update(recruit);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return recruitService.deleteById(id);
    }

    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "jobName", required = false) String jobName) {
        return recruitService.search(page, size, id, jobName);
    }


}

