package com.waben.stock.datalayer.buyrecord.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.buyrecord.entity.BuyRecord;
import com.waben.stock.datalayer.buyrecord.entity.Settlement;
import com.waben.stock.datalayer.buyrecord.repository.SettlementDao;
import com.waben.stock.interfaces.pojo.query.SettlementQuery;

/**
 * 结算 Service
 * 
 * @author luomengan
 *
 */
@Service
public class SettlementService {

	@Autowired
	private SettlementDao settlementDao;

	public Page<Settlement> pagesByQuery(final SettlementQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<Settlement> pages = settlementDao.page(new Specification<Settlement>() {
			@Override
			public Predicate toPredicate(Root<Settlement> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Join<Settlement, BuyRecord> join = root.join("buyRecord", JoinType.LEFT);
				List<Predicate> predicateList = new ArrayList<>();
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					predicateList
							.add(criteriaBuilder.equal(join.get("publisherId").as(Long.class), query.getPublisherId()));
				}
				if (query.getBuyRecordId() != null && query.getBuyRecordId() > 0) {
					predicateList.add(criteriaBuilder.equal(join.get("id").as(Long.class), query.getBuyRecordId()));
				}
				if (query.isOnlyProfit()) {
					predicateList.add(criteriaBuilder.gt(root.get("publisherProfitOrLoss").as(BigDecimal.class),
							new BigDecimal(0)));
				}
				if (predicateList.size() > 0) {
					criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get("settlementTime").as(Long.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

    public Settlement findByBuyRecord(Long id) {
		Settlement result = settlementDao.retrieveByBuyRecord(id);
		if (result == null) {
			throw new ServiceException(ExceptionConstant.INVESTOR_NOT_FOUND_EXCEPTION);
		}
		return result;
	}
}
