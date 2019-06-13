package com.comm.activity.controller;

import com.comm.activity.service.ActivityService;
import com.comm.api.activity.ActivityControllerApi;
import com.comm.common.result.CommonResult;
import com.comm.model.activity.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
public class ActivityController implements ActivityControllerApi {

    @Autowired
    ActivityService activityService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Activity activity) {
        return activityService.add(activity);
    }

    @Override
    @GetMapping
    public List getALL() {
        return activityService.getAll();
    }


    @Override
    @GetMapping("/{id}")
    public Activity getById(@PathVariable("id") String id) {
        return activityService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Activity activity) {
        return activityService.update(activity);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return activityService.deleteById(id);
    }

    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "state", required = false) Boolean state) {
        return activityService.search(page, size, id, name, null, state);
    }

    @Override
    @GetMapping("/city/{cityId}/{page}/{size}")
    public CommonResult searchByCityId(@PathVariable("cityId") String cityId,
                                       @PathVariable("page") Integer page,
                                       @PathVariable("size") Integer size) {
        return activityService.search(page, size, null, null, cityId, null);
    }


}

