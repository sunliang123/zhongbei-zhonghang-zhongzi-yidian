package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.repository.DynamicQuerySqlDao;

/**
 * 动态执行查询sql
 * 
 * @author luomengan
 *
 */
@Repository("manageDynamicQuerySqlDao")
public class DynamicQuerySqlDaoImpl implements DynamicQuerySqlDao {

	@Autowired
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Object[]> execute(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql);
			List<Object[]> queryList = query.getResultList();
			return queryList;
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
