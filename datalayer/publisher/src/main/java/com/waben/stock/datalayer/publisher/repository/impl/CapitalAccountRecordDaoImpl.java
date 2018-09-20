package com.waben.stock.datalayer.publisher.repository.impl;

import com.waben.stock.datalayer.publisher.entity.CapitalAccount;
import com.waben.stock.datalayer.publisher.entity.CapitalAccountRecord;
import com.waben.stock.datalayer.publisher.repository.CapitalAccountRecordDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.CapitalAccountRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2018/5/4.
 * @desc
 */
@Repository

public class CapitalAccountRecordDaoImpl implements CapitalAccountRecordDao{

    @Autowired
    private CapitalAccountRecordRepository repository;

    @Override
    public CapitalAccountRecord create(CapitalAccountRecord capitalAccountRecord) {
        return repository.save(capitalAccountRecord);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public CapitalAccountRecord update(CapitalAccountRecord capitalAccountRecord) {
        return repository.save(capitalAccountRecord);
    }

    @Override
    public CapitalAccountRecord retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<CapitalAccountRecord> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<CapitalAccountRecord> page(Specification<CapitalAccountRecord> specification, Pageable pageable) {
        return null;
    }

    @Override
    public List<CapitalAccountRecord> list() {
        return null;
    }

    @Override
    public List<CapitalAccountRecord> findByCapitalAccount(CapitalAccount capitalAccount) {
        return repository.findByCapitalAccount(capitalAccount);
    }
}
