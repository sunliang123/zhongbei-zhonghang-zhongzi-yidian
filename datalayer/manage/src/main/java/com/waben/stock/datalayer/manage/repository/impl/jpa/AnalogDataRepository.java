package com.waben.stock.datalayer.manage.repository.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.waben.stock.datalayer.manage.entity.AnalogData;
import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 模拟数据 Jpa
 * 
 * @author luomengan
 *
 */
public interface AnalogDataRepository extends CustomJpaRepository<AnalogData, Long> {

	Page<AnalogData> findByType(AnalogDataType type, Pageable pageable);

}
