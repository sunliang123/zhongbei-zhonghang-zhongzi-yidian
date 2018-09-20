package com.waben.stock.applayer.strategist.crawler.util.commons;

import org.springframework.core.convert.support.DefaultConversionService;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import com.waben.stock.applayer.strategist.crawler.util.commons.support.DateToStringConverter;
import com.waben.stock.applayer.strategist.crawler.util.commons.support.StringToDateConverter;

/**
 * Created by heavenick on 2016/5/10.
 */
public class ConversionUtil {
    private static DefaultConversionService conversionService = new DefaultConversionService();

    static {
        conversionService.addConverter(new DateToStringConverter());
        conversionService.addConverter(new StringToDateConverter());
    }

    public static Object convert(Object source, Class<?> targetType) {

        return conversionService.convert(source, targetType);
    }


    public static boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        return conversionService.canConvert(sourceType, targetType);
    }


    public static Object jsonToObject(String json, Object obj) {

        return TypeUtils.cast(JSONObject.parse(json), obj.getClass(), ParserConfig.getGlobalInstance());
    }

    public static DefaultConversionService getConversionService() {
        return conversionService;
    }
}
