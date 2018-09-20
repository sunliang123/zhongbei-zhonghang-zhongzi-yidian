package com.waben.stock.datalayer.message.service;

import java.math.BigInteger;
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

import com.waben.stock.datalayer.message.entity.MessageReceipt;
import com.waben.stock.datalayer.message.entity.Messaging;
import com.waben.stock.datalayer.message.repository.DynamicQuerySqlDao;
import com.waben.stock.datalayer.message.repository.MessageReceiptDao;
import com.waben.stock.datalayer.message.repository.MessagingDao;
import com.waben.stock.datalayer.message.repository.impl.MethodDesc;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.enums.MessageType;
import com.waben.stock.interfaces.enums.OutsideMessageType;
import com.waben.stock.interfaces.enums.ResourceType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.query.MessagingQuery;

/**
 * 
 * @author Created by hujian on 2018年1月4日
 */
@Service
public class MessagingService {

	@Autowired
	private MessagingDao messagingDao;

	@Autowired
	private MessageReceiptDao messageReceiptDao;

	@Autowired
	private DynamicQuerySqlDao sqlDao;

	public Messaging save(Messaging messaging) {
		return messagingDao.create(messaging);
	}

	public Long remove(Long messagingId) {
		messagingDao.delete(messagingId);
		return messagingId;
	}

	public Messaging revision(Messaging messaging) {
		return messagingDao.update(messaging);
	}

	public Messaging findById(Long messagingId) {
		Messaging messaging = messagingDao.retrieve(messagingId);
		if (messaging == null) {
			throw new ServiceException(ExceptionConstant.DATANOTFOUND_EXCEPTION);
		}
		return messaging;
	}

