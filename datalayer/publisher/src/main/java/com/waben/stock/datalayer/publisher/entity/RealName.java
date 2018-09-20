package com.waben.stock.datalayer.publisher.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.waben.stock.datalayer.publisher.entity.enumconverter.ResourceTypeConverter;
import com.waben.stock.interfaces.enums.ResourceType;

/**
 * 实名认证
 * 
 * @author luomengan
 *
 */
@Entity
@Table(name = "real_name")
public class RealName {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 身份证号
	 */
	private String idCard;
	/**
	 * 设置对象的资源类型
	 */
	@Convert(converter = ResourceTypeConverter.class)
	@Column(name = "resource_type")
	private ResourceType resourceType;
	/**
	 * 设置对象的ID
	 */
	@Column(name = "resource_id")
	private Long resourceId;
	/**
	 * 实名认证时间
	 */
	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Long getResourceId() {
		return resourceId;
	}

	public void setResourceId(Long resourceId) {
		this.resourceId = resourceId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
