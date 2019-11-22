package com.kisin.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-17 11:32
 * @Description:
 */
@Data
@KeySequence
@TableName("t_gen")
public class Gen extends Model<Gen> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "gen_id", type = IdType.UUID)
    private String genId;
    private String moduleName;
    private String packageName;
    private String mainTable;
    private String subTableStr;

    public String[] getSubTable() {
        if(subTableStr==null || "".equals(subTableStr.trim()))
            return null;
        return subTableStr.split(",");
    }
}
