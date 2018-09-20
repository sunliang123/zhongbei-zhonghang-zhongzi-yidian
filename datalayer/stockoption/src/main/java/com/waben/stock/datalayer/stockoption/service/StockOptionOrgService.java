package com.waben.stock.datalayer.stockoption.service;

import com.waben.stock.datalayer.stockoption.entity.StockOptionOrg;
import com.waben.stock.datalayer.stockoption.repository.StockOptionOrgDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockOptionOrgService {
    @Autowired
    private StockOptionOrgDao stockOptionOrgDao;


    public List<StockOptionOrg> lists() {
        return stockOptionOrgDao.list();
    }
}
