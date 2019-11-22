package com.kisin.gen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-18 09:52
 * @Description:
 */
public interface TableInfoService extends IService<TableInfo> {


    void saveInfoAndField(List<TableInfo> tableInfoList);

}
