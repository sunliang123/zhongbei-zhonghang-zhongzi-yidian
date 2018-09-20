package com.waben.stock.datalayer.manage.repository;

import org.springframework.data.domain.Page;

import com.waben.stock.datalayer.manage.entity.AnalogData;
import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 模拟数据 Dao
 * 
 * @author luomengan
 *
 */
public interface AnalogDataDao extends BaseDao<AnalogData, Long> {

	Page<AnalogData> pageByType(AnalogDataType type, int page, int limit);

}
