package com.example.KMS.APP.controller.ManageModule.MenuManage;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 菜单管理
 * @Author tutu
 * @Date 2020/02/22
 * @Version 1.0
 **/
@Api(tags = "管理模块--菜单管理控制层")
@Validated
@RestController
@RequestMapping(value = "v1/menu")
public class MenuController {
}