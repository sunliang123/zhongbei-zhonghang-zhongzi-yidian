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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.waben.stock.datalayer.publisher.entity.CapitalAccount;
import com.waben.stock.datalayer.publisher.entity.Publisher;
import com.waben.stock.datalayer.publisher.repository.CapitalAccountDao;
import com.waben.stock.datalayer.publisher.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.publisher.repository.PublisherDao;
import com.waben.stock.datalayer.publisher.repository.impl.MethodDesc;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.publisher.PublisherAdminDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.query.PublisherQuery;
import com.waben.stock.interfaces.pojo.query.admin.publisher.PublisherAdminQuery;
import com.waben.stock.interfaces.util.PasswordCrypt;
import com.waben.stock.interfaces.util.ShareCodeUtil;
import com.waben.stock.interfaces.util.StringUtil;
import com.waben.stock.interfaces.util.UniqueCodeGenerator;

/**
 * @author Created by yuyidi on 2017/11/12.
 * @desc
 */
@Service
public class PublisherService {

	@Autowired
	private PublisherDao publisherDao;

	@Autowired
	private CapitalAccountDao capitalAccountDao;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Publisher findById(Long id) {
		Publisher publisher = publisherDao.retrieve(id);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		return publisher;
	}

	@Transactional
	public Publisher register(String phone, String password, String promoter, String endType) {
		// 检查手机号
		Publisher check = publisherDao.retriveByPhone(phone);
		if (check != null) {
			throw new ServiceException(ExceptionConstant.PHONE_BEEN_REGISTERED_EXCEPTION);
		}
		// 保存发布策略人信息
		Publisher publisher = new Publisher();
		publisher.setSerialCode(UniqueCodeGenerator.generateSerialCode());
		publisher.setPhone(phone);
		publisher.setPassword(PasswordCrypt.crypt(password));
		publisher.setCreateTime(new Date());
		publisher.setPromoter(promoter);
		publisher.setEndType(endType);
		publisherDao.create(publisher);
		publisher.setPromotionCode(ShareCodeUtil.encode(publisher.getId().intValue()));
		publisherDao.update(publisher);
		// 保存资金账号信息
		CapitalAccount account = new CapitalAccount();
		account.setBalance(new BigDecimal(0.00));
		account.setAvailableBalance(new BigDecimal(0.00));
		account.setFrozenCapital(new BigDecimal(0.00));
		account.setPublisherSerialCode(publisher.getSerialCode());
		account.setPublisherId(publisher.getId());
		account.setPublisher(publisher);
		account.setUpdateTime(new Date());
		capitalAccountDao.create(account);
		// 返回
		return publisher;
	}

	public Publisher findBySerialCode(String serialCode) {
		Publisher publisher = publisherDao.retriveBySerialCode(serialCode);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		return publisher;
	}

