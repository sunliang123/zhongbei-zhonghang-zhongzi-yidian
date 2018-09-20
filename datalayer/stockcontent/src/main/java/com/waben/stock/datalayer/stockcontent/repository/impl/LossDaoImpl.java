package com.waben.stock.datalayer.stockcontent.repository.impl;

import com.waben.stock.datalayer.stockcontent.entity.Loss;
import com.waben.stock.datalayer.stockcontent.repository.LossDao;
import com.waben.stock.datalayer.stockcontent.repository.impl.jpa.LossRepository;
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
public class LossDaoImpl implements LossDao {

    @Autowired
    private LossRepository repository;

    @Override
    public Loss create(Loss loss) {
        return repository.save(loss);
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Loss update(Loss loss) {
        return repository.save(loss);
    }

    @Override
    public Loss retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Loss> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<Loss> page(Specification<Loss> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<Loss> list() {
        return repository.findAll();
    }

}
