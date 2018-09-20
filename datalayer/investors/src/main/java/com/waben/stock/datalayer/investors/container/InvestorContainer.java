package com.waben.stock.datalayer.investors.container;

import com.waben.stock.interfaces.dto.investor.InvestorDto;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Created by yuyidi on 2017/12/3.
 * @desc 券商股票申请委托买入容器
 */
@Component
public class InvestorContainer {

    Logger logger = LoggerFactory.getLogger(getClass());
    List<InvestorDto> investorContainer = new ArrayList<>();

    public void add(InvestorDto investorDto) {
        investorContainer.add(investorDto);
    }

    public void remove(InvestorDto investorDto) {
        investorContainer.remove(investorDto);
    }

    public List<InvestorDto> getInvestorContainer() {
        return investorContainer;
    }

}
