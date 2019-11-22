package com.kisin.gen;

import com.baomidou.mybatisplus.core.MybatisMapperRegistry;
import com.kisin.gen.common.data.DataMapperStore;
import com.kisin.gen.config.WgGlobalConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-15 11:20
 * @Description:
 */
@Component
public class WgStartupRunner implements CommandLineRunner {

    @Value("${default.gen.output.dir}")
    String outputDir;
    @Value("${default.gen.file.override}")
    String fileOverride;
    @Value("${default.gen.enable.cache}")
    String enableCache;
    @Value("${default.gen.db.url}")
    String dbUrl;
    @Value("${default.gen.db.username}")
    String dbUsername;
    @Value("${default.gen.db.password}")
    String dbPassword;
    @Value("${default.gen.db.table.prefix}")
    String tablePrefix;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public void run(String... args) throws Exception {
        WgGlobalConfig.getInstance().init(Boolean.parseBoolean(fileOverride),
            Boolean.parseBoolean(enableCache), outputDir, "kisin");

        WgGlobalConfig.getInstance().initDb(dbUrl, dbUsername, dbPassword, tablePrefix.split(","));

        //还要加个 swagger2
        MybatisMapperRegistry mapperRegistry = (MybatisMapperRegistry) sqlSessionFactory.getConfiguration().getMapperRegistry();
        DataMapperStore store = DataMapperStore.getInstance();
        mapperRegistry.getMappers().forEach( clazz-> {
            DataMapperStore.DataMapper dataMapper = store.newDataMapper();
            dataMapper.setClassName(clazz.getName());
            dataMapper.setSimpleName(clazz.getSimpleName());
            for (Method method : clazz.getDeclaredMethods()) {
                DataMapperStore.DataMapperFuns funs = store.newDataMapperFuns();
                funs.setMethodName(method.getName());
                funs.setReturnTypeClassName(method.getReturnType().getName());
                funs.setReturnTypeSimpleName(method.getReturnType().getSimpleName());
                dataMapper.addFuns(funs);
                for (Parameter parameter : method.getParameters()) {
                    DataMapperStore.DataMapperFunsParam param = store.newDataMapperFunsParam();
                    param.setParamName(parameter.getName());
                    param.setParamTypeClassName(parameter.getType().getName());
                    param.setParamTypeSimpleName(parameter.getType().getSimpleName());
                    funs.addParam(param);
                }
            }
            store.addMappers(dataMapper);
        });
    }




}
