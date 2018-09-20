package com.waben.stock.datalayer.message.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 站外推送配置
 */
@Entity
@Table(name = "outside_push_config")
public class OutsidePushConfig {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 设备类型，1IOS 2安卓
	 */
	private Integer deviceType;
	/**
	 * 空壳包序号
	 */
	private Integer shellIndex;
	/**
	 * 是否为默认的配置
	 */
	private Boolean isDefault;
	/**
	 * 应用key
	 */
	private String appKey;
	/**
	 * 应用密钥
	 */
	private String masterSecret;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

}
