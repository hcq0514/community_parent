package com.comm.qa.controller;

import com.comm.api.qa.AnswerControllerApi;
import com.comm.common.result.CommonResult;
import com.comm.model.qa.Answer;
import com.comm.qa.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("answer")
public class AnswerController implements AnswerControllerApi {

    @Autowired
    AnswerService answerService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Answer answer) {
        return answerService.add(answer);
    }

    @Override
    @GetMapping
    public List getALL() {
        return answerService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Answer getById(@PathVariable("id") String id) {
        return answerService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Answer answer) {
        return answerService.update(answer);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return answerService.deleteById(id);
    }

    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "problemId", required = false) String problemId) {
        return answerService.search(page, size, id, problemId);
    }


}

