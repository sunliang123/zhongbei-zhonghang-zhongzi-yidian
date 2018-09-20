package com.waben.stock.interfaces.dto.manage;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通告 Dto
 * 
 * @author luomengan
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(value = "CircularsDto", description = "公告对象")
public class CircularsDto {
	@ApiModelProperty(value = "公告id")
	private Long id;
	@ApiModelProperty(value = "公告标题")
	private String title;
	@ApiModelProperty(value = "公告内容")
	private String content;
	@ApiModelProperty(value = "创建时间")
	private Date createTime = new Date();
	@ApiModelProperty(value = "到期时间")
	private Date expireTime;
	@ApiModelProperty(value = "是否可用")
	private Boolean enable;
	@ApiModelProperty(value = "公告链接")
	private String href;
	@ApiModelProperty(value = "创建人")
	private String author;
	private StaffDto staffDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public StaffDto getStaffDto() {
		return staffDto;
	}

	public void setStaffDto(StaffDto staffDto) {
		this.staffDto = staffDto;
	}

}
