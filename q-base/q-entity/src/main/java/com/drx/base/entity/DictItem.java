package com.drx.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典项表
 *
 * @TableName sys_dict_item
 */
@Data
public class DictItem implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
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
}