package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.CardBin;
import com.waben.stock.datalayer.manage.repository.CardBinDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.CardBinRepository;

/**
 * 银行卡片 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class CardBinDaoImpl implements CardBinDao {

	@Autowired
	private CardBinRepository repository;

	@Override
	public CardBin create(CardBin t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public CardBin update(CardBin t) {
		return repository.save(t);
	}

	@Override
	public CardBin retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<CardBin> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<CardBin> page(Specification<CardBin> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<CardBin> list() {
		return repository.findAll();
	}

	@Override
	public CardBin retrieveByBankCard(String bankCard) {
		return repository.findByBankCard(bankCard, bankCard);
	}

}
