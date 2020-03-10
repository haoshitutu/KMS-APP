package com.example.KMS.APP.entity.ManageModule.ConfigManage.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 配置数据库实体类
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "配置数据库实体类")
@Entity
@Table(name = "xx")
public class ConfigDtoEntity implements Serializable {
    private static final long serialVersionUID = 2972559910548510319L;

    private String id;
}
