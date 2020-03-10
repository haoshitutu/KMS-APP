package com.example.KMS.APP.entity.ManageModule.AuthManage.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 权限返给前端参数实体
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "权限返给前端参数实体")
public class AuthVoEntity implements Serializable {
    private static final long serialVersionUID = 750487216999281486L;

    private String id;
}
