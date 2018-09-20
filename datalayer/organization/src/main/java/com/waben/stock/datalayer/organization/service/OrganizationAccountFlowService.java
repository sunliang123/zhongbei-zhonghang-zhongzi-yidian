package com.waben.stock.datalayer.organization.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.datalayer.organization.entity.OrganizationAccountFlow;
import com.waben.stock.datalayer.organization.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.organization.repository.OrganizationAccountFlowDao;
import com.waben.stock.datalayer.organization.repository.impl.MethodDesc;
import com.waben.stock.interfaces.dto.organization.OrganizationAccountFlowWithTradeInfoDto;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationAccountFlowQuery;
import com.waben.stock.interfaces.util.StringUtil;

/**
 * 机构账户流水 Service
 *
 * @author luomengan
 */
@Service
public class OrganizationAccountFlowService {

	org.slf4j.Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private OrganizationAccountFlowDao organizationAccountFlowDao;

	@Autowired
	private DynamicQuerySqlDao dynamicQuerySqlDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public OrganizationAccountFlow getOrganizationAccountFlowInfo(Long id) {
		return organizationAccountFlowDao.retrieve(id);
	}

	@Transactional
	public OrganizationAccountFlow addOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow) {
		return organizationAccountFlowDao.create(organizationAccountFlow);
	}

	@Transactional
	public OrganizationAccountFlow modifyOrganizationAccountFlow(OrganizationAccountFlow organizationAccountFlow) {
		return organizationAccountFlowDao.update(organizationAccountFlow);
	}

	public Page<OrganizationAccountFlowWithTradeInfoDto> pagesWithTradeInfoByQuery(OrganizationAccountFlowQuery query) {
		String queryTypeCondition = " and (t7.level=1 or (t4.id=t7.id or t4.parent_id=t7.id and t7.level>1)) ";
//		if (query.getQueryType() != null && query.getQueryType() == 1) {
//			queryTypeCondition = " and t1.org_id=" + query.getCurrentOrgId() + " ";
//		} else {
//			queryTypeCondition = " and (t7.level=1 or (t4.id=t7.id or t4.parent_id=t7.id and t7.level>1)) ";
//		}
		String tradeNoCondition = "";
		if (!StringUtil.isEmpty(query.getTradeNo())) {
			tradeNoCondition = " and  t1.resource_trade_no like '%" + query.getTradeNo() + "%' ";
		}
		String flowNoCondition = "";
		if (!StringUtil.isEmpty(query.getFlowNo())) {
			flowNoCondition = " and t1.flow_no like '%" + query.getFlowNo() + "%' ";
		}
		String publisherPhoneCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherPhone())) {
			publisherPhoneCondition = " and ((t2.id is not null and t2.publisher_phone like '%"
					+ query.getPublisherPhone() + "%') or (t3.id is not null and t3.publisher_phone like '%"
					+ query.getPublisherPhone() + "%')) ";
		}
		String publisherNameCondition = "";
		if (!StringUtil.isEmpty(query.getPublisherName())) {
			publisherNameCondition = " and ((t2.id is not null and t5.name like '%" + query.getPublisherName()
					+ "%') or (t3.id is not null and t6.name like '%" + query.getPublisherName() + "%')) ";
		}
		String cycleIdCondition = "";
		if (query.getCycleId() != null && query.getCycleId() > 0) {
			cycleIdCondition = " and t3.id is not null and t3.cycle_id=" + query.getCycleId() + " ";
		}
		String orgCodeOrNameConditon = "";
		if (!StringUtil.isEmpty(query.getOrgCodeOrName())) {
			orgCodeOrNameConditon = " and (t4.code like '%" + query.getOrgCodeOrName() + "%' or t4.name like '%"
					+ query.getOrgCodeOrName() + "%') ";
		}
		String stockCodeOrNameConditon = "";
		if (!StringUtil.isEmpty(query.getStockCodeOrName())) {
			stockCodeOrNameConditon = " and ((t2.id is not null and (t2.stock_code like '%" + query.getStockCodeOrName()
					+ "%' or t2.stock_name like '%" + query.getStockCodeOrName()
					+ "%')) or (t3.id is not null and (t3.stock_code like '%" + query.getStockCodeOrName()
					+ "%' or t3.stock_name like '%" + query.getStockCodeOrName() + "%'))) ";
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.occurrence_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.occurrence_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String typeCondition = "";
		if (!StringUtil.isEmpty(query.getTypes()) && !"0".equals(query.getTypes())) {
			typeCondition = " and t1.type in(" + query.getTypes() + ") ";
		}

		String sql = String.format(
				"select t1.id, t1.amount, t1.flow_no, t1.occurrence_time, t1.origin_amount, t1.remark, t1.resource_id, t1.resource_trade_no, t1.resource_type, t1.type, t1.org_id, "
						+ "t2.publisher_id as b_publisher_id, t2.publisher_phone as b_publisher_phone, t2.stock_code as b_stock_code, t2.stock_name as b_stock_name, "
						+ "t3.publisher_id as s_publisher_id, t3.publisher_phone as s_publisher_phone, t3.stock_code as s_stock_code, t3.stock_name as s_stock_name, "
						+ "t3.cycle_id, t3.cycle_name, t4.code as org_code, t4.name as org_name, "
						+ "t5.name as b_publisher_name, t6.name as s_publisher_name, t1.available_balance "
						+ "from p_organization_account_flow t1 "
						+ "LEFT JOIN buy_record t2 on t1.resource_type=1 and t1.resource_id=t2.id "
						+ "LEFT JOIN stock_option_trade t3 on t1.resource_type=3 and t1.resource_id=t3.id "
						+ "LEFT JOIN p_organization t4 on t1.org_id=t4.id "
						+ "LEFT JOIN real_name t5 on t5.resource_type=2 and t2.publisher_id=t5.resource_id "
						+ "LEFT JOIN real_name t6 on t6.resource_type=2 and t3.publisher_id=t6.resource_id "
						+ "LEFT JOIN p_organization t7 on t7.id=" + query.getCurrentOrgId() + " "
						+ "where 1=1 %s %s %s %s %s %s %s %s %s %s %s and t1.org_id is not null order by t1.occurrence_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				queryTypeCondition, tradeNoCondition, flowNoCondition, publisherPhoneCondition, publisherNameCondition,
				cycleIdCondition, orgCodeOrNameConditon, stockCodeOrNameConditon, startTimeCondition, endTimeCondition,
				typeCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setAmount", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setFlowNo", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setOccurrenceTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setOriginAmount", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setRemark", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setResourceId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setResourceTradeNo", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setResourceType", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setType", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setOrgId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(11), new MethodDesc("setbPublisherId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(12), new MethodDesc("setbPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(13), new MethodDesc("setbStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(14), new MethodDesc("setbStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(15), new MethodDesc("setsPublisherId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(16), new MethodDesc("setsPublisherPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(17), new MethodDesc("setsStockCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(18), new MethodDesc("setsStockName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(19), new MethodDesc("setCycleId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(20), new MethodDesc("setCycleName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(21), new MethodDesc("setOrgCode", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(22), new MethodDesc("setOrgName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(23), new MethodDesc("setbPublisherName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(24), new MethodDesc("setsPublisherName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(25), new MethodDesc("setAvailableBalance", new Class<?>[] { BigDecimal.class }));
		List<OrganizationAccountFlowWithTradeInfoDto> content = dynamicQuerySqlDao
				.execute(OrganizationAccountFlowWithTradeInfoDto.class, sql, setMethodMap);
		BigInteger totalElements = dynamicQuerySqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	@Transactional
	public void deleteOrganizationAccountFlow(Long id) {
		organizationAccountFlowDao.delete(id);
	}

	@Transactional
	public void deleteOrganizationAccountFlows(String ids) {
		if (ids != null) {
			String[] idArr = ids.split(",");
			for (String id : idArr) {
				if (!"".equals(id.trim())) {
					organizationAccountFlowDao.delete(Long.parseLong(id.trim()));
				}
			}
		}
	}

	public Page<OrganizationAccountFlow> organizationAccountFlows(int page, int limit) {
		return organizationAccountFlowDao.page(page, limit);
	}

	public List<OrganizationAccountFlow> list() {
		return organizationAccountFlowDao.list();
	}

}
