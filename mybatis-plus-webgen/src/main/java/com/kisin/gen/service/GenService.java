package com.kisin.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.kisin.gen.entity.Gen;

import java.util.List;
import java.util.Map;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-17 11:40
 * @Description:
 */
public interface GenService extends IService<Gen> {

    List<Map<String, String>> dbAllTableList();

    List<Map<String, String>> dbTableAllField(String tableName);

    void saveToTableInfo(Gen gen);

    void genCode(Gen gen, List<TableInfo> tableInfoList);
}
