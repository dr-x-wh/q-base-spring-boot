package com.drx.starter.mapper;

import com.drx.starter.entity.SysDictItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author tom
* @description 针对表【sys_dict_item(字典项表)】的数据库操作Mapper
* @createDate 2025-09-06 11:30:15
* @Entity com.drx.starter.domain.SysDictItem
*/
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

    List<SysDictItem> selectByDictCode(String code);

}




