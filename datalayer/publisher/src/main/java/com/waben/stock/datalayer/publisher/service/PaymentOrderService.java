package com.waben.stock.datalayer.publisher.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.publisher.entity.PaymentOrder;
import com.waben.stock.datalayer.publisher.repository.PaymentOrderDao;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.pojo.query.PaymentOrderQuery;
import com.waben.stock.interfaces.pojo.query.SortQuery;

/**
 * 支付订单 Service
 * 
 * @author luomengan
 *
 */
@Service
public class PaymentOrderService {

	@Autowired
	private PaymentOrderDao paymentOrderDao;

	public PaymentOrder save(PaymentOrder paymentOrder) {
		paymentOrder.setCreateTime(new Date());
		return paymentOrderDao.create(paymentOrder);
	}
	
	public PaymentOrder revision(PaymentOrder paymentOrder) {
		return paymentOrderDao.update(paymentOrder);
	}

	public PaymentOrder changeState(String paymentNo, PaymentState state) {
		PaymentOrder paymentOrder = paymentOrderDao.retrieveByPaymentNo(paymentNo);
		paymentOrder.setState(state);
		paymentOrder.setUpdateTime(new Date());
		return paymentOrderDao.update(paymentOrder);
	}

	public PaymentOrder findByPaymentNo(String paymentNo) {
		return paymentOrderDao.retrieveByPaymentNo(paymentNo);
	}
	
	public PaymentOrder findById(Long paymentId) {
		return paymentOrderDao.retrieve(paymentId);
	}

	public Page<PaymentOrder> pages(final PaymentOrderQuery query){
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<PaymentOrder> pages = paymentOrderDao.page(new Specification<PaymentOrder>() {
			@Override
			public Predicate toPredicate(Root<PaymentOrder> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
				if(query.getPublisherId() != null){
					Predicate publisherIdQuery = cb.equal(root.get("publisherId").as(Long.class), query.getPublisherId());
					criteriaQuery.where(publisherIdQuery);
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}
	
	public Page<PaymentOrder> pagesByQuery(final PaymentOrderQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<PaymentOrder> pages = paymentOrderDao.page(new Specification<PaymentOrder>() {
			@Override
			public Predicate toPredicate(Root<PaymentOrder> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					predicateList
							.add(criteriaBuilder.equal(root.get("publisherId").as(Long.class), query.getPublisherId()));
				}
				if (query.getStates() != null && query.getStates().length > 0) {
					predicateList.add(root.get("state").in(query.getStates()));
				}
				if (query.getTypes() != null && query.getTypes().length > 0) {
					predicateList.add(root.get("type").in(query.getTypes()));
				}
				if (query.getKeyword() != null && !"".equals(query.getKeyword().trim())) {
					String keyword = query.getKeyword().trim();
					Predicate keywordQuery = criteriaBuilder.like(root.get("alipayAccount").as(String.class),
							"%" + keyword + "%");
					predicateList.add(keywordQuery);
				}
				if (predicateList.size() > 0) {
					criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				if (query.getSorts() != null && query.getSorts().length > 0) {
					Order[] orders = new Order[query.getSorts().length];
					for (int i = 0; i < query.getSorts().length; i++) {
						SortQuery sort = query.getSorts()[i];
						if ("asc".equals(sort.getDir())) {
							orders[i] = criteriaBuilder.asc(root.get(sort.getField()));
						} else {
							orders[i] = criteriaBuilder.desc(root.get(sort.getField()));
						}
					}
					criteriaQuery.orderBy(orders);
				} else {
					criteriaQuery.orderBy(criteriaBuilder.desc(root.get("createTime").as(Long.class)),
							criteriaBuilder.desc(root.get("updateTime").as(Long.class)));
				}
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

}
