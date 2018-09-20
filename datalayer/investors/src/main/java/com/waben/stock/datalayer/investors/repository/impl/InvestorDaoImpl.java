package com.waben.stock.datalayer.investors.repository.impl;

import com.waben.stock.datalayer.investors.entity.Investor;
import com.waben.stock.datalayer.investors.repository.InvestorDao;
import com.waben.stock.datalayer.investors.repository.impl.jpa.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
@Repository
public class InvestorDaoImpl implements InvestorDao {

    @Autowired
    private InvestorRepository repository;

    @Override
    public Investor retieveWithUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public Integer updateById(String userName,Boolean state,Long investorId) {
        return repository.revisionById(investorId,state,userName);
    }

    @Override
    public Investor create(Investor investor) {
        return repository.save(investor);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Investor update(Investor investor) {
        return repository.save(investor);
    }

    @Override
    public Investor retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Investor> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<Investor> page(Specification<Investor> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<Investor> list() {
        return repository.findAll();
    }
}
