package com.waben.stock.applayer.promotion.warpper.converter;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import com.waben.stock.interfaces.enums.CommonalityEnum;

/**
* @author yuyidi 2017-06-27 10:18:51
* @class com.wangbei.awre.converter.UniversalEnumConverterFactory
* @description 通用的枚举转换工厂类
*/
public class UniversalEnumConverterFactory implements ConverterFactory<String, CommonalityEnum> {
    Logger logger = LoggerFactory.getLogger(getClass());
    public static final Map<Class, Converter> converterMap = new WeakHashMap<>();

    @Override
    public <T extends CommonalityEnum> Converter<String, T> getConverter(Class<T> targetType) {
        Converter result = converterMap.get(targetType);
        if (result == null) {
            result = new StrToEnum<>(targetType);
            converterMap.put(targetType, result);
        }
        return result;
    }

    class StrToEnum<T extends CommonalityEnum> implements Converter<String, T> {
        private final Class<T> enumType;
        private Map<String, T> enumMap = new HashMap<>();

        public StrToEnum(Class<T> enumType) {
            this.enumType = enumType;
            T[] enums = enumType.getEnumConstants();
            for (T e : enums) {
                enumMap.put(e.getIndex(), e);
            }
        }


        @Override
        public T convert(String source) {
            logger.info("枚举参数：{}",source);
            T result = enumMap.get(source);
            if (result == null) {
                throw new IllegalArgumentException("No element matches " + source);
            }
            return result;
        }
    }
}
