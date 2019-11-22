/*
 * Copyright (c) 2011-2020, baomidou (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * https://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.baomidou.mybatisplus.generator.config.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 表字段信息
 *
 * @author YangHu
 * @since 2016-12-03
 */
@Data
@KeySequence
@Accessors(chain = true)
@TableName("t_table_field")
public class TableField extends Model<TableField> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "field_id", type = IdType.UUID)
    private String fieldId;
    private String tableId;
    private String comment;
    private Boolean keyFlag;
    /**
     * 主键是否为自增类型
     */
    private Boolean keyIdentityFlag;
    private String name;
    private String propertyName;
    private String propertyType;
    private String type;
    private String lableName;
    private Integer cellNum;
    private Boolean edit;
    private Boolean list;
    private Integer inputType;
    private String inputTypeValue;
    private String dictType;

    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private boolean convert;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private IColumnType columnType;
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private String fill;
    /**
     * 自定义查询字段列表
     */
    @com.baomidou.mybatisplus.annotation.TableField(exist = false)
    private Map<String, Object> customMap;

    public TableField setConvert(boolean convert) {
        this.convert = convert;
        return this;
    }

    protected TableField setConvert(StrategyConfig strategyConfig) {
        if (strategyConfig.isEntityTableFieldAnnotationEnable()) {
            this.convert = true;
            return this;
        }
        if (strategyConfig.isCapitalModeNaming(name)) {
            this.convert = false;
        } else {
            // 转换字段
            if (NamingStrategy.underline_to_camel == strategyConfig.getColumnNaming()) {
                // 包含大写处理
                if (StringUtils.containsUpperCase(name)) {
                    this.convert = true;
                }
            } else if (!name.equals(propertyName)) {
                this.convert = true;
            }
        }
        return this;
    }

    public void setPropertyType(String propertyType){}

    public void setType(String type){
        if(this.columnType==null) {
            this.columnType = new MySqlTypeConvert().processTypeConvert(new GlobalConfig(), type);
        }
        this.type = type;
    }

    public IColumnType getColumnType(){
        if(this.columnType==null) {
            this.columnType = new MySqlTypeConvert().processTypeConvert(new GlobalConfig(), this.type);
        }
        return this.columnType;
    }

    public TableField setPropertyName(StrategyConfig strategyConfig, String propertyName) {
        this.propertyName = propertyName;
        this.setConvert(strategyConfig);
        return this;
    }

    public String getPropertyType() {
        if (null != columnType) {
            return columnType.getType();
        }
        return null;
    }

    /**
     * 按JavaBean规则来生成get和set方法
     */
    public String getCapitalName() {
        if (propertyName.length() <= 1) {
            return propertyName.toUpperCase();
        }
        String setGetName = propertyName;
        if (DbColumnType.BASE_BOOLEAN.getType().equalsIgnoreCase(getColumnType().getType())) {
            setGetName = StringUtils.removeIsPrefixIfBoolean(setGetName, Boolean.class);
        }
        // 第一个字母 小写、 第二个字母 大写 ，特殊处理
        String firstChar = setGetName.substring(0, 1);
        if (Character.isLowerCase(firstChar.toCharArray()[0])
            && Character.isUpperCase(setGetName.substring(1, 2).toCharArray()[0])) {
            return firstChar.toLowerCase() + setGetName.substring(1);
        }
        return firstChar.toUpperCase() + setGetName.substring(1);
    }

    public InputTypeEnum getInputTypeEnum() {
        if(this.inputType==null)return null;
        return InputTypeEnum.getInputType(this.inputType);
    }

    public RowCellSizeEnum getCellNumEnum() {
        if(this.cellNum==null)return null;
        return RowCellSizeEnum.getRowCellSize(this.cellNum);
    }
}
