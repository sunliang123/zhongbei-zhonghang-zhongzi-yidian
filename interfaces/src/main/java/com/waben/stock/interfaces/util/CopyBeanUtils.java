package com.waben.stock.interfaces.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.DigestUtils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

public class CopyBeanUtils {
	/**
	 * Bean 对象属性copy Util
	 *
	 * @author
	 */
	private static Logger logger = LoggerFactory.getLogger(CopyBeanUtils.class);
	private static ConcurrentHashMap<String, BeanCopier> cache = new ConcurrentHashMap<>();

	/**
	 * @param target
	 *            目标对对象class
	 * @param sourceObj
	 *            复制的源对象
	 * @param useConverter
	 * @return
	 * @throws Exception
	 */
	public static <T> T copyBeanProperties(Class<T> target, Object sourceObj, boolean useConverter) {
		if (sourceObj == null)
			return null;
		T t;
		try {
			t = target.newInstance();
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		String key = sourceObj.getClass().getName() + target.getName();
		BeanCopier copier = cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(sourceObj.getClass(), target, useConverter, key);
		}
		copier.copy(sourceObj, t, null);
		return t;
	}

	public static <T> T copyBeanProperties(Class<T> target, Object sourceObj, Converter converter) {
		if (sourceObj == null)
			return null;
		T t;
		try {
			t = target.newInstance();
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
		String key = sourceObj.getClass().getName() + target.getName();
		BeanCopier copier = cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(sourceObj.getClass(), target, true, key);
		}
		copier.copy(sourceObj, t, converter);
		return t;
	}

	/**
	 * @param sourceObj
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param useConverter
	 * @return
	 * @throws Exception
	 */
	public static <T> T copyBeanProperties(Object sourceObj, T target, boolean useConverter) {
		if (sourceObj == null || target == null)
			return null;
		String key = sourceObj.getClass().getName() + target.getClass().getName();
		BeanCopier copier = cache.get(key);
		if (copier == null) {
			copier = createBeanCopier(sourceObj.getClass(), target.getClass(), useConverter, key);
		}
		copier.copy(sourceObj, target, null);
		return target;
	}

	public static <T> Set<T> copySetBeanPropertiesToSet(Set<?> sourceObjs, Set<T> targets, Class<T> targetType) {
		if (sourceObjs == null || targets == null || targetType == null)
			return null;
		T t;
		for (Object o : sourceObjs) {
			try {
				t = targetType.newInstance();
				targets.add(copyBeanProperties(o, t, false));
			} catch (InstantiationException e) {
				logger.error("", e);
			} catch (IllegalAccessException e) {
				logger.error("", e);
			}
		}
		return targets;
	}

	public static <T> List<T> copyListBeanPropertiesToList(List<?> sourceObjs, List<T> targets, Class<T> targetType) {
		if (sourceObjs == null || targets == null || targetType == null)
			return null;
		T t;
		for (Object o : sourceObjs) {
			try {
				t = targetType.newInstance();
				targets.add(copyBeanProperties(o, t, false));
			} catch (InstantiationException e) {
				logger.error("", e);
			} catch (IllegalAccessException e) {
				logger.error("", e);
			}
		}
		return targets;
	}

	public static <T> List<T> copyListBeanPropertiesToList(List<?> sourceObjs, Class<T> targetType) {
		List<T> targets = new ArrayList<T>();
		return copyListBeanPropertiesToList(sourceObjs, targets, targetType);
	}

	public static <T> Set<T> copySetBeanPropertiesToSet(Set<?> sourceObjs, Class<T> targetType) {
		Set<T> targets = new HashSet<T>();
		return copySetBeanPropertiesToSet(sourceObjs, targets, targetType);
	}

	private static String getHashKey(String str) {
		if (str == null)
			return null;
		return DigestUtils.md5DigestAsHex(str.getBytes());
	}

	@SuppressWarnings({ "rawtypes" })
	private static BeanCopier createBeanCopier(Class sourceClass, Class targetClass, boolean useConverter,
			String cacheKey) {
		BeanCopier copier = BeanCopier.create(sourceClass, targetClass, useConverter);
		cache.putIfAbsent(cacheKey, copier);
		return copier;
	}
}