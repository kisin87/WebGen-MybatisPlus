package com.kisin.gen.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kisin.gen.entity.QuoteTable;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-24 18:05
 * @Description:
 */
public interface QuoteTableMapper extends BaseMapper<QuoteTable> {

    @Select("select * from t_quote_table")
    @ResultMap("com.kisin.gen.dao.QuoteTableMapper.InfoBaseResultMap")
    List<QuoteTable> findAll();
}
