package com.drx.system.dict.service;

import com.drx.starter.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author tom
* @description 针对表【sys_dict_item(字典项表)】的数据库操作Service
* @createDate 2025-09-06 10:43:26
*/
public interface SysDictItemService extends IService<SysDictItem> {
    List<SysDictItem> listByCode(String code);
    List<SysDictItem> allListByCode(String code);
}
