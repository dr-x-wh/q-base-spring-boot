package com.drx.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum USER_ROLE {

    USER("user", "用户"), ADMIN("admin", "管理员"), ADMINISTRATOR("administrator", "超级管理员");

    private String code;
    private String name;

}
