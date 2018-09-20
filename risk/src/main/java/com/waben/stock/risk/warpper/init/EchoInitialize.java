package com.waben.stock.risk.warpper.init;

import com.waben.stock.risk.business.BuyRecordBusiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Created by yuyidi on 2018/3/23.
 * @desc
 */
//@Component
public class EchoInitialize implements CommandLineRunner{

    @Autowired
    private BuyRecordBusiness buyRecordBusiness;

    Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void run(String... args) throws Exception {
        Boolean flag = buyRecordBusiness.echo();
        logger.info("接口是否可用:{}", flag);
    }
}
