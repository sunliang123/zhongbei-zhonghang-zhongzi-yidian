package com.waben.stock.datalayer.manage.service;

import com.waben.stock.datalayer.manage.entity.Permission;
import com.waben.stock.datalayer.manage.entity.Role;
import com.waben.stock.datalayer.manage.repository.PermissionDao;
import com.waben.stock.datalayer.manage.repository.RoleDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.query.PermissionQuery;
import com.waben.stock.interfaces.pojo.query.RoleQuery;
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
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
@Service
public class PermissionService {


    @Autowired
    private PermissionDao permissionDao;

    public Permission findById(Long id) {
        Permission permission = permissionDao.retrieve(id);
        if (permission == null) {
            throw new ServiceException(ExceptionConstant.ROLE_NOT_FOUND_EXCEPTION);
        }
        return permission;
    }

    public Page<Permission> pagesByQuery(final PermissionQuery query) {
        Pageable pageable = new PageRequest(query.getPage(), query.getSize());
        Page<Permission> pages = permissionDao.page(new Specification<Permission>() {
            @Override
            public Predicate toPredicate(Root<Permission> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder
                    criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList();
                if (!StringUtils.isEmpty(query.getName())) {
                    Predicate nameQuery = criteriaBuilder.equal(root.get("name").as(String.class), query
                            .getName());
                    predicatesList.add(nameQuery);
                }
                if (!StringUtils.isEmpty(query.getCode())) {
                    Predicate codeQuery = criteriaBuilder.equal(root.get("expression").as(String.class), query
                            .getCode());
                    predicatesList.add(codeQuery);
                }
                criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
                return criteriaQuery.getRestriction();
            }
        }, pageable);
        return pages;
    }

    public void delete(Long id) {
        permissionDao.delete(id);
    }

    public Permission save(Permission permission) {
        return permissionDao.create(permission);
    }

    public Permission revision(Permission permission) {
        return permissionDao.update(permission);
    }

    public List<Permission> findPermissions() {
        return permissionDao.list();
    }

    public List<Permission> findPermissionsByVariety(Long variety) {
        return permissionDao.retrieveAllByVariety(variety);
    }

    public List<Permission> findPermissionsByRole(Long role) {
        return permissionDao.retrieveAllByRole(role);
    }
}
