package com.waben.stock.datalayer.message.repository.impl;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.message.repository.DynamicQuerySqlDao;

/**
 * 动态执行查询sql
 * 
 * @author luomengan
 *
 */
@Repository("messageDynamicQuerySqlDao")
public class DynamicQuerySqlDaoImpl implements DynamicQuerySqlDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public <T> List<T> execute(Class<T> clazz, String sql, Map<Integer, MethodDesc> setMethodMap) {
		try {
			List<T> result = new ArrayList<>();
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> queryList = query.getResultList();
			if (queryList != null && queryList.size() > 0) {
				for (int i = 0; i < queryList.size(); i++) {
					T inner = clazz.newInstance();
					for (int j = 0; j < queryList.get(i).length; j++) {
						if (setMethodMap.containsKey(new Integer(j))) {
							String mName = setMethodMap.get(new Integer(j)).getName();
							Class<?>[] paramTypes = setMethodMap.get(new Integer(j)).getParamTypes();
							Method m = clazz.getMethod(mName, paramTypes);
							Object obj = queryList.get(i)[j];
							if (paramTypes[0].isEnum()) {
								if (obj != null) {
									m.invoke(inner, paramTypes[0].getEnumConstants()[(Integer) obj - 1]);
								}
								continue;
							}
							if (obj instanceof BigInteger) {
								BigInteger bigObj = (BigInteger) obj;
								if (paramTypes[0] == Integer.class) {
									m.invoke(inner, bigObj.intValue());
									continue;
								} else if (paramTypes[0] == Long.class) {
									m.invoke(inner, bigObj.longValue());
									continue;
								} else if (paramTypes[0] == Double.class) {
									m.invoke(inner, bigObj.doubleValue());
									continue;
								}
							} else if (obj instanceof BigDecimal) {
								BigDecimal bigObj = (BigDecimal) obj;
								if (paramTypes[0] == Integer.class) {
									m.invoke(inner, bigObj.intValue());
									continue;
								} else if (paramTypes[0] == Long.class) {
									m.invoke(inner, bigObj.longValue());
									continue;
								} else if (paramTypes[0] == Double.class) {
									m.invoke(inner, bigObj.doubleValue());
									continue;
								}
							} else if (obj instanceof Double && paramTypes[0] != Double.class) {
								Double dObj = (Double) obj;
								if (paramTypes[0] == Integer.class) {
									m.invoke(inner, dObj.intValue());
									continue;
								} else if (paramTypes[0] == Long.class) {
									m.invoke(inner, dObj.longValue());
									continue;
								}
							}
							m.invoke(inner, obj);
						}
					}
					result.add(inner);
				}
			}
			return result;
		} catch (Exception ex) {
			throw new RuntimeException("DynamicQuerySql execute failed!", ex);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T executeComputeSql(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql);
			List<T> list = query.getResultList();
			if (list.size() > 0) {
				return list.get(0);
			} else {
				return null;
			}
		} catch (Exception ex) {
			throw new RuntimeException("DynamicQuerySql execute failed!", ex);
		}
	}

}
