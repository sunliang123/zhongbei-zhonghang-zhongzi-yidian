package com.waben.stock.datalayer.stockcontent.repository.impl;

import com.waben.stock.datalayer.stockcontent.entity.StockExponent;
import com.waben.stock.datalayer.stockcontent.repository.StockExponentDao;
import com.waben.stock.datalayer.stockcontent.repository.impl.jpa.StockExponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc
 */
@Repository
public class StockExponentDaoImpl implements StockExponentDao {

    @Autowired
    private StockExponentRepository repository;

    @Override
    public StockExponent retrieveWithExponeneCode(String exponentCode) {
        return repository.findByExponentCode(exponentCode);
    }

    @Override
    public StockExponent create(StockExponent stockExponent) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public StockExponent update(StockExponent stockExponent) {
        return null;
    }

    @Override
    public StockExponent retrieve(Long id) {
        return null;
    }

    @Override
    public Page<StockExponent> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<StockExponent> page(Specification<StockExponent> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<StockExponent> list() {
        return repository.findAll();
    }
}
