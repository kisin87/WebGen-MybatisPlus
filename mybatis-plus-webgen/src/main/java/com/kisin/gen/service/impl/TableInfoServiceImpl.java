package com.kisin.gen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.kisin.gen.dao.TableInfoMapper;
import com.kisin.gen.service.TableFieldService;
import com.kisin.gen.service.TableInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 09:54
 * @Description:
 */
@Service
public class TableInfoServiceImpl extends ServiceImpl<TableInfoMapper, TableInfo> implements TableInfoService {

    @Autowired
    private TableFieldService tableFieldService;

    @Override
    public void saveInfoAndField(List<TableInfo> tableInfoList) {
        if(tableInfoList==null || tableInfoList.isEmpty())return;
        tableInfoList.forEach(tableInfo -> {
            saveOrUpdate(tableInfo);
            tableFieldService.saveOrUpdateBatch(tableInfo.getFields());
        });
    }
}
