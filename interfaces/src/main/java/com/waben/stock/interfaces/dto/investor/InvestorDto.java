package com.waben.stock.interfaces.dto.investor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/11/29.
 * @desc
 */
public class InvestorDto implements Serializable{

    private Long id;
    private String serialCode;
    private String userName;
    private String password;
    private String salt;
    private Long role;
    private SecurityAccountDto securityAccountDto;
    private Boolean state;
    private Date createTime;
    private String securitiesSession;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSerialCode() {
        return serialCode;
    }

    public void setSerialCode(String serialCode) {
        this.serialCode = serialCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSecuritiesSession() {
        return securitiesSession;
    }

    public void setSecuritiesSession(String securitiesSession) {
        this.securitiesSession = securitiesSession;
    }


    public void setMenus(SecurityAccountDto securityAccount) {
        this.securityAccountDto = securityAccount;
    }

    public void setSecurityAccountDto(SecurityAccountDto securityAccountDto) {
        this.securityAccountDto = securityAccountDto;
    }

    public SecurityAccountDto getSecurityAccountDto() {
        return securityAccountDto;
    }
}
