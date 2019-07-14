package com.example.KMS.APP.service.basicData.impl;

import com.example.KMS.APP.basic.exception.BusinessException;
import com.example.KMS.APP.entity.AddressType;
import com.example.KMS.APP.mapper.AddressTypeMapper;
import com.example.KMS.APP.service.basicData.AddressTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 地址类型接口实现类
 * @Author cx
 * @Date 2019/7/8 18:04
 * @Version 1.0
 **/
@Service
@Slf4j
public class AddressTypeServiceImpl implements AddressTypeService {
    @Autowired
    private AddressTypeMapper addressTypeMapper;

    @Override
    public List<AddressType> getAllAddressTypes() {
        try {
            return addressTypeMapper.selectAll();
        } catch (Exception e) {
            log.error(String.format("获取全部地址类型基础数据失败！--%s", e.getMessage()));
            throw new BusinessException(String.format("获取全部地址类型基础数据失败！--%s", e.getMessage()));
        }
    }
}