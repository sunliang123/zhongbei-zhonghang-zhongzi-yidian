package com.waben.stock.datalayer.publisher.service;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.publisher.entity.FavoriteStock;
import com.waben.stock.datalayer.publisher.repository.FavoriteStockDao;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;

/**
 * 收藏股票 Service
 * 
 * @author luomengan
 *
 */
@Service
public class FavoriteStockService {

	@Autowired
	private FavoriteStockDao favoriteStockDao;

	public FavoriteStock save(FavoriteStock favoriteStock) {
		FavoriteStock checkFavorite = favoriteStockDao.retrive(favoriteStock.getPublisherId(), favoriteStock.getCode());
		if (checkFavorite != null) {
			throw new ServiceException(ExceptionConstant.STOCK_ALREADY_FAVORITE_EXCEPTION);
		}
		favoriteStock.setFavoriteTime(new Date());
		Integer maxSort = favoriteStockDao.retriveMaxSort(favoriteStock.getPublisherId());
		favoriteStock.setSort(maxSort != null ? maxSort + 1 : 1);
		favoriteStockDao.create(favoriteStock);
		return favoriteStock;
	}

	@Transactional
	public void remove(Long publisherId, String[] stockCodeArr) {
		favoriteStockDao.delete(publisherId, stockCodeArr);
	}

	public List<FavoriteStock> list(Long publisherId) {
		return favoriteStockDao.list(publisherId);
	}

	public void top(Long publisherId, String[] stockCodeArr) {
		if (stockCodeArr != null && stockCodeArr.length > 0) {
			List<FavoriteStock> others = favoriteStockDao.listByCodeNotIn(publisherId, stockCodeArr);
			int topSize = 0;
			for (String stockCode : stockCodeArr) {
				FavoriteStock favoriteStock = favoriteStockDao.retrive(publisherId, stockCode);
				if (favoriteStock != null) {
					favoriteStock.setSort(topSize + 1);
					favoriteStockDao.update(favoriteStock);
					topSize++;
				}
			}

			if (others != null && others.size() > 0) {
				for (FavoriteStock favoriteStock : others) {
					favoriteStock.setSort(topSize + 1);
					favoriteStockDao.update(favoriteStock);
					topSize++;
				}
			}
		}
	}

	public List<String> listStockCodeByPublisherId(Long publisherId) {
		return favoriteStockDao.listStockCode(publisherId);
	}

	public Page<FavoriteStock> pagesByQuery(final Long publisherId, int page, int size) {
		Pageable pageable = new PageRequest(page, size);
		Page<FavoriteStock> pages = favoriteStockDao.page(new Specification<FavoriteStock>() {
			@Override
			public Predicate toPredicate(Root<FavoriteStock> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				criteriaQuery.where(criteriaBuilder.equal(root.get("publisherId").as(Long.class), publisherId));
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get("sort").as(Integer.class)),
						criteriaBuilder.desc(root.get("favoriteTime").as(Long.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

}
