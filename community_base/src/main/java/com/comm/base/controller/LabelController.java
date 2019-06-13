package com.comm.base.controller;

import com.comm.api.base.LabelControllerApi;
import com.comm.base.service.LabelService;
import com.comm.common.result.CommonResult;
import com.comm.model.base.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("label")
public class LabelController implements LabelControllerApi {

    @Autowired
    LabelService labelService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Label label) {
        return labelService.add(label);
    }

    @Override
    @GetMapping
    public List getALL() {
        return labelService.getAll();
    }

    @Override
    @GetMapping("topList")
    public List getTopList() {
        return labelService.getTopList();
    }

    @Override
    @GetMapping("validList")
    public List getValidList() {
        return labelService.getValidList();
    }

    @Override
    @GetMapping("/{id}")
    public Label getById(@PathVariable("id") String id) {
        return labelService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Label label) {
        return labelService.update(label);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return labelService.deleteById(id);
    }


    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "labelName", required = false) String labelName,
                               @RequestParam(value = "state", required = false, defaultValue = "true") boolean state,
                               @RequestParam(value = "recommend", required = false, defaultValue = "true") boolean recommend) {
        return labelService.search(page, size, id, labelName, state, recommend);
    }

}

