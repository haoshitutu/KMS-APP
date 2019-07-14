package com.example.KMS.APP.controller.loginModule;

import com.example.KMS.APP.basic.dto.WebResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;

/**
 * @Description 登陆模块
 * @Author cx
 * @Date 2019/06/11 11：23
 * @Version 1.0
 **/
@Api(tags = "登陆模块控制层")
@Validated
@RestController
@RequestMapping(value = "v1")
public class LoginController {

    @ApiOperation("登陆")
    @PostMapping("/login")
    public void login(@RequestParam @ApiParam(value = "用户名", required = true) @NotEmpty(message = "用户名不能为空") String accountNo,
                      @RequestParam @ApiParam(value = "密码", required = true) @NotEmpty(message = "密码不能为空") String password) {
        System.out.println("参数账号："+ accountNo + "密码：" + password);
    }

    @ApiOperation("修改密码")
    @PostMapping("/changePassword")
    public WebResponse changePassword() {
        return null;
    }
}