package com.waben.stock.datalayer.publisher.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.waben.stock.datalayer.publisher.entity.CapitalFlow;
import com.waben.stock.datalayer.publisher.entity.Publisher;
import com.waben.stock.datalayer.publisher.repository.CapitalFlowDao;
import com.waben.stock.datalayer.publisher.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.publisher.repository.impl.MethodDesc;
import com.waben.stock.interfaces.dto.admin.publisher.CapitalFlowAdminDto;
import com.waben.stock.interfaces.enums.CapitalFlowType;
import com.waben.stock.interfaces.pojo.query.CapitalFlowQuery;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalFlowAdminQuery;
import com.waben.stock.interfaces.util.StringUtil;

/**
 * 资金流水 Service
 * 
 * @author luomengan
 *
 */
@Service
public class CapitalFlowService {

	@Autowired
	private CapitalFlowDao capitalFlowDao;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unused")
	private String intArrToString(Integer[] intArr) {
		StringBuilder result = new StringBuilder();
		for (Integer i : intArr) {
			result.append(i.toString() + ",");
		}
		return result.toString();
	}

	public Page<CapitalFlowAdminDto> adminPagesByQuery(CapitalFlowAdminQuery query) {
		String publisherIdCondition = "";
		if (query.getPublisherId() != null && query.getPublisherId() > 0) {
			publisherIdCondition = " and t1.publisher_id=" + query.getPublisherId() + " ";
		}
		String pulisherPhoneCondition = "";
		if (!StringUtil.isEmpty(query.getPulisherPhone())) {
			pulisherPhoneCondition = " and t5.phone like '%" + query.getPulisherPhone() + "%' ";
		}
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and t4.name like '%" + query.getPublisherName() + "%' ";
		}
		String stockCodeCondition = "";
		if (!StringUtil.isEmpty(query.getStockCode())) {
			stockCodeCondition = " and t2.stock_code like '%" + query.getStockCode() + "%' and t3.stock_code like '%"
					+ query.getStockCode() + "%' ";
		}
		String typeCondition = "";
		if (query.getFlowTypes() != null && !"".equals(query.getFlowTypes()) && !"0".equals(query.getFlowTypes())) {
			typeCondition = " and t1.type in(" + query.getFlowTypes() + ") ";
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.occurrence_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.occurrence_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String paymentTypeCondition = "";
		if (query.getPaymentType() != null && query.getPaymentType() > 0) {
			paymentTypeCondition = " and t6.type=" + query.getPaymentType() + " ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t5.is_test=1 ";
			} else {
				isTestCondition = " and (t5.is_test is null or t5.is_test=0) ";
			}
		}

