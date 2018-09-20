package com.waben.stock.datalayer.manage.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.AreaInfo;

/**
 * 区域 Jpa
 * 
 * @author luomengan
 *
 */
public interface AreaInfoRepository extends CustomJpaRepository<AreaInfo, Long> {

	List<AreaInfo> findByParentCode(String parentCode);

}
