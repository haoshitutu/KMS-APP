package com.example.KMS.APP.entity.ManageModule.UserOrgManage.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 人员返给前端参数实体
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "人员返给前端参数实体")
public class UserVoEntity implements Serializable {
    private static final long serialVersionUID = 2716859450522633542L;

    private String id;
}
