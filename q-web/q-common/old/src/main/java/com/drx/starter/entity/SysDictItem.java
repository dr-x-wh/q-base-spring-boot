package com.drx.starter.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 字典项表
 * @TableName sys_dict_item
 */
@TableName(value ="sys_dict_item")
@Data
public class SysDictItem {
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
    private String code;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 字典项描述
     */
    private String description;

    /**
     * 是否默认值
     */
    private Integer isDefault;

    /**
     * 排序（数值越小越靠前）
     */
    private Integer sort;

    /**
     * 状态，1启用，0禁用
     */
    private String state;

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
}