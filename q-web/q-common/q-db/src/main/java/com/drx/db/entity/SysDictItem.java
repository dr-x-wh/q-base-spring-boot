package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_dict_item
 */
@TableName(value ="sys_dict_item")
@Data
public class SysDictItem {
    /**
     * 
     */
    @TableId
    private Object id;

    /**
     * 
     */
    private Object dictId;

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String description;

    /**
     * 
     */
    private Boolean isDefault;

    /**
     * 
     */
    private Integer sort;

    /**
     * 
     */
    private String state;

    /**
     * 
     */
    private Date updatedAt;

    /**
     * 
     */
    private String updatedBy;

    /**
     * 
     */
    private Date createdAt;

    /**
     * 
     */
    private String createdBy;
}