package com.waben.stock.datalayer.manage.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * app版本审核专用
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "app_version_audit")
public class AppVersionAudit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 创建时间
	 */
	@Column(name = "create_time")
	private Date createTime;
	/**
	 * 是否上线
	 */
	private Boolean isOnline;
	/**
	 * 是否上线，用于苹果人员审核
	 */
	private Boolean isOnlineForAudit;
	/**
	 * 是否上线，用于苹果人员审核1
	 */
	private Boolean isOnlineForAudit1;
	/**
	 * 设备类型，1IOS 2安卓
	 */
	private Integer deviceType;
	/**
	 * 空壳包序号
	 */
	private Integer shellIndex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Boolean isOnline) {
		this.isOnline = isOnline;
	}

	public Boolean getIsOnlineForAudit() {
		return isOnlineForAudit;
	}

	public void setIsOnlineForAudit(Boolean isOnlineForAudit) {
		this.isOnlineForAudit = isOnlineForAudit;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getShellIndex() {
		return shellIndex;
	}

	public void setShellIndex(Integer shellIndex) {
		this.shellIndex = shellIndex;
	}

	public Boolean getIsOnlineForAudit1() {
		return isOnlineForAudit1;
	}

	public void setIsOnlineForAudit1(Boolean isOnlineForAudit1) {
		this.isOnlineForAudit1 = isOnlineForAudit1;
	}

}
