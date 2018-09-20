package com.waben.stock.datalayer.organization.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.organization.entity.User;
import com.waben.stock.datalayer.organization.repository.UserDao;
import com.waben.stock.datalayer.organization.repository.impl.jpa.UserRepository;

/**
 * 机构管理用户 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserRepository repository;

	@Override
	public User retrieveByUserName(String userName) {
		return repository.findByUsername(userName);
	}

	@Override
	public User create(User t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public User update(User t) {
		return repository.save(t);
	}

	@Override
	public User retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<User> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<User> page(Specification<User> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public List<User> list() {
		return repository.findAll();
	}

}
