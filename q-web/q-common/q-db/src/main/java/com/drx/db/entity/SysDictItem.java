package com.drx.db.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SysDictItem {

    private UUID id;
    private UUID dictId;
    private String code;
    private String name;
    private String description;
    private String isDefault;
    private Long sort;
    private String state;
    private LocalDate updatedAt;
    private String updatedBy;
    private LocalDate createdAt;
    private String createdBy;
}
