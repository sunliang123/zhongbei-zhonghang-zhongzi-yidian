package com.waben.stock.applayer.promotion.warpper.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

/**
 * @author yuyidi 2017-07-04 16:28:45
 * @class com.wangbei.awre.converter.DateConverter
 * @description 自定义日期类型转换
 */
public class DateConverter implements Converter<String, Date> {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Date convert(String source) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        try {
            return dateFormat.parse(source);
        } catch (ParseException e) {
            logger.error("自定义日期绑定转换异常:{},errorOffset{},{}", e.getMessage(), e.getErrorOffset(), e);
        }
        return null;
    }
}
