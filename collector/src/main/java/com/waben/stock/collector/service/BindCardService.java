package com.waben.stock.collector.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.collector.dao.BindCardDao;
import com.waben.stock.collector.entity.BindCard;

/**
 * 绑卡 Service
 * 
 * @author luomengan
 *
 */
@Service
public class BindCardService {

	@Autowired
	private BindCardDao bindCardDao;

	public BindCard getBindCardInfo(Long id) {
		return bindCardDao.retrieveBindCardById(id);
	}

	@Transactional
	public BindCard addBindCard(BindCard bindCard) {
		return bindCardDao.createBindCard(bindCard);
	}

	@Transactional
	public BindCard modifyBindCard(BindCard bindCard) {
		return bindCardDao.updateBindCard(bindCard);
	}

	@Transactional
	public void deleteBindCard(Long id) {
		bindCardDao.deleteBindCardById(id);
	}
	
	@Transactional
	public void deleteBindCards(String ids) {
		if(ids != null) {
			String[] idArr= ids.split(",");
			for(String id : idArr) {
				if(!"".equals(id.trim())) {
					bindCardDao.deleteBindCardById(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<BindCard> bindCards(int page, int limit) {
		return bindCardDao.pageBindCard(page, limit);
	}
	
	public List<BindCard> list() {
		return bindCardDao.listBindCard();
	}

}
