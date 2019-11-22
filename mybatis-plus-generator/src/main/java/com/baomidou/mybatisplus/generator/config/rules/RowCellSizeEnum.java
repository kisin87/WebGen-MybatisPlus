package com.baomidou.mybatisplus.generator.config.rules;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-11-07 15:50
 * @Description:
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum RowCellSizeEnum {
    ONE(1, "一行一列", new int[]{12, 2, 10}),
    TWO(2, "一行二列", new int[]{6, 2, 10}),
    THR(3, "一行三列", new int[]{4, 2, 10});

    private int value;
    private String text;
    private int[] cellSizes;

    RowCellSizeEnum(int value, String text, int[] cellSizes){
        this.value = value;
        this.text = text;
        this.cellSizes = cellSizes;
    }

    private static Map<Integer, RowCellSizeEnum> valueMap = new HashMap<>();

    static {
        for (RowCellSizeEnum rowCellSizeEnum : RowCellSizeEnum.values()) {
            valueMap.put(rowCellSizeEnum.value, rowCellSizeEnum);
        }
    }

    public static RowCellSizeEnum getRowCellSize(int value){
        return valueMap.get(value);
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public int[] getCellSizes() {
        return cellSizes;
    }

}
