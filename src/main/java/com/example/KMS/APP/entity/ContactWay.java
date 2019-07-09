package com.example.KMS.APP.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 联系方式
 * @Author cx
 * @Date 2019/7/8 17:27
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "联系方式类")
@Entity
@Table(name = "contact_way_base")
public class ContactWay implements Serializable {
    private static final long serialVersionUID = 5331987205017517436L;

    @Column(name="contact_way")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "联系方式代码")
    private String contactWay;

    @Column(name="description")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "联系方式描述")
    private String description;

    @Column(name="valid_flag")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "是否有效")
    private String validFlag;
}