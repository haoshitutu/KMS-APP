package com.example.KMS.APP.controller.ManageModule.UserOrgManage;

import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 人员组织管理
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Api(tags = "管理模块--人员组织控制层")
@Validated
@RestController
@RequestMapping(value = "v1/userOrg")
public class UserOrgController {
}
