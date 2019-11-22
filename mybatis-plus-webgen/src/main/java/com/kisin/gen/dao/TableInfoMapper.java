package com.kisin.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 09:49
 * @Description:
 */
public interface TableInfoMapper extends BaseMapper<TableInfo> {

    List<TableInfo> findAllInfo();

    @Select("select * from t_table_info where name = #{name}")
    TableInfo findByName(String name);
}
