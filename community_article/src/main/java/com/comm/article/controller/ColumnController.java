package com.comm.article.controller;

import com.comm.api.article.ColumnControllerApi;
import com.comm.api.article.ColumnControllerApi;
import com.comm.article.service.ColumnService;
import com.comm.common.result.CommonResult;
import com.comm.model.article.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("column")
public class ColumnController implements ColumnControllerApi {

    @Autowired
    ColumnService columnService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Column column) {
        return columnService.add(column);
    }

    @Override
    @GetMapping
    public List getALL() {
        return columnService.getAll();
    }


    @Override
    @GetMapping("/{id}")
    public Column getById(@PathVariable("id") String id) {
        return columnService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Column column) {
        return columnService.update(column);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return columnService.deleteById(id);
    }

    @Override
    @PutMapping("/examine/{id}")
    public CommonResult examineColumn(@PathVariable("id") String id) {
        return columnService.examineColumn(id);
    }


    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "state", required = false) Boolean state) {
        return columnService.search(page, size, id, name, state);
    }

    @Override
    @GetMapping("/user/{userId}")
    public List getUserColumns(@PathVariable("userId") String userId) {
        return columnService.findByUserId(userId);
    }

}

