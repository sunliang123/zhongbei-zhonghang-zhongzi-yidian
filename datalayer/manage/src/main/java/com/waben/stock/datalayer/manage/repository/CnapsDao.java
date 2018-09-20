package com.waben.stock.datalayer.manage.repository;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.Cnaps;

/**
 * Cnaps Dao
 * 
 * @author luomengan
 *
 */
public interface CnapsDao extends BaseDao<Cnaps, Long> {

	List<Cnaps> retrieveByCityCodeAndClsCode(String cityCode, String clsCode);

}
