package com.example.KMS.APP.service.basicData;

import com.example.KMS.APP.entity.AddressType;

import java.util.List;

/**
 * @Description 地址类型服务接口
 * @Author cx
 * @Date 2019/7/8 18:03
 * @Version 1.0
 **/
public interface AddressTypeService {
    List<AddressType> getAllAddressTypes();
}