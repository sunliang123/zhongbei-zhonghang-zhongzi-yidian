package com.waben.stock.datalayer.manage.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.Cnaps;

/**
 * 支付支行信息 Jpa
 * 
 * @author luomengan
 *
 */
public interface CnapsRepository extends CustomJpaRepository<Cnaps, Long> {

	List<Cnaps> findByCityCodeAndClsCode(String cityCode, String clsCode);

}
