package com.example.KMS.APP.entity.ManageModule.MenuManage.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 菜单数据库实体类
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "菜单数据库实体类")
@Entity
@Table(name = "xx")
public class MenuDtoEntity implements Serializable {
    private static final long serialVersionUID = 7211991404785526667L;

    private String id;
}
