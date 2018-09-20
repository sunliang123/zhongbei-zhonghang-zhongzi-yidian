package com.waben.stock.interfaces.dto.stockoption;

public class StockOptionOrgDto implements Comparable<StockOptionTradeDto> {
    private Long id;
    /**
     * 机构名称
     */
    private String name;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public int compareTo(StockOptionTradeDto o) {
        return 0;
    }
}
