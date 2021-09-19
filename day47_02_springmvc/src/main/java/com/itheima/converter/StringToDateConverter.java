package com.itheima.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 包名:com.itheima.converter
 * @author Leevi
 * 日期2020-11-14  11:10
 * Converter<String, Date> 接口的泛型: 1. 要转换的类型  2. 转换后的类型
 */
public class StringToDateConverter implements Converter<String, Date>{
    @Override
    public Date convert(String source) {
        //方法的参数是: 要转换的东西
        //方法的返回值: 转换后的东西
        //我们的目标是: 将yyyy-MM-dd类型的字符串，转成Date
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("转换失败，请检查你的日期格式！！！");
        }
    }
}
