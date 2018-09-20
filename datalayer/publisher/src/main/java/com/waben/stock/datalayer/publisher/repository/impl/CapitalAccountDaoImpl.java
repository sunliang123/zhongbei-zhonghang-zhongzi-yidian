package com.waben.stock.datalayer.publisher.repository.impl;

import java.util.List;

import com.waben.stock.datalayer.publisher.entity.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.CapitalAccount;
import com.waben.stock.datalayer.publisher.repository.CapitalAccountDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.CapitalAccountRepository;

/**
 * 资金账号 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class CapitalAccountDaoImpl implements CapitalAccountDao {

	@Autowired
	private CapitalAccountRepository repository;

	@Override
	public CapitalAccount create(CapitalAccount t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public CapitalAccount update(CapitalAccount t) {
		return repository.save(t);
	}

	@Override
	public CapitalAccount retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<CapitalAccount> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<CapitalAccount> list() {
		return repository.findAll();
	}

	@Override
	public Page<CapitalAccount> page(Specification<CapitalAccount> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public CapitalAccount retriveByPublisherSerialCode(String serialCode) {
		return repository.findByPublisherSerialCode(serialCode);
	}

	@Override
	public CapitalAccount retriveByPublisherId(Long publisherId) {
		Publisher publisher = new Publisher();
		publisher.setId(publisherId);
		return repository.findByPublisher(publisher);
	}

}
