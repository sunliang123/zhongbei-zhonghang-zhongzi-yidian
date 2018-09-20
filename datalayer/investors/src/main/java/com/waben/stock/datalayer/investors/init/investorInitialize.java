package com.waben.stock.datalayer.investors.init;

import com.waben.stock.datalayer.investors.container.InvestorContainer;
import com.waben.stock.datalayer.investors.entity.Investor;
import com.waben.stock.datalayer.investors.service.InvestorService;
import com.waben.stock.interfaces.dto.investor.InvestorDto;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/12/14.
 * @desc
 */
@Component
//@Order(Ordered.LOWEST_PRECEDENCE+100)
public class investorInitialize implements CommandLineRunner {

    @Autowired
    private InvestorService investorService;
    @Autowired
    private InvestorContainer investorContainer;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void run(String... args) throws Exception {
        List<Investor> investors = investorService.findAll();
        logger.info("获取投资人：{}", investors.size());
        for (Investor investor : investors) {
            InvestorDto investorDto = CopyBeanUtils.copyBeanProperties(investorService.findByUserName(investor.getUserName()), new InvestorDto(), false);
            investorContainer.add(investorDto);
        }
    }
}
