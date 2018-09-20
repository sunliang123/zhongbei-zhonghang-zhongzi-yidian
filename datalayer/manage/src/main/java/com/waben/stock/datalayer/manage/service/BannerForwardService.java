package com.waben.stock.datalayer.manage.service;

import com.waben.stock.datalayer.manage.entity.BannerForward;
import com.waben.stock.datalayer.manage.repository.BannerForwardDao;
import com.waben.stock.datalayer.manage.repository.impl.BannerForwardDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerForwardService {


    @Autowired
    private BannerForwardDao dao;

    public List<BannerForward> findAll() {
        return dao.list();
    }

}
