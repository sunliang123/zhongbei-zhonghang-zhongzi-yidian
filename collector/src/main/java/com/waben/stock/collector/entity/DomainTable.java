package com.waben.stock.collector.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 应用表 实体
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "collector_domain_table")
public class DomainTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 表名
	 */
	private String tableName;
	/**
	 * 实体名
	 */
	private String className;
	/**
	 * set方法
	 */
	private String setMethods;
	/**
	 * 最新ID
	 */
	private Long lastId;
	/**
	 * 升级策略
	 * <ul>
	 * <li>1全量更新</li>
	 * <li>2最新ID更新</li>
	 * </ul>
	 */
	private Integer upgradeType;
	/**
	 * 同步数据sql
	 */
	private String executeSql;
	/**
	 * 对应的应用
	 */
	private String domain;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getExecuteSql() {
		return executeSql;
	}

	public void setExecuteSql(String executeSql) {
		this.executeSql = executeSql;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Long getLastId() {
		return lastId;
	}

	public void setLastId(Long lastId) {
		this.lastId = lastId;
	}

	public Integer getUpgradeType() {
		return upgradeType;
	}

	public void setUpgradeType(Integer upgradeType) {
		this.upgradeType = upgradeType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getSetMethods() {
		return setMethods;
	}

	public void setSetMethods(String setMethods) {
		this.setMethods = setMethods;
	}

}
