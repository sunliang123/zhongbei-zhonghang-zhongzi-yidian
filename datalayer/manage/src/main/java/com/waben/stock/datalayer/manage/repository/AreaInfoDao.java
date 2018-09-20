package com.waben.stock.datalayer.manage.repository;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.AreaInfo;

/**
 * 区域 Dao
 * 
 * @author luomengan
 *
 */
public interface AreaInfoDao extends BaseDao<AreaInfo, Long> {

	List<AreaInfo> retrieveByParentCode(String parentCode);

}
