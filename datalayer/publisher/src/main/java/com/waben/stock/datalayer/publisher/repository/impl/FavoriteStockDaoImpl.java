package com.waben.stock.datalayer.publisher.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import com.waben.stock.datalayer.publisher.entity.FavoriteStock;
import com.waben.stock.datalayer.publisher.repository.FavoriteStockDao;
import com.waben.stock.datalayer.publisher.repository.impl.jpa.FavoriteStockRepository;

/**
 * 收藏股票 Dao实现
 * 
 * @author luomengan
 *
 */
@Repository
public class FavoriteStockDaoImpl implements FavoriteStockDao {

	@Autowired
	private FavoriteStockRepository repository;

	@Override
	public FavoriteStock create(FavoriteStock t) {
		return repository.save(t);
	}

	@Override
	public void delete(Long id) {
		repository.delete(id);
	}

	@Override
	public FavoriteStock update(FavoriteStock t) {
		return repository.save(t);
	}

	@Override
	public FavoriteStock retrieve(Long id) {
		return repository.findById(id);
	}

	@Override
	public Page<FavoriteStock> page(int page, int limit) {
		return repository.findAll(new PageRequest(page, limit));
	}

	@Override
	public List<FavoriteStock> list() {
		return repository.findAll();
	}

	@Override
	public Page<FavoriteStock> page(Specification<FavoriteStock> specification, Pageable pageable) {
		return repository.findAll(specification, pageable);
	}

	@Override
	public FavoriteStock retrive(Long publisherId, String stockCode) {
		return repository.findByPublisherIdAndCode(publisherId, stockCode);
	}

	@Override
	public Integer retriveMaxSort(Long publisherId) {
		return repository.retriveMaxSort(publisherId);
	}

	@Override
	public List<FavoriteStock> list(Long publisherId) {
		Sort sort = new Sort(new Sort.Order(Direction.ASC, "sort"), new Sort.Order(Direction.DESC, "favoriteTime"));
		return repository.findByPublisherId(publisherId, sort);
	}

	@Override
	public List<FavoriteStock> listByCodeNotIn(Long publisherId, String[] stockCodes) {
		return repository.findByPublisherIdAndCodeNotIn(publisherId, stockCodes);
	}

	@Override
	public void delete(Long publisherId, String[] stockCodes) {
		if (stockCodes != null && stockCodes.length > 0) {
			for (String stockCode : stockCodes) {
				repository.deleteByPublisherIdAndCode(publisherId, stockCode);
			}
		}
	}

	@Override
	public List<String> listStockCode(Long publisherId) {
		return repository.findCodeByPublisherId(publisherId);
	}

}
