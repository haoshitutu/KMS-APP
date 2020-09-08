package com.example.KMS.APP.service.loginModule;

import com.example.KMS.APP.entity.loginModule.UserInfoDTO;

/**
 * @Description 登陆模块相关接口
 * @Author tutu
 * @Date 2020/9/2
 * @Version 1.0
 **/
public interface LoginService {
    /**
     * 根据账号获取用户信息
     * @param account 账号
     * @return 有则返回用户信息/无则返回null
     */
    UserInfoDTO getUserInfoByAccount(String account);
}
