package com.example.KMS.APP.entity.ManageModule.DicManage.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 字典返给前端参数实体
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "字典返给前端参数实体")
public class DicVoEntity implements Serializable {
    private static final long serialVersionUID = 8083549403672252970L;

    private String id;
}
