package com.waben.stock.datalayer.publisher.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 发布人信息统计
 * @author Created by hujian on 2018年1月17日
 */
@Entity
@Table(name="publisher_information_statistics")
public class PublisherInformationStatistics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(targetEntity = Publisher.class , fetch = FetchType.EAGER , optional = false)
	@JoinColumn(name="publisher")
	private Publisher publisher;
	
	/**
	 * 发布策略次数总计
	 */
	@Column(name = "strategy_count", columnDefinition="bigint(20) default 0")
	private Long strategyCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Long getStrategyCount() {
		return strategyCount;
	}

	public void setStrategyCount(Long strategyCount) {
		this.strategyCount = strategyCount;
	}
	
	
	
}
