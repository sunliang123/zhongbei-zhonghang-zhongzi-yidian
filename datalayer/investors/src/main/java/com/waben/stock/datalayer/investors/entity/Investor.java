package com.waben.stock.datalayer.investors.entity;

import com.waben.stock.interfaces.util.UniqueCodeGenerator;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Created by yuyidi on 2017/11/13.
 * @desc
 */
@Entity
@Table(name = "investor")
public class Investor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 序列码
     */
    @Column(name = "serial_code", nullable = false)
    private String serialCode;
    @Column
    private String userName;
    @Column
    private String password;
    @Column
    private Long role;
    @Column
    private String salt;
    @JoinColumn(name = "security_account", referencedColumnName = "id")
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private SecurityAccount securityAccount;
    @Column
    private Boolean state;
    @Column
    private Date createTime = new Date();

    @Transient
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
        setSerialCode(UniqueCodeGenerator.generateSerialCode());
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

    public SecurityAccount getSecurityAccount() {
        return securityAccount;
    }

    public void setSecurityAccount(SecurityAccount securityAccount) {
        this.securityAccount = securityAccount;
    }
}
