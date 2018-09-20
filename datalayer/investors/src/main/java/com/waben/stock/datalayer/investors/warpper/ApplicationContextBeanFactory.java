package com.waben.stock.datalayer.investors.warpper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author yuyidi 2017-07-05 17:08:58
 * @class com.wangbei.awre.ApplicationContextBeanFactory
 * @description applicationContext工具类
 */
@Component
public class ApplicationContextBeanFactory implements ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static ApplicationContext applicationContext;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }
}
