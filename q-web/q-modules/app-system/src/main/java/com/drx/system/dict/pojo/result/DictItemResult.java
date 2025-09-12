package com.drx.system.dict.pojo.result;

import lombok.Data;

@Data
public class DictItemResult {
    private String code;
    private String name;
    private String description;
    private Integer isDefault;
    private Integer sort;
}
