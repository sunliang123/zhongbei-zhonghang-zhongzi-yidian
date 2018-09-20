package com.waben.stock.datalayer.stockcontent.repository.impl;

import com.waben.stock.datalayer.stockcontent.entity.AmountValue;
import com.waben.stock.datalayer.stockcontent.repository.AmountValueDao;
import com.waben.stock.datalayer.stockcontent.repository.impl.jpa.AmountValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
@Repository
public class AmountValueDaoImpl implements AmountValueDao {


    @Autowired
    private AmountValueRepository repository;

    @Override
    public AmountValue create(AmountValue amountValue) {
        return repository.save(amountValue);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public AmountValue update(AmountValue amountValue) {
        return repository.save(amountValue);
    }

    @Override
    public AmountValue retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<AmountValue> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<AmountValue> page(Specification<AmountValue> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<AmountValue> list() {
        return repository.findAll();
    }

}
