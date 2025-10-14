package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

/**
 *
 * @TableName sys_dict_item
 */
@TableName(value = "sys_dict_item")
@Data
public class SysDictItem {
    @TableId
    private UUID id;
    private UUID dictId;
    private String code;
    private String name;
    private String description;
    private Boolean isDefault;
    private Integer sort;
    private String state;
    private Date updatedAt;
    private String updatedBy;
    private Date createdAt;
    private String createdBy;
}
