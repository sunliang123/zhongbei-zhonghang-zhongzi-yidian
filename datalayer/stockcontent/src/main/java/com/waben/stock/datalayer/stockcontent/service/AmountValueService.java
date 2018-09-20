package com.waben.stock.datalayer.stockcontent.service;

import com.waben.stock.datalayer.stockcontent.entity.AmountValue;
import com.waben.stock.datalayer.stockcontent.entity.Loss;
import com.waben.stock.datalayer.stockcontent.repository.AmountValueDao;
import com.waben.stock.interfaces.pojo.query.AmountValueQuery;
import com.waben.stock.interfaces.pojo.query.LossQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
@Service
public class AmountValueService {

    @Autowired
    private AmountValueDao amountValueDao;

    @Transactional
    public AmountValue save(AmountValue amountValue) {
        return amountValueDao.create(amountValue);
    }

    public List<AmountValue> fetchAmountValues() {
        return amountValueDao.list();
    }

    public Page<AmountValue> pages(final AmountValueQuery query) {
        Pageable pageable = new PageRequest(query.getPage(), query.getSize());
        Page<AmountValue> result = amountValueDao.page(new Specification<AmountValue>() {
            @Override
            public Predicate toPredicate(Root<AmountValue> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList();
                if (!StringUtils.isEmpty(query.getValue())&&query.getValue()!=null) {
                    Predicate valueQuery = criteriaBuilder.equal(root.get("value").as(Integer.class), query
                            .getValue());
                    predicatesList.add(valueQuery);
                }
                criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return result;
    }

    public AmountValue fetchById(Long id) {
        return amountValueDao.retrieve(id);
    }

    public AmountValue revision(AmountValue amountValue) {
        return amountValueDao.update(amountValue);
    }
}
