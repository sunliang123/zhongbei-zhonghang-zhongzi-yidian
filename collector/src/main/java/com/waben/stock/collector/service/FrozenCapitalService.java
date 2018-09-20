package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.FrozenCapitalDao;
import com.waben.stock.collector.entity.FrozenCapital;

/**
 * 冻结资金 Service
 * 
 * @author luomengan
 *
 */
@Service
public class FrozenCapitalService {

	@Autowired
	private FrozenCapitalDao frozenCapitalDao;

	public FrozenCapital getFrozenCapitalInfo(Long id) {
		return frozenCapitalDao.retrieveFrozenCapitalById(id);
	}

	@Transactional
	public FrozenCapital addFrozenCapital(FrozenCapital frozenCapital) {
		return frozenCapitalDao.createFrozenCapital(frozenCapital);
	}

	@Transactional
	public FrozenCapital modifyFrozenCapital(FrozenCapital frozenCapital) {
		return frozenCapitalDao.updateFrozenCapital(frozenCapital);
	}

	@Transactional
	public void deleteFrozenCapital(Long id) {
		frozenCapitalDao.deleteFrozenCapitalById(id);
	}
	
	@Transactional
	public void deleteFrozenCapitals(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					frozenCapitalDao.deleteFrozenCapitalById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<FrozenCapital> frozenCapitals(int page, int limit) {
		return frozenCapitalDao.pageFrozenCapital(page, limit);
	}
	
	public List<FrozenCapital> list() {
		return frozenCapitalDao.listFrozenCapital();
	}

}
