package com.waben.stock.datalayer.stockcontent.repository.impl;

import com.waben.stock.datalayer.stockcontent.entity.StrategyType;
import com.waben.stock.datalayer.stockcontent.repository.StrategyTypeDao;
import com.waben.stock.datalayer.stockcontent.repository.impl.jpa.StraegyTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
@Repository
public class StraegyTypeDaoImpl implements StrategyTypeDao {

    @Autowired
    private StraegyTypeRepository repository;

    @Override
    public List<StrategyType> retrieveWithEnable() {
        return repository.findAllByState(true);
    }

    @Override
    public StrategyType create(StrategyType straegyType) {
        return repository.save(straegyType);
    }

    @Override
    public void delete(Long id) {
    	repository.delete(id);
    }

    @Override
    public StrategyType update(StrategyType straegyType) {
        return repository.save(straegyType);
    }

    @Override
    public StrategyType retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<StrategyType> page(int page, int limit) {
        return repository.findAll(new PageRequest(page, limit));
    }

    @Override
    public Page<StrategyType> page(Specification<StrategyType> specification, Pageable pageable) {
        return repository.findAll(specification,pageable);
    }

    @Override
    public List<StrategyType> list() {
        return repository.findAll();
    }
}
