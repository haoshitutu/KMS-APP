package com.example.KMS.APP.entity.ManageModule.MenuManage.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description 菜单返给前端参数实体
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "菜单返给前端参数实体")
public class MenuVoEntity implements Serializable {
    private static final long serialVersionUID = -5194152747873610693L;

    private String id;
}
