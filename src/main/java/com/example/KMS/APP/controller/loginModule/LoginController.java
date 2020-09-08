package com.example.KMS.APP.controller.loginModule;

import com.example.KMS.APP.basic.dto.RSAKeyPair;
import com.example.KMS.APP.basic.dto.WebResponse;
import com.example.KMS.APP.basic.exception.BusinessException;
import com.example.KMS.APP.entity.loginModule.UserInfoDTO;
import com.example.KMS.APP.service.loginModule.LoginService;
import com.example.KMS.APP.utils.RSAUTtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.io.FileInputStream;
import java.security.PublicKey;
import java.util.Properties;

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
//    @Autowired
//    LoginService loginService;

    @ApiOperation("获取公钥")
    @GetMapping("/getPublicKey")
    public String getPublicKey(@RequestParam @ApiParam(value = "账号", required = true) @NotEmpty(message = "账号不能为空") String account,
                               @RequestParam @ApiParam(value = "是否重新获取密钥") boolean refreshKey) {
        //校验账号是否存在
        boolean legalAccount = false;
//        UserInfoDTO userInfoDTO = loginService.getUserInfoByAccount(account);
//        if (userInfoDTO != null){
//            legalAccount = true;
//        }
//        if (!legalAccount) {
            //throw new BusinessException("该账号未注册，请先进行注册！");
//            throw new Exception("CC");
//        }

        //存在，则返回公钥
        RSAKeyPair rsaKeyPair = RSAUTtil.getAccountKey(account, refreshKey);
        if (rsaKeyPair != null) {
            return rsaKeyPair.getPublicKey();
        } else {
            throw new BusinessException("获取RSA密钥为空！");
        }
    }

    @ApiOperation("登陆")
    @PostMapping("/login")
    public void login(@RequestParam @ApiParam(value = "账号", required = true) @NotEmpty(message = "用户名不能为空") String account,
                      @RequestParam @ApiParam(value = "密码", required = true) @NotEmpty(message = "密码不能为空") String password) throws Exception {
        //将密码进行RSA解密
        String privateKey = RSAUTtil.getAccountKey(account, false).getPrivateKey();
        String passwordMW = RSAUTtil.decrypt(password, RSAUTtil.getPrivateKey(privateKey));

        //验证账号

        //如果成功，则允许登录并赋予token


        System.out.println("参数账号："+ account + "密码：" + passwordMW);
    }

    @ApiOperation("修改密码")
    @PostMapping("/changePassword")
    public WebResponse changePassword() {
        return null;
    }
}