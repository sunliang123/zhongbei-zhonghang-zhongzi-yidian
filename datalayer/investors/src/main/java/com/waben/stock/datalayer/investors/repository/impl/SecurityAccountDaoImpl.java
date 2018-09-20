package com.waben.stock.datalayer.investors.repository.impl;

import com.waben.stock.datalayer.investors.entity.SecurityAccount;
import com.waben.stock.datalayer.investors.repository.SecurityAccountDao;
import com.waben.stock.datalayer.investors.repository.impl.jpa.SecurityAccountRepository;
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
public class SecurityAccountDaoImpl implements SecurityAccountDao {

    @Autowired
    private SecurityAccountRepository repository;

    @Override
    public SecurityAccount create(SecurityAccount securityAccount) {
        return repository.save(securityAccount);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public SecurityAccount update(SecurityAccount securityAccount) {
        return null;
    }

    @Override
    public SecurityAccount retrieve(Long id) {
        return null;
    }

    @Override
    public Page<SecurityAccount> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<SecurityAccount> page(Specification<SecurityAccount> specification, Pageable pageable) {
        return repository.findAll(specification,pageable);
    }

    @Override
    public List<SecurityAccount> list() {
        return null;
    }
}