		String sql = String.format(
				"select t1.id, t1.amount, t1.flow_no, t1.occurrence_time, t1.publisher_id, t5.phone, t1.remark, t1.type, t4.name, "
						+ "t2.stock_code as b_stock_code, t2.stock_name as b_stock_name, "
						+ "t3.stock_code as s_stock_code, t3.stock_name as s_stock_name, t6.type as payment_type, t7.bank_card, t8.bank_name, t1.available_balance, t5.is_test from capital_flow t1 "
						+ "LEFT JOIN buy_record t2 on t1.extend_type=1 and t1.extend_id=t2.id "
						+ "LEFT JOIN stock_option_trade t3 on t1.extend_type=3 and t1.extend_id=t3.id "
						+ "LEFT JOIN real_name t4 on t4.resource_type=2 and t1.publisher_id=t4.resource_id "
						+ "LEFT JOIN publisher t5 on t5.id=t1.publisher_id "
						+ "LEFT JOIN payment_order t6 on t1.extend_type=4 and t1.extend_id=t6.id "
						+ "LEFT JOIN withdrawals_order t7 on t1.extend_type=5 and t1.extend_id=t7.id "
						+ "LEFT JOIN bind_card t8 on t7.bank_card=t8.bank_card "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s group by t1.id order by t1.occurrence_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				publisherIdCondition, pulisherPhoneCondition, publisherNameCondition, stockCodeCondition, typeCondition,
				startTimeCondition, endTimeCondition, paymentTypeCondition, isTestCondition);
		String countSql = "select count(*) from (" + sql.substring(0, sql.indexOf("limit")) + ") c";
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setAmount", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setFlowNo", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setOccurrenceTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setPublisherId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setRemark", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setType", new Class<?>[] { CapitalFlowType.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setPublisherName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setbStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setbStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setsStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setsStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(13), new MethodDesc("setPaymentType", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(14), new MethodDesc("setBankCard", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(15), new MethodDesc("setBankName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(16), new MethodDesc("setAvailableBalance", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(17), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		List<CapitalFlowAdminDto> content = sqlDao.execute(CapitalFlowAdminDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	public BigDecimal adminAccumulateAmountByQuery(CapitalFlowAdminQuery query) {
		String publisherIdCondition = "";
		if (query.getPublisherId() != null && query.getPublisherId() > 0) {
			publisherIdCondition = " and t1.publisher_id=" + query.getPublisherId() + " ";
		}
		String pulisherPhoneCondition = "";
		if (!StringUtil.isEmpty(query.getPulisherPhone())) {
			pulisherPhoneCondition = " and t5.phone like '%" + query.getPulisherPhone() + "%' ";
		}
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and t4.name like '%" + query.getPublisherName() + "%' ";
		}
		String stockCodeCondition = "";
		if (!StringUtil.isEmpty(query.getStockCode())) {
			stockCodeCondition = " and t2.stock_code like '%" + query.getStockCode() + "%' and t3.stock_code like '%"
					+ query.getStockCode() + "%' ";
		}
		String typeCondition = "";
		if (query.getFlowTypes() != null && !"".equals(query.getFlowTypes()) && !"0".equals(query.getFlowTypes())) {
			typeCondition = " and t1.type in(" + query.getFlowTypes() + ") ";
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.occurrence_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.occurrence_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String paymentTypeCondition = "";
		if (query.getPaymentType() != null && query.getPaymentType() > 0) {
			paymentTypeCondition = " and t6.type=" + query.getPaymentType() + " ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t5.is_test=1 ";
			} else {
				isTestCondition = " and (t5.is_test is null or t5.is_test=0) ";
			}
		}

		String sql = String.format(
				"select sum(amount) from (select t1.amount from capital_flow t1 "
						+ "LEFT JOIN buy_record t2 on t1.extend_type=1 and t1.extend_id=t2.id "
						+ "LEFT JOIN stock_option_trade t3 on t1.extend_type=3 and t1.extend_id=t3.id "
						+ "LEFT JOIN real_name t4 on t4.resource_type=2 and t1.publisher_id=t4.resource_id "
						+ "LEFT JOIN publisher t5 on t5.id=t1.publisher_id "
						+ "LEFT JOIN payment_order t6 on t1.extend_type=4 and t1.extend_id=t6.id "
						+ "LEFT JOIN withdrawals_order t7 on t1.extend_type=5 and t1.extend_id=t7.id "
						+ "LEFT JOIN bind_card t8 on t7.bank_card=t8.bank_card "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s group by t1.id order by t1.occurrence_time desc) x",
				publisherIdCondition, pulisherPhoneCondition, publisherNameCondition, stockCodeCondition, typeCondition,
				startTimeCondition, endTimeCondition, paymentTypeCondition, isTestCondition);
		BigDecimal totalAmount = sqlDao.executeComputeSql(sql);
		return totalAmount;
	}

	public Page<CapitalFlow> pagesByQuery(final CapitalFlowQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<CapitalFlow> pages = capitalFlowDao.page(new Specification<CapitalFlow>() {
			@Override
			public Predicate toPredicate(Root<CapitalFlow> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicateList = new ArrayList<>();
				if (!StringUtils.isEmpty(query.getPublisherPhone())) {
					Predicate phoneQuery = criteriaBuilder.like(root.get("phone").as(String.class),
							"%" + query.getPublisherPhone() + "%");
					predicateList.add(phoneQuery);
				}
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					Join<CapitalFlow, Publisher> join = root.join("publisher", JoinType.LEFT);
					predicateList.add(criteriaBuilder.equal(join.get("id").as(Long.class), query.getPublisherId()));
				}
				if (query.getTypes() != null && query.getTypes().length > 0) {
					predicateList.add(root.get("type").in(query.getTypes()));
				}
				if (query.getStartTime() != null) {
					predicateList.add(criteriaBuilder.greaterThanOrEqualTo(root.get("occurrenceTime").as(Date.class),
							query.getStartTime()));
				}
				if (query.getEndTime() != null) {
					predicateList.add(
							criteriaBuilder.lessThan(root.get("occurrenceTime").as(Date.class), query.getEndTime()));
				}
				if (predicateList.size() > 0) {
					criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				criteriaQuery.orderBy(criteriaBuilder.desc(root.get("occurrenceTime").as(Long.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	public BigDecimal promotionTotalAmount(Long publisherId) {
		return capitalFlowDao.promotionTotalAmount(publisherId);
	}

	public CapitalFlow findById(Long capitalFlowId) {
		return capitalFlowDao.retrieve(capitalFlowId);
	}

}
