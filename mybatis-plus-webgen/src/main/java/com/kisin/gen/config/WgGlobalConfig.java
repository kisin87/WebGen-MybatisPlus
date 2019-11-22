package com.kisin.gen.config;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.mysql.cj.jdbc.Driver;
import lombok.Data;
import lombok.Synchronized;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-15 11:24
 * @Description:
 */
@Data
public class WgGlobalConfig {

    private WgGlobalConfig() {}
    private static WgGlobalConfig instance = new WgGlobalConfig();

    public static WgGlobalConfig getInstance() {
        return instance;
    }

    private GlobalConfig globalConfig;
    private DataSourceConfig dataSourceConfig;
    private PackageConfig packageConfig;
    private StrategyConfig strategyConfig;

    public synchronized void init(boolean fileOverride, boolean enableCache, String outputDir, String author){
        if(globalConfig==null) globalConfig = new GlobalConfig();
        globalConfig.setFileOverride(fileOverride)
            .setEnableCache(enableCache)
            .setOutputDir(outputDir)
            .setActiveRecord(true)
            .setIdType(IdType.UUID)
            .setServiceName("%sService")
            .setBaseResultMap(true)
            .setAuthor(author)
            .setSwagger2(true);
        if(packageConfig==null) packageConfig = new PackageConfig();
        packageConfig.setController("controller");
    }

    public synchronized void initDb(String url, String username, String password, String... tablePrefix) {
        if(dataSourceConfig==null) dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
            .setTypeConvert(new MySqlTypeConvert(){
                @Override
                public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                    System.out.println("转换类型：" + fieldType);
                    // if ( fieldType.toLowerCase().contains( "tinyint" ) ) {
                    //    return DbColumnType.BOOLEAN;
                    // }
                    return super.processTypeConvert(globalConfig, fieldType);
                }
            })
            .setDriverName(Driver.class.getName())
        .setUrl(url)
        .setUsername(username)
        .setPassword(password);
        if(strategyConfig==null) strategyConfig = new StrategyConfig();
        if(tablePrefix!=null && tablePrefix.length>0){
            strategyConfig.setTablePrefix(tablePrefix);
        }
        strategyConfig.setNaming(NamingStrategy.underline_to_camel)
            .setEntityBooleanColumnRemoveIsPrefix(true).setEntityLombokModel(true);
    }


}
