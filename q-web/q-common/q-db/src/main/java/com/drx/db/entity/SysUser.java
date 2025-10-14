package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser {
    /**
     * 
     */
    @TableId
    private Object id;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String nickname;

    /**
     * 
     */
    private String avatarUrl;

    /**
     * 
     */
    private String gender;

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