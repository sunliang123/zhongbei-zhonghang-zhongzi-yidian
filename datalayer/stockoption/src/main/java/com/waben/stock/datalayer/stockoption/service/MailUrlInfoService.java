package com.waben.stock.datalayer.stockoption.service;

import com.waben.stock.datalayer.stockoption.entity.MailUrlInfo;
import com.waben.stock.datalayer.stockoption.repository.MailUrlInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2018/3/16.
 */
@Service
public class MailUrlInfoService{

    @Autowired
    private MailUrlInfoDao mailUrlInfoDao;

    public MailUrlInfo save(MailUrlInfo mailUrlInfo) {
        return mailUrlInfoDao.create(mailUrlInfo);
    }
}
