package com.kisin.gen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.BeetlTemplateEngine;
import com.kisin.gen.config.WgGlobalConfig;
import com.kisin.gen.dao.GenMapper;
import com.kisin.gen.dao.TableFieldMapper;
import com.kisin.gen.dao.TableInfoMapper;
import com.kisin.gen.entity.Gen;
import com.kisin.gen.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-17 11:42
 * @Description:
 */
@Service
public class GenServiceImpl extends ServiceImpl<GenMapper, Gen> implements GenService {

    @Autowired
    private GenMapper genMapper;
    @Autowired
    private TableInfoMapper tableInfoMapper;
    @Autowired
    private TableFieldMapper tableFieldMapper;

    @Override
    public List<Map<String, String>> dbAllTableList() {
        return genMapper.selectDBAllTable();
    }

    @Override
    public void saveToTableInfo(Gen gen) {
        genMapper.insert(gen);
        List<TableInfo> tableInfoList = conver(gen);
        if(tableInfoList==null || tableInfoList.isEmpty())return;
        tableInfoList.forEach(tableInfo -> {
            tableInfo.setMainTable(gen.getMainTable().toLowerCase().equals(tableInfo.getName().toLowerCase()));
            tableInfo.setGenId(gen.getGenId());
            tableInfoMapper.insert(tableInfo);
            insertField(tableInfo.getFields(), tableInfo);
        });
    }

    private void insertField(List<TableField> fieldList, TableInfo tableInfo){
        if(fieldList==null || fieldList.isEmpty())return ;
        fieldList.forEach(field -> {
            field.setTableId(tableInfo.getTableId());
            tableFieldMapper.insert(field);
        });
    }

    private List<TableInfo> conver(Gen gen){
        String tableS = gen.getMainTable()+
            ((gen.getSubTableStr()!=null && !gen.getSubTableStr().isEmpty())?","+gen.getSubTableStr():"");

        WgGlobalConfig instance = WgGlobalConfig.getInstance();
        PackageConfig packageConfig = instance.getPackageConfig()
            .setModuleName(gen.getModuleName())
            .setParent(gen.getPackageName());
        StrategyConfig strategyConfig = instance.getStrategyConfig()
            .setInclude(tableS.split(","));

        ConfigBuilder configBuilder = new ConfigBuilder(packageConfig,
            instance.getDataSourceConfig(),
            strategyConfig,
            new TemplateConfig(),
            instance.getGlobalConfig());
        return configBuilder.getTableInfoList();
    }

    @Override
    public List<Map<String, String>> dbTableAllField(String tableName) {
        return genMapper.selectDBTableAllField(tableName);
    }

    @Override
    public void genCode(Gen gen, List<TableInfo> tableInfoList) {
        WgGlobalConfig instance = WgGlobalConfig.getInstance();
        PackageConfig packageConfig = instance.getPackageConfig()
            .setModuleName(gen.getModuleName())
            .setParent(gen.getPackageName());
        StrategyConfig strategyConfig = instance.getStrategyConfig();
        ConfigBuilder configBuilder = new ConfigBuilder(packageConfig,
            strategyConfig,
            new TemplateConfig(),
            instance.getGlobalConfig(), tableInfoList);
        /*configBuilder.getTableInfoList().forEach(info->{
            info.getFields().forEach(field->{
                System.out.print(field.getColumnType());
                System.out.println(field.getCapitalName());
            });
        });*/
        AutoGenerator wmpg = new AutoGenerator().setConfig(configBuilder);
        wmpg.setTemplateEngine(new BeetlTemplateEngine());
        wmpg.execute();
    }
}
