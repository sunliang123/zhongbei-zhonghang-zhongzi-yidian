package com.waben.stock.interfaces.dto.manage;

import java.util.Date;

public class AppVersionUpgradeDto {

	private Long id;
	/**
	 * 版本编号
	 */
	private Integer versionCode;
	/**
	 * 版本名称
	 */
	private String versionName;
	/**
	 * 发布日期
	 */
	private Date publishDate;
	/**
	 * md5摘要
	 */
	private String md5Abstract;
	/**
	 * 下载链接
	 */
	private String downloadLink;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * App设备类型
	 * <ul>
	 * <li>0安卓</li>
	 * <li>1IOS</li>
	 * </ul>
	 */
	private Integer deviceType;
	/**
	 * 升级策略
	 * <ul>
	 * <li>0强制升级</li>
	 * <li>1建议升级</li>
	 * </ul>
	 */
	private Integer upgradStrategy;
	/**
	 * 是否为当前版本
	 */
	private Boolean isCurrentVersion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(Integer versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getMd5Abstract() {
		return md5Abstract;
	}

	public void setMd5Abstract(String md5Abstract) {
		this.md5Abstract = md5Abstract;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(Integer deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getUpgradStrategy() {
		return upgradStrategy;
	}

	public void setUpgradStrategy(Integer upgradStrategy) {
		this.upgradStrategy = upgradStrategy;
	}

	public Boolean getIsCurrentVersion() {
		return isCurrentVersion;
	}

	public void setIsCurrentVersion(Boolean isCurrentVersion) {
		this.isCurrentVersion = isCurrentVersion;
	}

}
