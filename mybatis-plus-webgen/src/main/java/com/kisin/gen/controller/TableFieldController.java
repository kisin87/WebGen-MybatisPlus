package com.kisin.gen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.generator.config.po.TableField;
import com.kisin.gen.common.pagination.Page;
import com.kisin.gen.service.TableFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-23 15:24
 * @Description:
 */

@RestController
@RequestMapping("/data/gen/table/field")
public class TableFieldController {

    @Autowired
    private TableFieldService tableFieldService;

    @GetMapping("/list")
    Page<TableField> list(Page<TableField> page, TableField field){
        return (Page<TableField>)tableFieldService.page(page, new QueryWrapper<>(field));
    }
}
