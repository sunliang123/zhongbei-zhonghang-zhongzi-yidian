package com.waben.stock.datalayer.manage.entity;

import javax.persistence.*;
import java.util.Date;

/***
 * @author yuyidi 2017-11-13 22:12:35
 * @class com.waben.stock.datalayer.manage.entity.Banner
 * @description 轮播
 */
@Entity
@Table(name = "banner")
public class Banner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String link;
	@Column
	private String description;
	@Column
	private Integer sort;
	@Column(length = 1)
	private Boolean enable;
	@Column(name = "create_time")
	private Date createTime;

	@JoinColumn(name = "forward")
	@ManyToOne(targetEntity = BannerForward.class)
	private BannerForward bannerForward;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public BannerForward getBannerForward() {
		return bannerForward;
	}

	public void setBannerForward(BannerForward bannerForward) {
		this.bannerForward = bannerForward;
	}

	public String getForward() {
		if (getBannerForward() != null) {
			return getBannerForward().getForward();
		}
		return null;
	}
}
