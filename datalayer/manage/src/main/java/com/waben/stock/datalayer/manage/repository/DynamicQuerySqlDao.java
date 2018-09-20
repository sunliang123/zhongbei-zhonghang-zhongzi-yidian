package com.waben.stock.datalayer.manage.repository;

import java.util.List;

/**
 * 动态执行查询sql
 * 
 * @author luomengan
 *
 */
public interface DynamicQuerySqlDao {

	public List<Object[]> execute(String sql);

	public <T> T executeComputeSql(String sql);

}
