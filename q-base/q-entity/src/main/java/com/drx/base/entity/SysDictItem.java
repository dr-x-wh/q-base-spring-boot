package com.drx.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysDictItem {
    private Long id;

    private String dictCode;

    private String key;

    private String value;

    private Integer hasDefault;

    private Integer sort;

    private String description;

    private Date updatedAt;

    private String updatedBy;

    private Date createdAt;

    private String createdBy;
}