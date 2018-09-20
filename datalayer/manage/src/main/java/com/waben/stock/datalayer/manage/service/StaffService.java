package com.waben.stock.datalayer.manage.service;

import com.waben.stock.datalayer.manage.entity.Staff;
import com.waben.stock.datalayer.manage.repository.StaffDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.DataNotFoundException;
import com.waben.stock.interfaces.pojo.query.StaffQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * @author Created by yuyidi on 2017/11/15.
 * @desc
 */
@Service
public class StaffService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private StaffDao staffDao;


    public Staff findByUserName(String username) {
        Staff result = staffDao.findByUserName(username);
        if (result == null) {
            throw new DataNotFoundException(ExceptionConstant.STAFF_NOT_FOUND_EXCEPTION);
        }
        return result;
    }

    public Page<Staff> pagesByQuery(final StaffQuery staffQuery) {
        Pageable pageable = new PageRequest(staffQuery.getPage(), staffQuery.getSize());
        Page<Staff> pages = staffDao.page(new Specification<Staff>() {
            @Override
            public Predicate toPredicate(Root<Staff> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<>();
                if (!StringUtils.isEmpty(staffQuery.getUserName())) {
                    Predicate userNameQuery = criteriaBuilder.equal(root.get("userName").as(String.class), staffQuery
                            .getUserName());
                    predicatesList.add(userNameQuery);
                }
                if(staffQuery.getBeginTime() != null && staffQuery.getEndTime() != null){
                    Predicate createTimeQuery = criteriaBuilder.between(root.<Date>get("createTime").as(Date.class),staffQuery.getBeginTime(),staffQuery.getEndTime());
                    predicatesList.add(criteriaBuilder.and(createTimeQuery));
                }
                if (staffQuery.getState() != null&&staffQuery.getState()!=2) {
                    Predicate stateQuery = criteriaBuilder.equal(root.get("state").as(Integer.class), staffQuery
                            .getState());
                    predicatesList.add(stateQuery);
                }
                criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
                criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("createTime").as(Date.class)));
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return pages;
    }

    public Staff saveStaff(Staff staff) {
        Staff result = staffDao.create(staff);
        return result;
    }


    public Staff fetchById(Long id) {
        return staffDao.retrieve(id);
    }

    public Staff revision(Staff staff) {
        return staffDao.create(staff);
    }

    public void delete(Long id) {
        staffDao.delete(id);
    }
}
