package com.comm.base.controller;

import com.comm.api.base.CityControllerApi;
import com.comm.api.base.CityControllerApi;
import com.comm.base.service.CityService;
import com.comm.common.result.CommonResult;
import com.comm.model.base.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("city")
public class CityController implements CityControllerApi {

    @Autowired
    CityService cityService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody City city) {
        return cityService.add(city);
    }

    @Override
    @GetMapping
    public List getALL() {
        return cityService.getAll();
    }


    @Override
    @GetMapping("/{id}")
    public City getById(@PathVariable("id") String id) {
        return cityService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody City city) {
        return cityService.update(city);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return cityService.deleteById(id);
    }


    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "hot", required = false) Boolean hot) {
        return cityService.search(page, size, id, name, hot);
    }

}

