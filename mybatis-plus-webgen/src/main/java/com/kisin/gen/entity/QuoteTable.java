package com.kisin.gen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-24 17:58
 * @Description:
 */
@Data
@KeySequence
@TableName("t_quote_table")
public class QuoteTable extends Model<QuoteTable> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "quote_id", type = IdType.UUID)
    private String quoteId;
    private String tableName;
    private String fields;
    private String whereFieldJson;
    private String matchField;
    private String descs;
    private Boolean delFlag;
    private Long createTime;

    private TableInfo tableInfo;

    @Override
    protected Serializable pkVal() {
        return this.quoteId;
    }

    @Override
    public String toString() {
        return "QuoteTable{" +
            "quoteId='" + quoteId + '\'' +
            ", tableName='" + tableName + '\'' +
            ", fields='" + fields + '\'' +
            ", whereFieldJson='" + whereFieldJson + '\'' +
            ", matchField='" + matchField + '\'' +
            ", descs='" + descs + '\'' +
            ", delFlag=" + delFlag +
            ", createTime=" + createTime +
            '}';
    }
}
