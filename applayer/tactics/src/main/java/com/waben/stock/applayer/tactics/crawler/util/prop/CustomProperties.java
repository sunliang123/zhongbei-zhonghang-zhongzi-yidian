package com.waben.stock.applayer.tactics.crawler.util.prop;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomProperties {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private Properties props = new Properties();
	
	private static String propPath = "crawler/config/system.properties";
	
	@PostConstruct
	public void init() {
		try {
			InputStream in = CustomProperties.class.getClassLoader().getResourceAsStream(propPath);
			props.load(in);
		} catch (FileNotFoundException e) {
			logger.error("缓存crawler properties信息发生异常! {}", e.getMessage());
		} catch (IOException e) {
			logger.error("缓存crawler properties信息发生异常! {}", e.getMessage());
		}
	}
	
	public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key);

        return StringUtils.isEmpty(value) ? defaultValue : value;
    }


    public Integer getInteger(String key) {
        return getInteger(key, null);
    }

    public Integer getInteger(String key, Integer defaultValue) {
        String obj = getProperty(key);
        if (obj == null) {
            return defaultValue;
        }
        return Integer.parseInt(obj);
    }

    public Long getLong(String key) {
        return getLong(key, null);
    }

    public Long getLong(String key, Long defaultValue) {
        String obj = getProperty(key);
        if (obj == null) {
            return defaultValue;
        }
        return Long.parseLong(obj);
    }

    public Double getDouble(String key) {
        return getDouble(key, null);
    }

    public Double getDouble(String key, Double defaultValue) {
        String obj = getProperty(key);
        if (obj == null) {
            return defaultValue;
        }
        return Double.parseDouble(obj);
    }
	
}