	public Publisher modifyPassword(String phone, String password) {
		// 检查手机号
		Publisher publisher = publisherDao.retriveByPhone(phone);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.PHONE_ISNOT_REGISTERED_EXCEPTION);
		}
		// 更新密码
		publisher.setPassword(PasswordCrypt.crypt(password));
		publisherDao.update(publisher);
		return publisher;
	}

	public Publisher findByPhone(String phone) {
		Publisher publisher = publisherDao.retriveByPhone(phone);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		return publisher;
	}

	public Integer promotionCount(Long id) {
		Publisher publisher = publisherDao.retrieve(id);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}

		return publisherDao.promotionCount(publisher.getPromotionCode());
	}

	public Page<Publisher> pagePromotionUser(Long id, int page, int size) {
		Publisher publisher = publisherDao.retrieve(id);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		return publisherDao.pageByPromoter(publisher.getPromotionCode(), page, size);
	}

	// 分页查询
	public Page<Publisher> pages(final PublisherQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<Publisher> pages = publisherDao.page(new Specification<Publisher>() {
			@Override
			public Predicate toPredicate(Root<Publisher> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				if (StringUtils.isEmpty(query.getPhone()) && StringUtils.isEmpty(query.getPromoter())
						&& StringUtils.isEmpty(query.getBeginTime())) {
					criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("createTime").as(Date.class)));
					return criteriaQuery.getRestriction();
				}
				List<Predicate> predicatesList = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(query.getPhone())) {
					Predicate phoneQuery = criteriaBuilder.like(root.get("phone").as(String.class),
							"%" + query.getPhone() + "%");
					predicatesList.add(phoneQuery);
				}
				if (!StringUtils.isEmpty(query.getPromoter())) {
					Predicate promoterQuery = criteriaBuilder.equal(root.get("promoter").as(String.class),
							query.getPromoter());
					predicatesList.add(promoterQuery);
				}
				if (query.getBeginTime() != null && query.getEndTime() != null) {
					Predicate createTimeQuery = criteriaBuilder.between(root.<Date>get("createTime").as(Date.class),
							query.getBeginTime(), query.getEndTime());
					predicatesList.add(criteriaBuilder.and(createTimeQuery));
				}
				criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("createTime").as(Date.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	public Publisher modiyHeadportrait(Long id, String headPortrait) {
		Publisher publisher = publisherDao.retrieve(id);
		if (publisher == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		if (headPortrait != null && !"".equals(headPortrait)) {
			publisher.setHeadPortrait(headPortrait);
			publisherDao.update(publisher);
		}
		return publisher;
	}

	public Publisher revision(Publisher publisher) {
		return publisherDao.update(publisher);
	}

	public List<Publisher> findPublishers() {
		return publisherDao.list();
	}

	public List<Publisher> findByIsTest(Boolean test) {
		return publisherDao.retrieveByIsTest(test);
	}

	public Page<PublisherAdminDto> adminPagesByQuery(PublisherAdminQuery query) {
		String nameCondition = "";
		if (!StringUtil.isEmpty(query.getName())) {
			nameCondition = " and t2.name like '%" + query.getName() + "%' ";
		}
		String phoneCondition = "";
		if (!StringUtil.isEmpty(query.getPhone())) {
			phoneCondition = " and t1.phone like '%" + query.getPhone() + "%' ";
		}
		String startTimeCondition = "";
		if (query.getStartTime() != null) {
			startTimeCondition = " and t1.create_time>='" + sdf.format(query.getStartTime()) + "' ";
		}
		String endTimeCondition = "";
		if (query.getEndTime() != null) {
			endTimeCondition = " and t1.create_time<'" + sdf.format(query.getEndTime()) + "' ";
		}
		String stateCondition = "";
		if (query.getState() != null && query.getState() == 1) {
			stateCondition = " and (t1.state is null or t1.state=1) ";
		}
		if (query.getState() != null && query.getState() == 2) {
			stateCondition = " and t1.state=2 ";
		}
		String isTestCondition = "";
		if (query.getIsTest() != null) {
			if (query.getIsTest()) {
				isTestCondition = " and t1.is_test=1 ";
			} else {
				isTestCondition = " and (t1.is_test is null or t1.is_test=0) ";
			}
		}

		String sql = String.format(
				"select t1.id, t2.name, t1.phone, t3.available_balance, t1.create_time, t1.end_type, t1.state, t1.is_test from publisher t1 "
						+ "LEFT JOIN real_name t2 on t2.resource_type=2 and t1.id=t2.resource_id "
						+ "LEFT JOIN capital_account t3 on t1.id=t3.publisher_id "
						+ "where 1=1 %s %s %s %s %s %s order by t1.create_time desc limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				nameCondition, phoneCondition, startTimeCondition, endTimeCondition, stateCondition, isTestCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setName", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setPhone", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setAvailableBalance", new Class<?>[] { BigDecimal.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setCreateTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setEndType", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setState", new Class<?>[] { Integer.class }));
		setMethodMap.put(new Integer(7), new MethodDesc("setIsTest", new Class<?>[] { Boolean.class }));
		List<PublisherAdminDto> content = sqlDao.execute(PublisherAdminDto.class, sql, setMethodMap);
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()),
				totalElements != null ? totalElements.longValue() : 0);
	}

	@Transactional
	public Publisher defriend(Long id) {
		Publisher publisher = publisherDao.retrieve(id);
		publisher.setState(2);
		return publisherDao.update(publisher);
	}

	public Publisher recover(Long id) {
		Publisher publisher = publisherDao.retrieve(id);
		publisher.setState(1);
		return publisherDao.update(publisher);
	}

	public void delete(Long id){
		publisherDao.delete(id);
	}

	/**模拟账户*/

	public static int getNum(int start,int end) {
		return (int)(Math.random()*(end-start+1)+start);
	}

	private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
	private static String getTel() {
		int index=getNum(0,telFirst.length-1);
		String first=telFirst[index];
		String second=String.valueOf(getNum(1,888)+10000).substring(1);
		String third=String.valueOf(getNum(1,9100)+10000).substring(1);
		return first+second+third;
	}

	public PublisherAdminDto registerDum(PublisherAdminDto dto){

		// 保存发布策略人信息
		Publisher publisher = new Publisher();
		publisher.setSerialCode(UniqueCodeGenerator.generateSerialCode());
		publisher.setPhone(getTel());
		publisher.setPassword(PasswordCrypt.crypt("123456"));
		publisher.setCreateTime(new Date());
		publisher.setPromoter("");
		publisher.setEndType("T");
		publisher.setIsTest(true);
		Publisher pu = publisherDao.create(publisher);
		CapitalAccount account = new CapitalAccount();
		account.setBalance(dto.getAvailableBalance());
		account.setAvailableBalance(dto.getAvailableBalance());
		account.setFrozenCapital(new BigDecimal(0.00));
		account.setPublisherSerialCode(publisher.getSerialCode());
		account.setPublisherId(publisher.getId());
		account.setPublisher(publisher);
		account.setUpdateTime(new Date());
		CapitalAccount result = capitalAccountDao.create(account);
		PublisherAdminDto response = new PublisherAdminDto();
		response.setAvailableBalance(result.getAvailableBalance());
		response.setPhone(pu.getPhone());
		response.setId(pu.getId());
		response.setCreateTime(pu.getCreateTime());
		return response;
	}
}
