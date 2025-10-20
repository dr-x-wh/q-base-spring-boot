package com.drx.db.entity;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SysUserRole {

    private UUID userId;
    private UUID roleId;
    private LocalDate createdAt;
    private String createdBy;
}
