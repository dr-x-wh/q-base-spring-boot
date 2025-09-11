package com.drx.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum USER_ROLE {

    USER("user", "用户", 1), ADMINISTRATOR("administrator", "开发者", 99);

    private String key;
    private String value;
    private Integer sort;

}
