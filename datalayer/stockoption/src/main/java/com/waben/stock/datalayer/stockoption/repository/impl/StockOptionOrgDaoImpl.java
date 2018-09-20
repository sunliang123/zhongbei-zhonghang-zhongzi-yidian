package com.waben.stock.datalayer.stockoption.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.repository.StockOptionOrgDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.StockOptionOrgRepository;

@Repository
public class StockOptionOrgDaoImpl implements StockOptionOrgDao {

    @Autowired
    private StockOptionOrgRepository stockOptionOrgRepository;

    @Override
    public StockOptionOrg create(StockOptionOrg stockOptionOrg) {
        return stockOptionOrgRepository.save(stockOptionOrg);
    }

    @Override
    public void delete(Long id) {
        stockOptionOrgRepository.delete(id);
    }

    @Override
    public StockOptionOrg update(StockOptionOrg stockOptionOrg) {
        return stockOptionOrgRepository.save(stockOptionOrg);
    }

    @Override
    public StockOptionOrg retrieve(Long id) {
        return stockOptionOrgRepository.findById(id);
    }

    @Override
    public Page<StockOptionOrg> page(int page, int limit) {
        return stockOptionOrgRepository.findAll(new PageRequest(page, limit));
    }

    @Override
    public Page<StockOptionOrg> page(Specification<StockOptionOrg> specification, Pageable pageable) {
        return stockOptionOrgRepository.findAll(specification, pageable);
    }

    @Override
    public List<StockOptionOrg> list() {
        return stockOptionOrgRepository.findAll();
    }

}
