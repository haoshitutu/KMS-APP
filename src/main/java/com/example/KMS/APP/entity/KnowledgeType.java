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
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description 知识类型
 * @Author cx
 * @Date 2019/7/10 10:39
 * @Version 1.0
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "知识类型实体类")
@Entity
@Table(name = "konwledge_type_base")
public class KnowledgeType implements Serializable {
    private static final long serialVersionUID = 4948701452500158073L;

    @Id
    @Column(name="id")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    @ApiModelProperty(value = "知识类型编号")
    private int id;

    @Column(name="name")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "知识类型名称")
    private String name;

    @Column(name="level")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    @ApiModelProperty(value = "层级")
    private int level;

    @Column(name="display_seq")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    @ApiModelProperty(value = "显示顺序")
    private String displaySeq;

    @Column(name="parent_id")
    @ColumnType(jdbcType = JdbcType.INTEGER)
    @ApiModelProperty(value = "父级编号")
    private int parentId;

    @Column(name="valid_flag")
    @ColumnType(jdbcType = JdbcType.VARCHAR)
    @ApiModelProperty(value = "是否有效")
    private String validFlag;
}