package com.waben.stock.datalayer.stockcontent.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.waben.stock.datalayer.stockcontent.entity.Stock;
import com.waben.stock.datalayer.stockcontent.entity.StockExponent;
import com.waben.stock.datalayer.stockcontent.repository.StockDao;
import com.waben.stock.datalayer.stockcontent.repository.StockExponentDao;
import com.waben.stock.datalayer.stockcontent.util.PinyinUtil;
import com.waben.stock.interfaces.pojo.query.StockQuery;

/***
 * @author yuyidi 2017-11-22 10:08:52
 * @class com.waben.stock.datalayer.stockcontent.service.StockService
 * @description
 */
@Service
public class StockService {

	@Autowired
	private StockDao stockDao;
	@Autowired
	private StockExponentDao stockExponentDao;

	@Transactional
	public Stock saveStock(Stock stock) {
		return stockDao.create(stock);
	}

	public Page<Stock> pages(final StockQuery stockQuery) {
		Pageable pageable = new PageRequest(stockQuery.getPage(), stockQuery.getSize());
		Page<Stock> result = stockDao.page(new Specification<Stock>() {
			@Override
			public Predicate toPredicate(Root<Stock> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicatesList = new ArrayList();
				if (!StringUtils.isEmpty(stockQuery.getStockName())) {
					Predicate nameQuery = criteriaBuilder.equal(root.get("name").as(String.class),
							stockQuery.getStockName());
					predicatesList.add(criteriaBuilder.and(nameQuery));
				} else if (!StringUtils.isEmpty(stockQuery.getStockCode())) {
					Predicate codeQuery = criteriaBuilder.equal(root.get("code").as(String.class),
							stockQuery.getStockCode());
					predicatesList.add(criteriaBuilder.and(codeQuery));
				} else if (!StringUtils.isEmpty(stockQuery.getStatus()) && stockQuery.getStatus() != 2) {
					Predicate statusQuery = criteriaBuilder.equal(root.get("status").as(Integer.class),
							stockQuery.getStatus());
					predicatesList.add(criteriaBuilder.and(statusQuery));
				} else if (!StringUtils.isEmpty(stockQuery.getKeyword())) {
					String keyword = stockQuery.getKeyword();
					boolean isCode = false;
					boolean isAbbr = false;
					int charAscii = keyword.charAt(0);
					if (charAscii >= 48 && charAscii <= 57) {
						isCode = true;
					} else if ((charAscii >= 65 && charAscii <= 90) || (charAscii >= 97 && charAscii <= 122)) {
						isAbbr = true;
					}
					if (isCode) {
						Predicate keywordQuery = criteriaBuilder.like(root.get("code").as(String.class),
								"%" + keyword + "%");
						predicatesList.add(criteriaBuilder.and(keywordQuery));
					} else if (isAbbr) {
						Predicate keywordQuery = criteriaBuilder.like(root.get("abbr").as(String.class),
								"%" + keyword + "%");
						predicatesList.add(criteriaBuilder.and(keywordQuery));
					} else {
						Predicate keywordQuery = criteriaBuilder.like(root.get("name").as(String.class),
								"%" + keyword + "%");
						predicatesList.add(criteriaBuilder.and(keywordQuery));
					}
				}
				criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return result;
	}

	public Stock findById(Long id) {
		return stockDao.retrieve(id);
	}

	public Stock findByCode(String code) {
		return stockDao.retrieveByCode(code);
	}

	public List<Stock> findByExponentCode(String exponentCode) {
		return stockDao.retrieveByExponentCode(exponentCode);
	}

	public Integer revision(Stock stock) {
		return stockDao.updateById(stock.getStatus(), stock.getName(), stock.getCode(), stock.getId());
	}

	public void delete(Long id) {
		stockDao.delete(id);
	}

	public Stock save(Stock stock) {
		if (stockDao.retrieveByCode(stock.getCode()) == null) {
			StockExponent stockExponent = stockExponentDao
					.retrieveWithExponeneCode(stock.getExponent().getExponentCode());
			stock.setExponent(stockExponent);
			return stockDao.create(stock);
		} else {
			return null;
		}
	}

	public void initStockAbbr() {
		List<Stock> stockList = stockDao.list();
		for (Stock stock : stockList) {
			String name = stock.getName();
			stock.setAbbr(PinyinUtil.getFirstSpell(name));
			stockDao.update(stock);
		}
	}

	public Stock downline(String code, String stockOptionBlackRemark) {
		Stock stock = stockDao.retrieveByCode(code);
		stock.setStockOptionState(2);
		stock.setStockOptionBlackRemark(stockOptionBlackRemark);
		return stockDao.update(stock);
	}

	public Stock online(String code) {
		Stock stock = stockDao.retrieveByCode(code);
		stock.setStockOptionState(1);
		return stockDao.update(stock);
	}
}
