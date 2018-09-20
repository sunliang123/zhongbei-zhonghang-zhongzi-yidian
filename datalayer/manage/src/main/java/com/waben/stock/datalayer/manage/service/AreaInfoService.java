package com.waben.stock.datalayer.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.manage.entity.AreaInfo;
import com.waben.stock.datalayer.manage.repository.AreaInfoDao;

/**
 * 区域 Service
 * 
 * @author luomengan
 *
 */
@Service
public class AreaInfoService {

	@Autowired
	private AreaInfoDao areaInfoDao;

	public List<AreaInfo> findByParentCode(String parentCode) {
		return areaInfoDao.retrieveByParentCode(parentCode);
	}

}
