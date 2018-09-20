package com.waben.stock.datalayer.publisher.repository.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.CapitalFlow;
import com.waben.stock.datalayer.publisher.entity.Publisher;
import com.waben.stock.datalayer.publisher.repository.CapitalFlowDao;
import com.waben.stock.datalayer.publisher.repository.PublisherDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.CapitalFlowRepository;
import com.waben.stock.interfaces.enums.CapitalFlowExtendType;
import com.waben.stock.interfaces.enums.CapitalFlowType;
import com.waben.stock.interfaces.util.UniqueCodeGenerator;

/**
 * 资金流水 Dao实现
 *
 * @author luomengan
 */
@Repository
public class CapitalFlowDaoImpl implements CapitalFlowDao {

	@Autowired
	private CapitalFlowRepository repository;

	@Autowired
	private PublisherDao publisherDao;

	@Override
	public CapitalFlow create(CapitalFlow t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public CapitalFlow update(CapitalFlow t) {
		return repository.save(t);
	}

	@Override
	public CapitalFlow retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<CapitalFlow> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<CapitalFlow> list() {
		return repository.findAll();
	}

	@Override
	public Page<CapitalFlow> page(Specification<CapitalFlow> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public CapitalFlow create(Publisher publisher, CapitalFlowType type, BigDecimal amount, Date occurrenceTime,
			CapitalFlowExtendType extendType, Long extendId, BigDecimal availableBalance) {
		CapitalFlow t = new CapitalFlow();
		t.setAmount(amount);
		t.setOccurrenceTime(occurrenceTime);
		t.setPublisher(publisher);
		t.setType(type);
		t.setRemark(type.getType());
		t.setFlowNo(UniqueCodeGenerator.generateFlowNo());
		t.setExtendId(extendId);
		t.setExtendType(extendType);
		t.setAvailableBalance(availableBalance);
		return repository.save(t);
	}

	@Override
	public List<CapitalFlow> retriveByPublisherIdAndType(Long publisherId, CapitalFlowType type) {
		Publisher publisher = publisherDao.retrieve(publisherId);
		return repository.findByPublisherAndType(publisher, type);
	}

	@Override
	public BigDecimal promotionTotalAmount(Long publisherId) {
		return repository.promotionTotalAmount(publisherId, CapitalFlowType.Promotion);
	}

}
