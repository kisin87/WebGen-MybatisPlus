package com.kisin.gen.config.beelt.function;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.beetl.core.Context;
import org.beetl.core.Function;

import java.io.IOException;

/**
 * @Author: shebin(kisin)
 * @Date: Create in 2019-11-04 11:44
 * @Description:
 */
public class ClassNameToField implements Function {

    @Override
    public Object call(Object[] objects, Context context) {
        String className = objects[0].toString();
        if(className!=null){
            try{
                context.byteWriter.writeString(StringUtils.firstCharToLower(className));
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        return "";
    }
}
