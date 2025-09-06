package com.drx.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 所属字典类型编码
     */
    @TableField(value = "dict_code")
    private String dict_code;

    /**
     * 字典项编码
     */
    @TableField(value = "key")
    private String key;

    /**
     * 字典项名称
     */
    @TableField(value = "value")
    private String value;

    /**
     * 是否默认值（0-否，1-是）
     */
    @TableField(value = "has_default")
    private Integer has_default;

    /**
     * 排序（数值越小越靠前）
     */
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 字典项描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    private LocalDateTime updated_at;

    /**
     * 更新人
     */
    @TableField(value = "updated_by")
    private String updated_by;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private LocalDateTime created_at;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private String created_by;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}