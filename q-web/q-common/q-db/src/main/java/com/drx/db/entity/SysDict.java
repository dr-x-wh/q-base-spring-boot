package com.drx.db.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class SysDict {
    @TableId
    private UUID id;
    private String code;
    private String name;
    private String description;
    private LocalDate updatedAt;
    private String updatedBy;
    private LocalDate createdAt;
    private String createdBy;
}
