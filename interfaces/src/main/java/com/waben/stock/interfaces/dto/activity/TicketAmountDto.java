package com.waben.stock.interfaces.dto.activity;

import java.math.BigDecimal;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
@ApiModel(value="TicketAmountDto",description="奖品对象")
public class TicketAmountDto {

	@ApiModelProperty(value = "奖品id")
	private long ticketAmountId;
	
	/**
	 * 券类型 1:期权抵扣券 2：话费券
	 */
	@ApiModelProperty(value = "券类型 1:期权抵扣券 2：话费券")
	private int ticketType;
	
	/**
	 * 券名称
	 */
	@ApiModelProperty(value = "券名称")
	private String ticketName;
	
	/**
	 * 开始时间
	 */
	@ApiModelProperty(value = "开始时间")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	
	/**
	 * 结束时间
	 */
	@ApiModelProperty(value = "结束时间")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	
	/**
	 * 金额
	 */
	@ApiModelProperty(value = "面值")
	private BigDecimal amount;
	
	/**
	 * 数量
	 */
	@ApiModelProperty(value = "总数量")
	private int num;
	
	/**
	 * 使用数量
	 */
	@ApiModelProperty(value = "使用数量")
	private int usednum;
	
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String memo;

	@ApiModelProperty(value = "奖品图片url")
	private String url;

	@ApiModelProperty(value = "剩余抽奖次数")
	private int residueDegree;

	@ApiModelProperty(value = "今日已抽奖次数")
	private int luckyDrawCount;

	public long getTicketAmountId() {
		return ticketAmountId;
	}

	public void setTicketAmountId(long ticketAmountId) {
		this.ticketAmountId = ticketAmountId;
	}

	public int getTicketType() {
		return ticketType;
	}

	public void setTicketType(int ticketType) {
		this.ticketType = ticketType;
	}

	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getUsednum() {
		return usednum;
	}

	public void setUsednum(int usednum) {
		this.usednum = usednum;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getResidueDegree() {
		return residueDegree;
	}

	public void setResidueDegree(int residueDegree) {
		this.residueDegree = residueDegree;
	}

	public int getLuckyDrawCount() {
		return luckyDrawCount;
	}

	public void setLuckyDrawCount(int luckyDrawCount) {
		this.luckyDrawCount = luckyDrawCount;
	}
}
