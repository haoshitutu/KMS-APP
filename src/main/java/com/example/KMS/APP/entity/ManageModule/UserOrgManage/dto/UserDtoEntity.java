package com.example.KMS.APP.entity.ManageModule.UserOrgManage.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 人员数据库实体类
 * @Author tutu
 * @Date 2020/2/22
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "人员数据库实体类")
@Entity
@Table(name = "xx")
public class UserDtoEntity implements Serializable {
    private static final long serialVersionUID = 2897125212340934445L;

    private String id;
}
