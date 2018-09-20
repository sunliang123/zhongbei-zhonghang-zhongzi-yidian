package com.waben.stock.applayer.strategist.wrapper.data;

import org.hibernate.bytecode.instrumentation.spi.LazyPropertyInitializer;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.jpa.event.internal.core.JpaMergeEventListener;
import org.hibernate.persister.entity.EntityPersister;
import org.hibernate.property.access.internal.PropertyAccessStrategyBackRefImpl;
import org.hibernate.type.AbstractStandardBasicType;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Created by yuyidi on 2017/7/4.
 * @desc
 */
@Component
public class CustomMergeEventListener extends JpaMergeEventListener {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected void copyValues(EntityPersister persister, Object entity, Object target, SessionImplementor source, Map
            copyCache) {
        Object[] copiedValues = replace(persister.getPropertyValues(entity), persister.getPropertyValues(target),
                persister.getPropertyTypes(), source, target, copyCache);
        persister.setPropertyValues(target, copiedValues);
    }


    private Object[] replace(Object[] original, Object[] target, Type[] types, SessionImplementor session, Object
            owner, Map copyCache) {
        Object[] copied = new Object[original.length];
        for (int i = 0; i < types.length; ++i) {
            if (original[i] != LazyPropertyInitializer.UNFETCHED_PROPERTY && original[i] !=
                    PropertyAccessStrategyBackRefImpl.UNKNOWN) {
                if (target[i] == LazyPropertyInitializer.UNFETCHED_PROPERTY) {
                    if (types[i].isMutable()) {
                        logger.info("可变的类型对象:{}", types[i]);
                        copied[i] = types[i].deepCopy(original[i], session.getFactory());
                    } else {
                        copied[i] = original[i];
                    }
                } else {
                    //若当前是基础数据类型
                    if (types[i] instanceof AbstractStandardBasicType) {
                        copied[i] = original[i];
                        if ((original[i] == null || "".equals(original[i])) && target[i] != null) {
                            copied[i] = target[i];
                        }
                    } else {
                        copied[i] = types[i].replace(original[i], target[i], session, owner, copyCache);
                    }

                }
            } else {
                copied[i] = target[i];
            }
        }
        return copied;
    }


}
