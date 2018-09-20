package com.waben.stock.datalayer.manage.repository.impl;

import com.waben.stock.datalayer.manage.entity.BannerForward;
import com.waben.stock.datalayer.manage.repository.BannerForwardDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.BannerForwardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BannerForwardDaoImpl implements BannerForwardDao {

    @Autowired
    private BannerForwardRepository repository;

    @Override
    public BannerForward create(BannerForward bannerForward) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public BannerForward update(BannerForward bannerForward) {
        return null;
    }

    @Override
    public BannerForward retrieve(Long id) {
        return null;
    }

    @Override
    public Page page(int page, int limit) {
        return null;
    }

    @Override
    public Page page(Specification specification, Pageable pageable) {
        return null;
    }

    @Override
    public List list() {
        return repository.findAll();
    }
}
