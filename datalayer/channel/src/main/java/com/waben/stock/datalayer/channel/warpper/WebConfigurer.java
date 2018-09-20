package com.waben.stock.datalayer.channel.warpper;

import com.waben.stock.interfaces.warpper.converter.DateConverter;
import com.waben.stock.interfaces.warpper.converter.UniversalEnumConverterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Created by yuyidi on 2017/6/27.
 * @desc
 */
@Configuration
public class WebConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
        registry.addConverterFactory(new UniversalEnumConverterFactory());
        registry.addConverter(new DateConverter());
    }



}
