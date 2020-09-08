package com.example.KMS.APP.controller.pageModule;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @Description 页面模块
 * @Author cx
 * @Date 2020/08/12 15：48
 * @Version 1.0
 **/
@Api(tags = "页面模块控制层")
@Validated
@RequestMapping(value = "v1/page")
@Controller
public class PageController {

    @ApiOperation("跳转页面")
    @GetMapping("/jumpToPage")
    public String jumpToPage(@RequestParam String pageAddress) {
        return pageAddress;
    }
}