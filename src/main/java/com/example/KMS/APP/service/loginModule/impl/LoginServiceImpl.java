package com.example.KMS.APP.service.loginModule.impl;

import com.example.KMS.APP.basic.exception.BusinessException;
import com.example.KMS.APP.entity.loginModule.UserInfoDTO;
//import com.example.KMS.APP.mapper.loginModule.UserInfoMapper;
import com.example.KMS.APP.service.loginModule.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 登录模块接口实现
 * @Author tutu
 * @Date 2020/9/2
 * @Version 1.0
 **/
@Service
public class LoginServiceImpl implements LoginService {
//    @Autowired
//    UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDTO getUserInfoByAccount(String account) {
        if (StringUtils.isBlank(account)) {
            throw new BusinessException("根据账号获取用户信息失败,参数账号为空");
        }

//        UserInfoDTO userInfoDTO = userInfoMapper.getUserInfoByAccount(account);
//        return userInfoDTO;
        return null;
    }
}
