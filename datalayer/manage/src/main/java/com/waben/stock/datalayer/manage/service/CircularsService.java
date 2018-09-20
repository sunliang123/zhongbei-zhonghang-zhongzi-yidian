package com.waben.stock.datalayer.manage.service;

import com.waben.stock.datalayer.manage.entity.Circulars;
import com.waben.stock.datalayer.manage.repository.CircularsDao;
import com.waben.stock.interfaces.pojo.query.CircularsQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 通告 Service
 *
 * @author luomengan
 */
@Service
public class CircularsService {

    @Autowired
    private CircularsDao circularsDao;

    /***
     * @author yuyidi 2017-11-21 10:48:14
     * @method findCirculars
     * @param enable
     * @return java.util.List<com.waben.stock.datalayer.manage.entity.Circulars>
     * @description 获取是否有效的公告列表  若enable为空，则获取所有的未过期且有效的公告列表
     */
    public List<Circulars> findCirculars(Boolean enable) {
        if (enable) {
        	return circularsDao.retrieveCircularsWithInExpireTime(new Date());
        }
        return circularsDao.retrieveCirculars(enable);
    }

    public Page<Circulars> pagesByQuery(final CircularsQuery query) {
        Pageable pageable = new PageRequest(query.getPage(), query.getSize());
        Page<Circulars> pages = circularsDao.page(new Specification<Circulars>() {
            @Override
            public Predicate toPredicate(Root<Circulars> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (!StringUtils.isEmpty(query.getTitle())) {
                    Predicate titleQuery = criteriaBuilder.like(root.get("title").as(String.class), "%"+query
                            .getTitle()+"%");
                    predicatesList.add(titleQuery);
                }
                criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
                criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("createTime").as(Date.class)));
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return pages;
    }

    public Circulars fetchById(Long id) {
       return circularsDao.retrieve(id);
    }

    public void delete(Long id) {
        circularsDao.delete(id);
    }

    public Circulars revision(Circulars circulars) {
        return circularsDao.create(circulars);
    }

    public Circulars save(Circulars circulars) {
        return circularsDao.create(circulars);
    }
}
