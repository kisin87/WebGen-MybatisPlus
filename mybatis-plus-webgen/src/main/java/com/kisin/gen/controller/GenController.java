package com.kisin.gen.controller;

import com.kisin.gen.common.data.Result;
import com.kisin.gen.common.data.ResultCode;
import com.kisin.gen.entity.Gen;
import com.kisin.gen.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-14 17:27
 * @Description:
 */
@RestController
@RequestMapping("/data/gen")
public class GenController {

    @Autowired
    private GenService genService;

    @PostMapping("/list")
    Result<List<Gen>> list(){
        return new Result<>().setData(genService.list())
            .setCode(ResultCode.SUCCESS);
    }

    @PostMapping("/edit")
    Result<Gen> edit(Gen gen, String[] subTableArray){
        if(subTableArray!=null && subTableArray.length>0){
            gen.setSubTableStr(StringUtils.arrayToCommaDelimitedString(subTableArray));
        }
        genService.saveToTableInfo(gen);
        return new Result<>().setData(gen).setCode(ResultCode.SUCCESS);
    }
}
