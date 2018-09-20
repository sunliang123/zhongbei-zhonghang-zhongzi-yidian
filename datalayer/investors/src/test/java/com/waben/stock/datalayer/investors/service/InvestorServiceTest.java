package com.waben.stock.datalayer.investors.service;

import com.waben.stock.datalayer.investors.InvestorsApplicationTests;
import com.waben.stock.datalayer.investors.entity.Investor;
import com.waben.stock.datalayer.investors.entity.SecurityAccount;
import com.waben.stock.interfaces.util.JacksonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Created by yuyidi on 2017/11/30.
 * @desc
 */
public class InvestorServiceTest extends InvestorsApplicationTests {

    @Autowired
    private InvestorService investorService;

    @Test
    public void save() {
        Investor investor = new Investor();
        investor.setUserName("manage@waben.cn");
        investor.setPassword("$2a$10$9Khur/TK2mRh1AEoLSf92uDXxeFlq8CjmWqXc/DA6gFBWdYoY2gI6");
        investor.setRole(303L);
        investor.setState(true);
        SecurityAccount securityAccount = new SecurityAccount();
        securityAccount.setAccount("70001553");
        securityAccount.setPassword("111111");
        investor.setSecurityAccount(securityAccount);
        Investor result = investorService.save(investor);
        System.out.println(JacksonUtil.encode(result));
    }

}
