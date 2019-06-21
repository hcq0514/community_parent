package com.comm.sms.controller;

import com.alibaba.fastjson.JSONObject;
import com.comm.sms.service.SmsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * @author : hcq
 * @date : 2019/6/17
 */

@Controller
@RequestMapping("sms")
public class SmsController {

    @Autowired
    SmsService smsService;

    @ApiOperation("根据短信模版发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, example = "13113051027", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "templateId", value = "模版id", required = true, example = "1", paramType = "query", dataType = "long"),
            @ApiImplicitParam(name = "appcode", value = "appcode", example = "500", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "cloned", value = "cloned", example = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "captcha", value = "是否为验证码", example = "true", paramType = "query", dataType = "boolean"),
            @ApiImplicitParam(name = "params", value = "参数", example = "{\"code\":\"1442\",\"phone\":\"13113051027\"}", paramType = "query", dataType = "string")
    })
    @PostMapping("sendSmsByTemplate")
    @ResponseBody
    public String sendSmsByTemplate(@RequestParam("mobile") String mobile, @RequestParam("templateId") int templateId,
                                    @RequestParam("appcode") int appcode, @RequestParam("cloned") int cloned,
                                    @RequestParam("captcha") boolean captcha, @RequestParam("params") String params) {
        HashMap hashMap = JSONObject.parseObject(params, HashMap.class);
        smsService.sendSmsByTemplate(mobile, templateId, appcode, cloned, captcha, hashMap);
        return "success";
    }

    @ApiOperation("根据短信内容发送短信")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "手机号", required = true, example = "131130501027", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "content", value = "短信内容", required = true, example = "hello world", paramType = "query", dataType = "string"),
            @ApiImplicitParam(name = "appcode", value = "appcode", example = "500", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "cloned", value = "cloned", example = "1", paramType = "query", dataType = "int"),
            @ApiImplicitParam(name = "captcha", value = "是否为验证码", example = "true", paramType = "query", dataType = "boolean"),
    })
    @PostMapping("sendSmsByContent")
    @ResponseBody
    public String sendByContent(@RequestParam("mobile") String mobile, @RequestParam("content") String content,
                                @RequestParam("appcode") int appcode, @RequestParam("cloned") int cloned,
                                @RequestParam("captcha") boolean captcha) {
        smsService.sendSmsByContent(mobile, content, appcode, cloned, captcha);
        return "success";
    }
}

