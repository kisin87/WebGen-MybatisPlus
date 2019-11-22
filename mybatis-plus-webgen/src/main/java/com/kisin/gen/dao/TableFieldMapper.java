package com.kisin.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.config.po.TableField;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 09:50
 * @Description:
 */
public interface TableFieldMapper extends BaseMapper<TableField> {

    List<TableField> findByTableId(String tableId);
}
