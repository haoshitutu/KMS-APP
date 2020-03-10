package com.example.KMS.APP.entity.ManageModule.ConfigManage.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 配置返给前端参数实体
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "配置返给前端参数实体")
public class ConfigVoEntity implements Serializable {
    private static final long serialVersionUID = 8554573009977145786L;

    private String id;
}
