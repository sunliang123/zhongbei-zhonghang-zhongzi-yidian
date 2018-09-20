package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.AnalogData;
import com.waben.stock.datalayer.manage.repository.AnalogDataDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.AnalogDataRepository;
import com.waben.stock.interfaces.enums.AnalogDataType;

/**
 * 模拟数据 Dao
 * 
 * @author luomengan
 *
 */
@Repository
public class AnalogDataDaoImpl implements AnalogDataDao {

	@Autowired
	private AnalogDataRepository repository;

	@Override
	public AnalogData create(AnalogData t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public AnalogData update(AnalogData t) {
		return repository.save(t);
	}

	@Override
	public AnalogData retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<AnalogData> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<AnalogData> page(Specification<AnalogData> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<AnalogData> list() {
		return repository.findAll();
	}

	@Override
	public Page<AnalogData> pageByType(AnalogDataType type, int page, int limit) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "sortNum"));
		return repository.findByType(type, new PageRequest(page, limit, sort));
	}

}
