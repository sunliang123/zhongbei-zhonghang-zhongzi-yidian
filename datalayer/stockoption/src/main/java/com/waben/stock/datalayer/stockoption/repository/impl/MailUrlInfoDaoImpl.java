package com.waben.stock.datalayer.stockoption.repository.impl;

import com.waben.stock.datalayer.stockoption.entity.MailUrlInfo;
import com.waben.stock.datalayer.stockoption.repository.MailUrlInfoDao;
import com.waben.stock.datalayer.stockoption.repository.impl.jpa.MailUrlInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by chenk on 2018/3/16.
 */
@Repository
public class MailUrlInfoDaoImpl implements MailUrlInfoDao {

    @Autowired
    private MailUrlInfoRepository mailUrlInfoRepository;

    @Override
    public MailUrlInfo create(MailUrlInfo mailUrlInfo) {
        return mailUrlInfoRepository.save(mailUrlInfo);
    }

    @Override
    public void delete(Long id) {
        mailUrlInfoRepository.delete(id);
    }

    @Override
    public MailUrlInfo update(MailUrlInfo mailUrlInfo) {
        return mailUrlInfoRepository.save(mailUrlInfo);
    }

    @Override
    public MailUrlInfo retrieve(Long id) {
        return mailUrlInfoRepository.findById(id);
    }

    @Override
    public Page<MailUrlInfo> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<MailUrlInfo> page(Specification<MailUrlInfo> specification, Pageable pageable) {
        return mailUrlInfoRepository.findAll(specification,pageable);
    }

    @Override
    public List<MailUrlInfo> list() {
        return mailUrlInfoRepository.findAll();
    }
}
