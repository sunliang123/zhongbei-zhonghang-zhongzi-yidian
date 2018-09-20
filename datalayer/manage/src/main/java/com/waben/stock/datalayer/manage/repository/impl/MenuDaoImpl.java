package com.waben.stock.datalayer.manage.repository.impl;

import com.waben.stock.datalayer.manage.entity.Menu;
import com.waben.stock.datalayer.manage.repository.MenuDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/17.
 * @desc
 */
@Repository
public class MenuDaoImpl implements MenuDao {

    @Autowired
    private MenuRepository repository;

    @Override
    public List<Menu> retrieveAllByVariety(Long variety) {
        return repository.findAllByVariety(variety);
    }

    @Override
    public List<Menu> retrieveByRole(Long role) {
        return repository.findAllByRolesOrderById(role);
    }

    @Override
    public Menu create(Menu menu) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Menu update(Menu menu) {
        return null;
    }

    @Override
    public Menu retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Menu> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<Menu> page(Specification<Menu> specification,Pageable pageable) {
        return repository.findAll(specification,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()));
    }

    @Override
    public List<Menu> list() {
        return null;
    }
}
