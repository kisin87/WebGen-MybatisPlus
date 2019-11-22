package com.kisin.gen.controller;

import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.InputTypeEnum;
import com.baomidou.mybatisplus.generator.config.rules.RowCellSizeEnum;
import com.kisin.dict.service.DictService;
import com.kisin.gen.common.data.Result;
import com.kisin.gen.common.data.ResultCode;
import com.kisin.gen.service.GenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-23 17:25
 * @Description:
 */

@RestController
@RequestMapping("/data/gen/data")
public class DataController {

    @Autowired
    private GenService genService;

    @Autowired
    private DictService dictService;

    @GetMapping
    Result<Map<String, Object>> data(){
        Map<String, Object> data = new HashMap<>();
        data.put("JAVA_TYPE_LIST", list().getData());
        data.put("INPUT_TYPE_LIST", objectList().getData());
        data.put("ROW_CELL_SIZE_LIST", rowCellSize().getData());
        data.put("DICT_TYPE_LIST", dictTypeList("请选择", "").getData());
        return new Result<Map<String, Object>>().setCode(ResultCode.SUCCESS).setData(data);
    }

    @GetMapping("/dict/type/list")
    Result<List<Map<String, String>>> dictTypeList(String selText, String selVal) {
        List<Map<String, String>> result = dictService.findDictType();
        if(selText!=null && !"".equals(selText.trim())){
            if(result==null) result = new ArrayList<>();
            Map<String, String> obj = new HashMap<>();
            obj.put("text", selText);
            obj.put("value", selVal);
            result.add(obj);
        }
        return new Result<>().setCode(ResultCode.SUCCESS).setData(result);
    }

    @GetMapping("/column/java/type/list")
    Result<Set<String>> list() {
        return new Result<>().setCode(ResultCode.SUCCESS).setData(DbColumnType.getAllType());
    }

    @GetMapping("/input/type/list")
    Result<InputTypeEnum[]> objectList() {
        return new Result<>().setCode(ResultCode.SUCCESS).setData(InputTypeEnum.values());
    }

    @GetMapping("/row-cell-size/list")
    Result<RowCellSizeEnum[]> rowCellSize() {
        return new Result<>().setCode(ResultCode.SUCCESS).setData(RowCellSizeEnum.values());
    }

    @GetMapping("/db/table-info/list")
    Result<Map<String, String>> dbTableInfoList(){
        return new Result<>().setData(genService.dbAllTableList()).setCode(ResultCode.SUCCESS);
    }

    @GetMapping("/db/table/field-info/list")
    Result<Map<String, String>> dbTableFieldInfoList(String tableName){
        return new Result<>().setData(genService.dbTableAllField(tableName)).setCode(ResultCode.SUCCESS);
    }

}
