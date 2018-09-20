package com.waben.stock.datalayer.stockoption.repository.impl;

import com.waben.stock.datalayer.stockoption.entity.OfflineStockOptionTrade;
import com.waben.stock.datalayer.stockoption.repository.OfflineStockOptionTradeDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.OfflineStockOptionTradeRepository;
import com.waben.stock.interfaces.enums.OfflineStockOptionTradeState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Repository
public class OfflineStockOptionTradeDaoImpl implements OfflineStockOptionTradeDao {

    @Autowired
    private OfflineStockOptionTradeRepository repository;

    @Override
    public OfflineStockOptionTrade create(OfflineStockOptionTrade offlineStockOptionTrade) {
        return repository.save(offlineStockOptionTrade);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public OfflineStockOptionTrade update(OfflineStockOptionTrade offlineStockOptionTrade) {
        return repository.save(offlineStockOptionTrade);
    }

    @Override
    public OfflineStockOptionTrade retrieve(Long id) {
        return repository.findById(id);
    }


    @Override
    public Page<OfflineStockOptionTrade> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<OfflineStockOptionTrade> page(Specification<OfflineStockOptionTrade> specification, Pageable pageable) {
        return repository.findAll(specification,pageable);
    }

    @Override
    public List<OfflineStockOptionTrade> list() {
        return repository.findAll();
    }

    @Override
    public List<OfflineStockOptionTrade> retrieveByStateAndSellingTimeBetween(OfflineStockOptionTradeState state, Date start, Date end) {
        return repository.findOfflineStockOptionTradesByStateAndSellingTimeBetween(state,start,end);
    }
}
