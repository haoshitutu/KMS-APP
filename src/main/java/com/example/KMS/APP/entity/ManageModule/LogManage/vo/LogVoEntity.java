package com.example.KMS.APP.entity.ManageModule.LogManage.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 日志返给前端参数实体
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "日志返给前端参数实体")
public class LogVoEntity implements Serializable {
    private static final long serialVersionUID = 1743093875645670483L;

    private String id;
}