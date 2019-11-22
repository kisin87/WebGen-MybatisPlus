package com.kisin.dict.mapper;

import com.kisin.dict.entity.Dict;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 这个是字典表！！！ Mapper 接口
 * </p>
 *
 * @author kisin
 * @since 2019-11-08
 */
public interface DictMapper extends BaseMapper<Dict> {


    @Select("select distinct dict_type as value, type_name as text from t_dict")
    List<Map<String, String>> selectDictType();

    @Select("select * from t_dict where dict_type=#{dictType} and dict_value=#{dictValue}")
    Dict selectDict(@Param("dictType") String dictType, @Param("dictValue") String dictValue);
}
