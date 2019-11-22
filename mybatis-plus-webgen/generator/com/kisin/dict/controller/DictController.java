package com.kisin.dict.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kisin.dict.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.kisin.gen.common.pagination.Page;
import com.kisin.dict.entity.Dict;
import com.kisin.gen.common.data.Result;
import com.kisin.gen.common.data.ResultCode;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 这个是字典表！！！ 前端控制器
 * </p>
 *
 * @author kisin
 * @since 2019-11-08
 */
@Controller
@RequestMapping("/dict/dict")
public class DictController {

    @Autowired
    DictService dictService;

    @GetMapping("/list")
    String list(){
        return "dict/list";
    }

    @ResponseBody
    @PostMapping("/list")
    Page<Dict> list(Page<Dict> page){
        return (Page<Dict>)dictService.page(page);
    }

    @GetMapping("/view")
    String view() {
        return "dict/view";
    }

    @ResponseBody
    @PostMapping("/data")
    Result<Dict> data(Dict dict) {
        return new Result<>().
            setCode(ResultCode.SUCCESS).
            setData(dictService.getOne(new QueryWrapper<>(dict)));
    }

    @GetMapping("/edit")
    String edit() {
        return "dict/edit";
    }

    @ResponseBody
    @PostMapping("/edit")
    Result<Dict> edit(Dict dict) {
        dictService.saveOrUpdate(dict);
        return new Result<>().
            setCode(ResultCode.SUCCESS).
            setData(dict);
    }

    @ResponseBody
    @PostMapping("/remove")
    Result<Dict> remove(Dict dict){
        dictService.remove(new QueryWrapper<>(dict));
        return new Result<>().
        setCode(ResultCode.SUCCESS).
        setData(dict);
    }

}
