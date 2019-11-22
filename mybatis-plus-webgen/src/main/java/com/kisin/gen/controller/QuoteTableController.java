package com.kisin.gen.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kisin.gen.common.data.Result;
import com.kisin.gen.common.data.ResultCode;
import com.kisin.gen.entity.QuoteTable;
import com.kisin.gen.service.QuoteTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-25 10:48
 * @Description:
 */
@RestController
@RequestMapping("/data/gen/quote/table")
public class QuoteTableController {

    @Autowired
    private QuoteTableService quoteTableService;

    @GetMapping("/list")
    Result<List<QuoteTable>> list() {
        QuoteTable quoteTable = new QuoteTable();
        quoteTable.setDelFlag(false);
        QueryWrapper<QuoteTable> wrapper = new QueryWrapper(quoteTable);
        wrapper.orderByDesc("create_time");
        List<QuoteTable> quoteTableList = quoteTableService.list(wrapper);
        return new Result<>().setData(quoteTableList).setCode(ResultCode.SUCCESS);
    }

    @PostMapping("/edit")
    Result<QuoteTable> edit(@RequestBody JSONObject jsonObject) {
        QuoteTable quoteTable = jsonObject.getJSONObject("quote").toJavaObject(QuoteTable.class);
        quoteTable.setDelFlag(false);
        quoteTable.setCreateTime(System.currentTimeMillis());
        quoteTableService.saveOrUpdate(quoteTable);
        return new Result<>().setCode(ResultCode.SUCCESS).setData(quoteTable);
    }
}
