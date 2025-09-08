package com.drx.system.dict.pojo.result;

import lombok.Data;

@Data
public class DictItemResult {
    private String itemKey;
    private String itemValue;
    private Integer hasDefault;
    private Integer sort;
}
