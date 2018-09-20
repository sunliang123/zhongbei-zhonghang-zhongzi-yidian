package com.waben.stock.datalayer.manage.repository.impl;

import com.waben.stock.datalayer.manage.entity.Permission;
import com.waben.stock.datalayer.manage.repository.PermissionDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/11.
 * @desc
 */
@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Autowired
    private PermissionRepository repository;

    @Override
    public List<Permission> retrieveAllByRole(Long role) {
        return repository.findAllByRolesOrderById(role);
    }

    @Override
    public List<Permission> retrieveAllByVariety(Long variety) {
        return repository.findAllByVariety(variety);
    }

    @Override
    public Permission create(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Permission update(Permission permission) {
        return repository.save(permission);
    }

    @Override
    public Permission retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Permission> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<Permission> page(Specification<Permission> specification, Pageable pageable) {
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<Permission> list() {
        return repository.findAll();
    }
}
