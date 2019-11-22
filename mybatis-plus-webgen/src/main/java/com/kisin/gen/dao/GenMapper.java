package com.kisin.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kisin.gen.entity.Gen;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-17 11:39
 * @Description:
 */
public interface GenMapper extends BaseMapper<Gen> {

    @Select("show table status")
    List<Map<String, String>> selectDBAllTable();

    @Select("show full fields from ${tableName}")
    List<Map<String, String>> selectDBTableAllField(String tableName);
}
