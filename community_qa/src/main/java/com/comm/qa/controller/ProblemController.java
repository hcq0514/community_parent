package com.comm.qa.controller;

import com.comm.api.qa.ProblemControllerApi;
import com.comm.common.result.CommonResult;
import com.comm.model.qa.Problem;
import com.comm.qa.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("problem")
public class ProblemController implements ProblemControllerApi {

    @Autowired
    ProblemService problemService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Problem problem) {
        return problemService.add(problem);
    }

    @Override
    @GetMapping
    public List getALL() {
        return problemService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Problem getById(@PathVariable("id") String id) {
        return problemService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Problem problem) {
        return problemService.update(problem);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return problemService.deleteById(id);
    }

    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "title", required = false) String title) {
        return problemService.search(page, size, id, title);
    }


}

