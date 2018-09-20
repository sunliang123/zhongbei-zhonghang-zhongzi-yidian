package com.waben.stock.datalayer.manage.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.manage.entity.Banner;
import com.waben.stock.datalayer.manage.repository.BannerDao;
import com.waben.stock.datalayer.manage.repository.impl.jpa.BannerRepository;

/**
 * 轮播 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class BannerDaoImpl implements BannerDao {

	@Autowired
	private BannerRepository repository;

	@Override
	public List<Banner> retrieveBanners(Boolean enable) {
		return repository.findByEnableOrderBySortAsc(enable);
	}

	@Override
	public List<Banner> retrieveBannersOrderByCreateTime() {
		return repository.findByOrderByCreateTimeDesc();
	}

	@Override
	public Banner create(Banner t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public Banner update(Banner t) {
		return repository.save(t);
	}

	@Override
	public Banner retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<Banner> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public Page<Banner> page(Specification<Banner> specification, Pageable pageable) {
		return repository.findAll(specification,new PageRequest(pageable.getPageNumber(),pageable.getPageSize()));
	}

	@Override
	public List<Banner> list() {
		return repository.findAll();
	}

}
