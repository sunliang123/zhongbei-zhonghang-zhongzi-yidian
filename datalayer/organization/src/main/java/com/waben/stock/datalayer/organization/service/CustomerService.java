package com.waben.stock.datalayer.organization.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.organization.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.organization.repository.impl.MethodDesc;
import com.waben.stock.interfaces.dto.organization.CustomerDto;
import com.waben.stock.interfaces.pojo.query.organization.CustomerQuery;
import com.waben.stock.interfaces.util.StringUtil;

/**
 * 客户 Service
 * 
 * @author luomengan
 *
 */
@Service
public class CustomerService {

	@Autowired
	private DynamicQuerySqlDao dynamicQuerySqlDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Page<CustomerDto> pagesByQuery(CustomerQuery query) {
		String currentOrgCondition = " and (t6.level=1 or (t4.id=t6.id or t4.parent_id=t6.id and t6.level>1)) ";
		String publisherIdCondition = "";
		if (query.getPublisherId() != null && !"".equals(query.getPublisherId().trim())) {
			publisherIdCondition = " and t1.id='" + query.getPublisherId().trim() + "' ";
		}
		String publisherPhoneCondition = "";
		if (query.getPublisherPhone() != null && !"".equals(query.getPublisherPhone())) {
			publisherPhoneCondition = " and t1.phone like '%" + query.getPublisherPhone().trim() + "%' ";
		}
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and t5.name like '%" + query.getPublisherName().trim() + "%' ";
		}
		String orgCodeOrNameConditon = "";
		if (!StringUtil.isEmpty(query.getOrgCodeOrName())) {
			orgCodeOrNameConditon = " and (t4.code like '%" + query.getOrgCodeOrName() + "%' or t4.name like '%"
					+ query.getOrgCodeOrName() + "%') ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t1.is_test=1 ";
			} else {
				isTestCondition = " and (t1.is_test is null or t1.is_test=0) ";
			}
		}
		String stateCondition = "";
		if (query.getState() != null && query.getState() == 1) {
			stateCondition = " and (t1.state is null or t1.state=1) ";
		}
		if (query.getState() != null && query.getState() == 2) {
			stateCondition = " and t1.state=2 ";
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.create_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.create_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String sql = String.format(
				"select t1.id, t1.phone, t4.id as org_id, t4.code as org_code, t4.name as org_name, t1.create_time, t3.balance, t3.available_balance, t3.frozen_capital, t1.end_type, t1.is_test, t1.state, t5.name as publisher_name from publisher t1 "
						+ "LEFT JOIN p_organization_publisher t2 on t1.id=t2.publisher_id "
						+ "LEFT JOIN real_name t5 on t1.id=t5.resource_id and t5.resource_type=2 "
						+ "LEFT JOIN p_organization t4 on t2.org_code=t4.code "
						+ "LEFT JOIN p_organization t6 on t6.id=" + query.getCurrentOrgId() + " "
						+ "LEFT JOIN capital_account t3 on t1.id=t3.publisher_id "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s order by t1.create_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				currentOrgCondition, startTimeCondition, endTimeCondition, publisherIdCondition,
				publisherPhoneCondition, publisherNameCondition, orgCodeOrNameConditon, isTestCondition,
				stateCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setPublisherId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setOrgId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setOrgCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setOrgName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setCreateTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setBalance", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setAvailableBalance", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setFrozenCapital", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setEndType", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setState", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setPublisherName", new Class<?>[] { String.class }));
		List<CustomerDto> content = dynamicQuerySqlDao.execute(CustomerDto.class, sql, setMethodMap);
		BigInteger totalElements = dynamicQuerySqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()), totalElements.longValue());
	}

}
