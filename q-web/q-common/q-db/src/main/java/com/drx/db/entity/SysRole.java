package com.drx.db.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SysRole {

    private UUID id;
    private String code;
    private String name;
    private String description;
    private String state;
    private LocalDate updatedAt;
    private String updatedBy;
    private LocalDate createdAt;
    private String createdBy;
}
