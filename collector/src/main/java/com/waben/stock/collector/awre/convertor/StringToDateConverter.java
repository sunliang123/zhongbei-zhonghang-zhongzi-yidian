package com.waben.stock.collector.awre.convertor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 字符串转日期的转换器
 * 
 * @author luomengan
 *
 */
public class StringToDateConverter implements Converter<String, Date> {

	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String shortDateFormat = "yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		source = source.trim();
		try {
			if (source.contains("-")) {
				SimpleDateFormat formatter;
				if (source.contains(":")) {
					formatter = new SimpleDateFormat(dateFormat);
				} else {
					formatter = new SimpleDateFormat(shortDateFormat);
				}
				Date dtDate = formatter.parse(source);
				return dtDate;
			} else if (source.matches("^\\d+$")) {
				Long lDate = new Long(source);
				return new Date(lDate);
			}
		} catch (Exception e) {
			throw new RuntimeException(String.format("parser %s to Date fail", source));
		}
		throw new RuntimeException(String.format("parser %s to Date fail", source));
	}

}
