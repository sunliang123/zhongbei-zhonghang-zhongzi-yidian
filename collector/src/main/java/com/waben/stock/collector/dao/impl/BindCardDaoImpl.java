package com.waben.stock.collector.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.waben.stock.collector.dao.BindCardDao;
import com.waben.stock.collector.dao.impl.jpa.BindCardRepository;
import com.waben.stock.collector.entity.BindCard;

/**
 * 绑卡 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BindCardDaoImpl implements BindCardDao {

	@Autowired
	private BindCardRepository bindCardRepository;

	@Override
	public BindCard createBindCard(BindCard bindCard) {
		return bindCardRepository.save(bindCard);
	}

	@Override
	public void deleteBindCardById(Long id) {
		bindCardRepository.delete(id);
	}

	@Override
	public BindCard updateBindCard(BindCard bindCard) {
		return bindCardRepository.save(bindCard);
	}

	@Override
	public BindCard retrieveBindCardById(Long id) {
		return bindCardRepository.findById(id);
	}

	@Override
	public Page<BindCard> pageBindCard(int page, int limit) {
		return bindCardRepository.findAll(new PageRequest(page, limit));
	}
	
	@Override
	public List<BindCard> listBindCard() {
		return bindCardRepository.findAll();
	}

	@Override
	public BindCard getByDomainAndDataId(String domain, Long dataId) {
		return bindCardRepository.findByDomainAndDataId(domain, dataId);
	}

}
