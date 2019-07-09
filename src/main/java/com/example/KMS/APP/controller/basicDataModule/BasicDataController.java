package com.example.KMS.APP.controller.basicDataModule;

import com.example.KMS.APP.basic.dto.WebResponse;
import com.example.KMS.APP.service.basicData.ContactWayService;
import com.example.KMS.APP.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 基础信息控制层
 * @Author cx
 * @Date 2019/6/13 15:56
 * @Version 1.0
 **/
@Api(tags = "基础数据的获取")
@RestController
@RequestMapping(value = "v1/basicData")
public class BasicDataController {

    @Autowired
    private ContactWayService contactWayService;

    @ApiOperation("获取全部联系方式基础数据")
    @GetMapping(value = "getAllContactWays")
    public WebResponse getAllContactWays() {
        return ResponseUtil.success(contactWayService.getAllContactWays(), "获取全部联系方式基础数据成功！");
    }
}