	private Page<Messaging> pagesBySql(final MessagingQuery query) {
		String isOutsideConition = "";
		if (query.getIsOutside() != null) {
			isOutsideConition = " and is_outside=" + (query.getIsOutside() ? "1" : "0") + " ";
		}
		String hasReadCondition = "";
		if (query.getHasRead() != null) {
			if (query.getHasRead()) {
				hasReadCondition = " and t4.state=1 ";
			} else {
				hasReadCondition = " and (t4.state is null or t4.state=0) ";
			}
		}

		String sql = String.format(
				"select * from (select * from ((select t0.id,t0.content,t0.create_time,t0.title,t0.type,t0.is_outside,t0.link,t0.outside_msg_type,t0.resource_id,t0.resource_type,null as state,null as recipient "
						+ "from messaging t0 where t0.type=3 %s) UNION "
						+ "(select t1.id, t1.content, t1.create_time, t1.title, t1.type, t1.is_outside, t1.link, t1.outside_msg_type, t1.resource_id, t1.resource_type, t2.state, t2.recipient "
						+ "from  messaging t1 LEFT JOIN message_receipt t2 ON t1.id = t2.message "
						+ "WHERE t1.id = t2.message AND t2.recipient=" + query.getPublisherId() + " %s "
						+ "ORDER BY t1.create_time DESC)) as t3 order by t3.create_time desc, t3.recipient desc) t4 where 1=1 %s limit "
						+ query.getPage() * query.getSize() + "," + query.getSize(),
				isOutsideConition, isOutsideConition, hasReadCondition);
		String countSql = "select count(*) " + sql.substring(sql.indexOf("from"), sql.indexOf("limit"));
		Map<Integer, MethodDesc> setMethodMap = new HashMap<>();
		setMethodMap.put(new Integer(0), new MethodDesc("setId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(1), new MethodDesc("setContent", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(2), new MethodDesc("setCreateTime", new Class<?>[] { Date.class }));
		setMethodMap.put(new Integer(3), new MethodDesc("setTitle", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(4), new MethodDesc("setType", new Class<?>[] { MessageType.class }));
		setMethodMap.put(new Integer(5), new MethodDesc("setIsOutside", new Class<?>[] { Boolean.class }));
		setMethodMap.put(new Integer(6), new MethodDesc("setLink", new Class<?>[] { String.class }));
		setMethodMap.put(new Integer(7),
				new MethodDesc("setOutsideMsgType", new Class<?>[] { OutsideMessageType.class }));
		setMethodMap.put(new Integer(8), new MethodDesc("setResourceId", new Class<?>[] { Long.class }));
		setMethodMap.put(new Integer(9), new MethodDesc("setResourceType", new Class<?>[] { ResourceType.class }));
		setMethodMap.put(new Integer(10), new MethodDesc("setHasRead", new Class<?>[] { Boolean.class }));
		List<Messaging> content = sqlDao.execute(Messaging.class, sql, setMethodMap);
		if (content != null && content.size() > 0) {
			List<Long> checkList = new ArrayList<>();
			List<Integer> needDelIndex = new ArrayList<>();
			for (int i = 0; i < content.size(); i++) {
				if (checkList.contains(new Long(content.get(i).getId()))) {
					needDelIndex.add(i);
				} else {
					checkList.add(content.get(i).getId());
				}
			}
			for (int i = needDelIndex.size() - 1; i >= 0; i--) {
				content.remove(needDelIndex.get(i));
			}
		}
		BigInteger totalElements = sqlDao.executeComputeSql(countSql);
		return new PageImpl<>(content, new PageRequest(query.getPage(), query.getSize()), totalElements.longValue());
	}

	@SuppressWarnings("unused")
	private Page<Messaging> pagesByReceipt(final MessagingQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<MessageReceipt> pages = messageReceiptDao.page(new Specification<MessageReceipt>() {
			@Override
			public Predicate toPredicate(Root<MessageReceipt> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				Join<MessageReceipt, Messaging> join = root.join("message", JoinType.LEFT);
				List<Predicate> predicateList = new ArrayList<>();
				if (query.getPublisherId() != null && query.getPublisherId() > 0) {
					predicateList.add(criteriaBuilder.equal(root.get("recipient").as(String.class),
							String.valueOf(query.getPublisherId())));
				}
				if (query.getIsOutside() != null) {
					predicateList
							.add(criteriaBuilder.equal(join.get("isOutside").as(Boolean.class), query.getIsOutside()));
				}
				if (query.getHasRead() != null) {
					predicateList.add(criteriaBuilder.equal(root.get("state").as(Boolean.class), query.getHasRead()));
				}
				if (predicateList.size() > 0) {
					criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()]));
				}
				criteriaQuery.orderBy(criteriaBuilder.asc(root.get("state").as(Boolean.class)),
						criteriaBuilder.desc(join.get("createTime").as(Long.class)));
				criteriaQuery.orderBy(criteriaBuilder.desc(join.get("createTime").as(Long.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);

		List<Messaging> content = new ArrayList<>();
		if (pages.getContent() != null && pages.getContent().size() > 0) {
			for (MessageReceipt receipt : pages.getContent()) {
				Messaging messaging = receipt.getMessage();
				messaging.setHasRead(receipt.getState());
				content.add(messaging);
			}
		}
		return new PageImpl<>(content, pageable, pages.getTotalElements());
	}

	public Page<Messaging> pages(final MessagingQuery messagingQuery) {
		Pageable pageable = new PageRequest(messagingQuery.getPage(), messagingQuery.getSize());
		if (messagingQuery.getPublisherId() != null) {
			// return pagesByReceipt(messagingQuery);
			return pagesBySql(messagingQuery);
		}
		Page<Messaging> pages = messagingDao.page(new Specification<Messaging>() {
			@Override
			public Predicate toPredicate(Root<Messaging> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicatesList = new ArrayList<Predicate>();
				if (!StringUtils.isEmpty(messagingQuery.getTitle())) {
					Predicate titleQuery = criteriaBuilder.like(root.get("title").as(String.class),
							"%" + messagingQuery.getTitle() + "%");
					predicatesList.add(criteriaBuilder.and(titleQuery));
				}
				if (!StringUtils.isEmpty(messagingQuery.getMessageType())) {
					Predicate stateQuery = criteriaBuilder.equal(root.get("type").as(MessageType.class),
							MessageType.getByType(messagingQuery.getMessageType()));
					predicatesList.add(criteriaBuilder.and(stateQuery));
				}
				if (messagingQuery.getBeginTime() != null && messagingQuery.getEndTime() != null) {
					Predicate createTimeQuery = criteriaBuilder.between(root.<Date>get("createTime").as(Date.class),
							messagingQuery.getBeginTime(), messagingQuery.getEndTime());
					predicatesList.add(criteriaBuilder.and(createTimeQuery));
				}
				if (messagingQuery.getIsOutside() != null) {
					Predicate stateQuery = criteriaBuilder.equal(root.get("isOutside").as(Boolean.class),
							messagingQuery.getIsOutside());
					predicatesList.add(stateQuery);
				}
				criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("createTime").as(Date.class)));
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	/**
	 * 查询当前用户未产生回执的消息集合
	 * 
	 * @param recipient
	 * @return
	 */
	public List<Messaging> findNotProduceReceiptMessage(String recipient) {
		return messagingDao.retrieveNotProduceReceiptAllByRecipient(recipient);
	}

	public Messaging readMessage(String recipient, Long id) {
		Messaging messaging = messagingDao.retrieve(id);
		if (messaging == null) {
			return null;
		}
		MessageReceipt receipt = messageReceiptDao.findByMessageAndRecipient(messaging, recipient);
		if (receipt == null) {
			receipt = new MessageReceipt();
			receipt.setMessage(messaging);
			receipt.setRecipient(recipient);
			receipt.setState(true);
			messageReceiptDao.create(receipt);
		} else {
			receipt.setState(true);
			messageReceiptDao.update(receipt);
		}
		messaging.setHasRead(true);
		return messaging;
	}

}
