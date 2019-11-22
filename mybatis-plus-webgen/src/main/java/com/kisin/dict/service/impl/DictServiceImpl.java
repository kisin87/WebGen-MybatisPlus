package com.kisin.dict.service.impl;

import com.kisin.dict.entity.Dict;
import com.kisin.dict.mapper.DictMapper;
import com.kisin.dict.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 这个是字典表！！！ 服务实现类
 * </p>
 *
 * @author kisin
 * @since 2019-11-08
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Map<String, String>> findDictType() {
        return dictMapper.selectDictType();
    }
}
