package com.waben.stock.datalayer.stockcontent.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author Created by yuyidi on 2017/11/21.
 * @desc 股票指数
 */
@Entity
@Table(name = "stock_exponent")
public class StockExponent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 股票指数名称
     */
    @Column
    private String name;
    /**
     * 行情代码
     */
    @Column
    private String quotation;
    /**
     * 交易所代码
     */
    @Column(name = "exponent_code",unique = true)
    private String exponentCode;
    /**
     * 所属种类产品(沪深)
     */
    @JoinColumn(name = "product")
    @ManyToOne(targetEntity = VarietyProduct.class)
    private VarietyProduct product;
    @OneToMany(mappedBy = "exponent")
    private Set<Stock> stocks;

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

    public String getExponentCode() {
        return exponentCode;
    }

    public void setExponentCode(String exponentCode) {
        this.exponentCode = exponentCode;
    }

    public VarietyProduct getProduct() {
        return product;
    }

    public void setProduct(VarietyProduct product) {
        this.product = product;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getOpenMarketTime() {
        return this.getProduct().getOpenMarketTime();
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
