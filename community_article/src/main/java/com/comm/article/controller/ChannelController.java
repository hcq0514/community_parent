package com.comm.article.controller;

import com.comm.api.article.ChannelControllerApi;
import com.comm.article.service.ChannelService;
import com.comm.common.result.CommonResult;
import com.comm.model.article.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("article")
public class ChannelController implements ChannelControllerApi {

    @Autowired
    ChannelService channelService;


    @Override
    @PostMapping
    public CommonResult add(@RequestBody Channel channel) {
        return channelService.add(channel);
    }

    @Override
    @GetMapping
    public List getALL() {
        return channelService.getAll();
    }


    @Override
    @GetMapping("/{id}")
    public Channel getById(@PathVariable("id") String id) {
        return channelService.getById(id);
    }

    @Override
    @PutMapping
    public CommonResult update(@RequestBody Channel channel) {
        return channelService.update(channel);
    }

    @Override
    @DeleteMapping("/{id}")
    public CommonResult delete(@PathVariable("id") String id) {
        return channelService.deleteById(id);
    }


    @Override
    @GetMapping("/search/{page}/{size}")
    public CommonResult search(@PathVariable("page") Integer page,
                               @PathVariable("size") Integer size,
                               @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "state", required = false) Boolean state) {
        return channelService.search(page, size, id, name, state);
    }

}

