package com.example.KMS.APP.service.basicData.impl;

import com.example.KMS.APP.basic.exception.BusinessException;
import com.example.KMS.APP.entity.ContactWay;
import com.example.KMS.APP.mapper.ContactWayMapper;
import com.example.KMS.APP.service.basicData.ContactWayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 联系方式服务接口实现类
 * @Author cx
 * @Date 2019/7/8 18:04
 * @Version 1.0
 **/
@Service
@Slf4j
public class ContactWayServiceImpl implements ContactWayService {
    @Autowired
    private ContactWayMapper contactWayMapper;

    @Override
    public List<ContactWay> getAllContactWays() {
        try {
            return contactWayMapper.selectAll();
        } catch (Exception e) {
            log.error(String.format("获取全部联系方式基础数据失败！--%s", e.getMessage()));
            throw new BusinessException(String.format("获取全部联系方式基础数据失败！--%s", e.getMessage()));
        }
    }
}