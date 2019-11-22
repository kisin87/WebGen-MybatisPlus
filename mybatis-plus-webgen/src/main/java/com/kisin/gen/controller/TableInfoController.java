package com.kisin.gen.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.kisin.gen.common.data.Result;
import com.kisin.gen.common.data.ResultCode;
import com.kisin.gen.service.GenService;
import com.kisin.gen.service.TableInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-14 17:37
 * @Description:
 */
@RestController
@RequestMapping("/data/gen/table")
public class TableInfoController {

    @Autowired
    private TableInfoService tableInfoService;
    @Autowired
    private GenService genService;

    @GetMapping("/list")
    Result<List<TableInfo>> list(TableInfo info){
        List<TableInfo> tableInfoList = tableInfoService.list(new QueryWrapper<>(info));
        return new Result<>().setCode(ResultCode.SUCCESS).setData(tableInfoList);
    }

    @PostMapping("/edit")
    Result edit(@RequestBody JSONObject jsonObject){
        String genId = jsonObject.getJSONObject("gen").getString("genId");
        String gen = jsonObject.getJSONObject("gen").getString("gen");
        List<TableInfo> tableInfoList = jsonObject.getJSONArray("table").toJavaList(TableInfo.class);
        tableInfoService.saveInfoAndField(tableInfoList);
        if(gen!=null && "1".equals(gen.trim()))
            genService.genCode(genService.getById(genId), tableInfoList);
        return new Result().setCode(ResultCode.SUCCESS);
    }

}
