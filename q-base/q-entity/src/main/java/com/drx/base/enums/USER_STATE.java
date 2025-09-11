package com.drx.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum USER_STATE {

    NORMAL("1", "正常"), DISABLED("0", "禁用"), LOCKED("-1", "锁定");

    private String key;
    private String value;

}
