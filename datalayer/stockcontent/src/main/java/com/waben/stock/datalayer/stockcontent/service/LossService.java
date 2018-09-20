package com.waben.stock.datalayer.stockcontent.service;

import com.waben.stock.datalayer.stockcontent.entity.Loss;
import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.repository.LossDao;
import com.waben.stock.interfaces.pojo.query.LossQuery;
import com.waben.stock.interfaces.pojo.query.StockQuery;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/23.
 * @desc
 */
@Service
public class LossService {

    @Autowired
    private LossDao lossDao;

    @Transactional
    public Loss save(Loss loss) {
        return lossDao.create(loss);
    }

    public List<Loss> fetchLosses() {
        return lossDao.list();
    }

    public Page<Loss> pages(final LossQuery lossQuery) {
        Pageable pageable = new PageRequest(lossQuery.getPage(), lossQuery.getSize());
        Page<Loss> result = lossDao.page(new Specification<Loss>() {
            @Override
            public Predicate toPredicate(Root<Loss> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList();
                if (!StringUtils.isEmpty(lossQuery.getPoint())&&lossQuery.getPoint()!=null) {
                    Predicate valueQuery = criteriaBuilder.equal(root.get("point").as(BigDecimal.class), lossQuery
                            .getPoint());
                    predicatesList.add(valueQuery);
                }
                criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return result;
    }

    public Loss fetchById(Long id) {
        return lossDao.retrieve(id);
    }

    public Loss revision(Loss loss) {
        return lossDao.update(loss);
    }

    public List<Loss> findAllLoss() {
        return lossDao.list();
    }
}
