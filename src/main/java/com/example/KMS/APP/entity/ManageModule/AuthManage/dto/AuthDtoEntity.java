package com.example.KMS.APP.entity.ManageModule.AuthManage.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 权限数据库实体类
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "权限数据库实体类")
@Entity
@Table(name = "xx")
public class AuthDtoEntity implements Serializable {
    private static final long serialVersionUID = -2098522962964075959L;

    private String id;
}
