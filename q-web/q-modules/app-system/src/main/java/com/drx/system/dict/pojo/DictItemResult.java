package com.drx.system.dict.pojo;

import lombok.Data;

@Data
public class DictItemResult {
    private String itemKey;
    private String itemValue;
    private Integer hasDefault;
    private Integer sort;
}
