package com.waben.stock.applayer.strategist.crawler.util.commons;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by framework on 2014/11/7.
 */
public class BeanUtil extends BeanUtils {
    private static final String LIST_PREFIX = "[";
    private static final String LIST_SUFFIX = "]";


    public static <T> T convert(Object obj, Class<T> clazz) {

        return (T) ConversionUtil.convert(obj, clazz);
    }


    public static <T> T getValue(Object root, String field, Class<T> tClass) {
        Object obj = getValue(root, field);
        try {
            return convert(obj, tClass);
        } catch (Exception e) {
            throw new RuntimeException("can't case the[" + root.getClass().getName() + "] to " + tClass.getName());
        }
    }

    /**
     * @param obj
     * @param field
     * @param value
     */
    public static void setValue(Object obj, String field, Object value) {

        if (obj instanceof List) {
            ((List) obj).add(value);
        } else if (obj instanceof Map) {
            ((Map) obj).put(field, value);
        } else if (field.contains(".")) {
            StringTokenizer tokenizer = new StringTokenizer(field, ".");
            String name = tokenizer.nextToken();
            PropertyDescriptor descriptor = getPropertyDescriptor(obj.getClass(), name);

            Object readObj = ReflectionUtils.invokeMethod(descriptor.getReadMethod(), obj);

            if (readObj == null) {
                readObj = ClassUtil.newInstance(descriptor.getPropertyType());
                setValue(readObj, tokenizer.nextToken(), value);

                if (descriptor.getWriteMethod() == null) {
                    return;
                }

                ReflectionUtils.invokeMethod(descriptor.getWriteMethod(), obj, readObj);
            } else {
                setValue(readObj, tokenizer.nextToken(), value);
            }
        } else {
            PropertyDescriptor descriptor = getPropertyDescriptor(obj.getClass(), field);

            if (descriptor == null || value == null) {
                return;
            }

            if (descriptor.getWriteMethod() == null) {
                return;
            }

            value = convert(value, descriptor.getPropertyType());

            ReflectionUtils.invokeMethod(descriptor.getWriteMethod(), obj, value);
        }
    }


    public static Object getValue(Object root, String field) {

        String realField = field;
        Integer seq = -1;
        if (field.contains(LIST_PREFIX)) {
            realField = field.substring(field.lastIndexOf(LIST_PREFIX));
            String temp = field.substring(field.lastIndexOf(LIST_PREFIX) + LIST_PREFIX.length(), field.lastIndexOf(LIST_SUFFIX));
            seq = Integer.valueOf(temp);
        }
        Object obj = null;
        if (root instanceof Collection) {
            Collection collection = (Collection) root;
            obj = collection.toArray()[seq];
        } else if (root instanceof Map) {
            obj = (Map) ((Map) root).get(field);
        } else if (root.getClass().isArray()) {
            Object[] v = (Object[]) root;
            obj = v[seq];
        } else {
            obj = getFieldValue(root, field);
        }
        return null;
    }

    public static Object getFieldValue(Object obj, String field) {

        PropertyDescriptor propertyDescriptor = getPropertyDescriptor(obj.getClass(), field);
        try {
            return propertyDescriptor.getReadMethod().invoke(obj);
        } catch (Exception e) {
            // Ignore
        }

        return null;
    }

    public static Map<String, Object> convertBean2Map(Object obj) {

        Map<String, Object> resultMap = new HashMap<String, Object>();

        return convertBean2Map(obj, resultMap);
    }


    public static Map<String, Object> convertBean2Map(Object obj, Map<String, Object> resultMap) {
        PropertyDescriptor[] descriptors = getPropertyDescriptors(obj.getClass());

        for (PropertyDescriptor descriptor : descriptors) {
            if ("class".equals(descriptor.getName()))
                continue;
            Method method = descriptor.getReadMethod();
            Object val = ReflectionUtils.invokeMethod(method, obj);
            resultMap.put(descriptor.getName(), val);
        }
        return resultMap;
    }


    public static <T> T convertMap2Bean(Class<T> tClass, Map<String, Object> param) {

        T t = instantiate(tClass);

        convertMap2Bean(t, param);

        return t;
    }

    public static <T> T instantiate(Class<T> clazz) throws BeanInstantiationException {
        Assert.notNull(clazz, "Class must not be null");
        if (clazz.isInterface()) {
            throw new BeanInstantiationException(clazz, "Specified class is an interface");
        } else {
            try {
                return clazz.newInstance();
            } catch (InstantiationException var2) {
                throw new BeanInstantiationException(clazz, "Is it an abstract class?", var2);
            } catch (IllegalAccessException var3) {
                throw new BeanInstantiationException(clazz, "Is the constructor accessible?", var3);
            }
        }
    }

    public static void convertMap2Bean(Object obj, Map<String, Object> param) {

        for (String key : param.keySet()) {
            PropertyDescriptor descriptor = getPropertyDescriptor(obj.getClass(), key);

            if (descriptor == null) {
                continue;
            }
            Method writeMethod = descriptor.getWriteMethod();

            if (writeMethod == null) {
                continue;
            }

            Object val = convert(param.get(key), writeMethod.getParameterTypes()[0]);

            ReflectionUtils.invokeMethod(writeMethod, obj, val);
        }
    }


    public static void copyNotNullProperties(Object source, Object target) throws BeansException {


        copyNotNullProperties(source,target,null,null);
    }

    public static void copyNotNullProperties(Object source, Object target, String... ignoreProperties) throws BeansException {


        copyNotNullProperties(source,target,null,ignoreProperties);
    }


    private static void copyNotNullProperties(Object source, Object target, Class<?> editable, String... ignoreProperties) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }

            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = getPropertyDescriptors(actualEditable);
        List ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for (int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object ex = readMethod.invoke(source, new Object[0]);
                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            if (ex != null) {
                                writeMethod.invoke(target, new Object[]{ex});
                            }
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property \'" + targetPd.getName() + "\' from source to target", var15);
                        }
                    }
                }
            }
        }

    }

}
