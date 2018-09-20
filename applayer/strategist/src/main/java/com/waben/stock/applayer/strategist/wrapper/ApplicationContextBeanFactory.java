package com.waben.stock.applayer.strategist.wrapper;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerGroup;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.waben.stock.applayer.strategist.wrapper.data.CustomMergeEventListener;

/**
 * @author yuyidi 2017-07-05 17:08:58
 * @class com.wangbei.awre.ApplicationContextBeanFactory
 * @description applicationContext工具类
 */
@Component
public class ApplicationContextBeanFactory implements ApplicationContextAware {

    Logger logger = LoggerFactory.getLogger(getClass());

    private static ApplicationContext applicationContext;


    @Autowired
    private EntityManagerFactory entityManagerFactory;
    
    @Autowired
    private CustomMergeEventListener mergeEventListener;
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        EventListenerRegistry registry = ((SessionFactoryImpl) entityManagerFactory.unwrap(SessionFactory.class))
                .getServiceRegistry().getService(
                        EventListenerRegistry.class);
        EventListenerGroup group = registry.getEventListenerGroup(EventType.MERGE);
        group.clear();
        group.appendListener(mergeEventListener);
    }

    public static <T> T getBean(Class<T> tClass){
        return applicationContext.getBean(tClass);
    }
}
