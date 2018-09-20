package com.waben.stock.datalayer.manage.repository.impl;

import com.waben.stock.datalayer.manage.entity.Role;
import com.waben.stock.datalayer.manage.repository.RoleDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/16.
 * @desc
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private RoleRepository repository;

    @Override
    public Role retrieveRoleAdminByOrganization(Long organization) {
        return repository.findByOrganizationAndCode(organization, "ADMIN");
    }

    @Override
    public List<Role> retrieveRolesByOrganization(Long organization) {
        return repository.findRolesByOrganization(organization);
    }

    @Override
    public Role create(Role role) {
        return repository.save(role);
    }

    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    @Override
    public Role update(Role role) {
        return repository.save(role);
    }

    @Override
    public Role retrieve(Long id) {
        return repository.findById(id);
    }

    @Override
    public Page<Role> page(int page, int limit) {
        return null;
    }

    @Override
    public Page<Role> page(Specification<Role> specification, Pageable pageable) {
        return repository.findAll(specification,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()));
    }

    @Override
    public List<Role> list() {
        return repository.findAll();
    }

	@Override
	public List<Role> findByRoleName(String name) {
		return repository.findByRoleName(name);
	}
}
