package com.kisin.gen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.kisin.gen.dao.TableFieldMapper;
import com.kisin.gen.service.TableFieldService;
import org.springframework.stereotype.Service;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 09:53
 * @Description:
 */
@Service
public class TableFieldServiceImpl extends
    ServiceImpl<TableFieldMapper, TableField> implements TableFieldService {
}
