package com.drx.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 字典项表
 * @TableName sys_dict_item
 */
@TableName(value ="sys_dict_item")
@Data
public class SysDictItem implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 所属字典类型编码
     */
    private Long dictId;

    /**
     * 字典项编码
     */
    private String itemKey;

    /**
     * 字典项名称
     */
    private String itemValue;

    /**
     * 是否默认值（0-否，1-是）
     */
    private Integer hasDefault;

    /**
     * 排序（数值越小越靠前）
     */
    private Integer sort;

    /**
     * 字典项描述
     */
    private String description;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 创建人
     */
    private String createdBy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}