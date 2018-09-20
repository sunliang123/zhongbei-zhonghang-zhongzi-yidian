package com.waben.stock.interfaces.vo.publisher;

import java.math.BigDecimal;
import java.util.Date;

import com.waben.stock.interfaces.enums.CapitalFlowType;

public class CapitalFlowVo {

    private Long id;
    /**
     * 冻结资金
     */
    private BigDecimal amount;
    /**
     * 流水类型
     */
    private CapitalFlowType type;

    private String capitalFlowType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 产生时间
     */
    private Date occurrenceTime;
    /**
     * 发布人ID
     */
    private Long publisherId;
    /**
     * 发布人序列号
     */
    private String publisherSerialCode;

    private String publisherPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CapitalFlowType getType() {
        return type;
    }

    public void setType(CapitalFlowType type) {
        this.type = type;
    }

    public String getCapitalFlowType() {
        return capitalFlowType;
    }

    public void setCapitalFlowType(String capitalFlowType) {
        this.capitalFlowType = capitalFlowType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPublisherSerialCode() {
        return publisherSerialCode;
    }

    public void setPublisherSerialCode(String publisherSerialCode) {
        this.publisherSerialCode = publisherSerialCode;
    }

    public String getPublisherPhone() {
        return publisherPhone;
    }

    public void setPublisherPhone(String publisherPhone) {
        this.publisherPhone = publisherPhone;
    }
}
