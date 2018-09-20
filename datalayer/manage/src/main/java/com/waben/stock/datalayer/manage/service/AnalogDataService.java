package com.waben.stock.datalayer.manage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.manage.entity.AnalogData;
import com.waben.stock.datalayer.manage.repository.AnalogDataDao;
import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 模拟数据 Service
 * 
 * @author luomengan
 *
 */
@Service
public class AnalogDataService {

	@Autowired
	private AnalogDataDao dao;

	public Page<AnalogData> pageByType(AnalogDataType type, int page, int limit) {
		return dao.pageByType(type, page, limit);
	}

}
