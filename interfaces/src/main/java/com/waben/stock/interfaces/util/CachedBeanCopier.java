package com.waben.stock.interfaces.util;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
public class CachedBeanCopier {

    static final ConcurrentHashMap<String, BeanCopier> beanCopierMap = new ConcurrentHashMap<String, BeanCopier>();

    public static void copy(Object source, Object target) {
        String beanKey  = genKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        beanCopierMap.putIfAbsent(beanKey, copier);// putIfAbsent已经实现原子操作了。
        copier = beanCopierMap.get(beanKey);
        copier.copy(source, target, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
