package com.example.KMS.APP.entity;

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
 * @Description 地址类型
 * @Author cx
 * @Date 2019/7/8 17:27
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "地址类型实体类")
@Entity
@Table(name = "address_type_base")
public class AddressType implements Serializable {
    private static final long serialVersionUID = -900937761060590983L;

    @Column(name="address_type")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "地址类型代码")
    private String addressType;

    @Column(name="description")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "地址类型描述")
    private String description;

    @Column(name="valid_flag")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "是否有效")
    private String validFlag;
}