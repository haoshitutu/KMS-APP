package com.example.KMS.APP.controller.loginModule;

import com.example.KMS.APP.basic.dto.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description 登陆模块
 * @Author cx
 * @Date 2019/06/11 11：23
 * @Version 1.0
 **/
@Api(tags = "登陆模块控制层")
@RestController
@RequestMapping(value = "v1")
public class LoginController {

    @ApiOperation("登陆")
    @PostMapping("/login")
    public void login() {}

    @ApiOperation("退出")
    @PostMapping("/logout")
    public void logout() {}

    @ApiOperation("修改密码")
    @PostMapping("/changePassword")
    public WebResponse changePassword() {
        return null;
    }

    @ApiOperation("重置密码")
    @PostMapping("/resetPassword")
    public WebResponse resetPassword() {
        return null;
    }
}