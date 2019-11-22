package com.baomidou.mybatisplus.generator.config.rules;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-10-24 17:00
 * @Description:
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum InputTypeEnum {

    TEXT(0, "文本框", "<input type='text' class='form-control' name='$name$' value='${$name$}'>"),
    INPUT(1, "文本域", "<textarea name='$name$' class='form-control'>${$name$}</textarea>"),
    DATA_TABLE(2,"日期", "<input type='text' class='form-control' name='$name$' value='${$name$}'>"),
    SELECT(3, "下拉框", "<select class='form-control' name='$name$'>{%each $list$ %}<option value='$value$' $selected$>$text$</option>{%/each%}</select>");

    private int value;
    private String text;
    private String htmlTag;

    InputTypeEnum(int value, String text, String htmlTag){
        this.text = text;
        this.value = value;
        this.htmlTag = htmlTag;
    }

    private static Map<Integer, InputTypeEnum> valueMap = new HashMap<>();

    static {
        for (InputTypeEnum inputTypeEnum : InputTypeEnum.values()) {
            valueMap.put(inputTypeEnum.value, inputTypeEnum);
        }
    }

    public static InputTypeEnum getInputType(int value){
        return valueMap.get(value);
    }

    public int getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public String getHtmlTag() {
        return htmlTag;
    }
}
