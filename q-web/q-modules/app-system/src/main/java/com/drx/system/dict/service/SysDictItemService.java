package com.drx.system.dict.service;

import com.drx.db.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysDictItemService extends IService<SysDictItem> {

    List<SysDictItem> listByCode(String code);
    List<SysDictItem> allListByCode(String code);

}
