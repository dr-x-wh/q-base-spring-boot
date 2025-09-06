package com.drx.base.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型表
 *
 * @TableName sys_dict
 */
@Data
public class Dict implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * ID
     */
    private Long id;
    /**
     * 字典类型编码
     */
    private String code;
    /**
     * 字典类型名称
     */
    private String name;
    /**
     * 字典描述
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