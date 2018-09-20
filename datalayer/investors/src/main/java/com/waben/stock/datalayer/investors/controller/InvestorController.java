package com.waben.stock.datalayer.investors.controller;

import com.waben.stock.datalayer.investors.business.BuyRecordBusiness;
import com.waben.stock.datalayer.investors.entity.Investor;
import com.waben.stock.datalayer.investors.entity.SecurityAccount;
import com.waben.stock.datalayer.investors.service.InvestorService;
import com.waben.stock.interfaces.dto.buyrecord.BuyRecordDto;
import com.waben.stock.interfaces.dto.investor.InvestorDto;
import com.waben.stock.interfaces.dto.investor.SecurityAccountDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.enums.WindControlType;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.InvestorQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.stock.SecuritiesStockEntrust;
import com.waben.stock.interfaces.service.inverstors.InvestorInterface;
import com.waben.stock.interfaces.util.CopyBeanUtils;
import com.waben.stock.interfaces.util.JacksonUtil;
import com.waben.stock.interfaces.util.PageToPageInfo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by yuyidi on 2017/11/5.
 * @desc
 */
@RestController
@RequestMapping("/investor")
public class InvestorController implements InvestorInterface {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private InvestorService investorService;
    @Autowired
    private BuyRecordBusiness buyRecordBusiness;

    @Override
    public Response<Integer> modify(@RequestBody InvestorDto investorDto) {
        Investor investor = CopyBeanUtils.copyBeanProperties(Investor.class, investorDto, false);
        int result = investorService.revision(investor);
        return new Response<>(result);
    }

    @Override
    public void delete(@PathVariable Long id) {
        logger.info("删除",id);
        investorService.delete(id);
    }

    /**
     * 投资人列表
     */
    public Response<PageInfo<InvestorDto>> pagesByQuery(@RequestBody InvestorQuery investorQuery) {
        Page<Investor> page = investorService.pagesByQuery(investorQuery);
        PageInfo<InvestorDto> result = PageToPageInfo.pageToPageInfo(page, InvestorDto.class);
        return new Response<>(result);
    }

    @Override
    public Response<InvestorDto> fetchById(@PathVariable Long id) {
        Investor investor = investorService.findById(id);
        InvestorDto investorDto = CopyBeanUtils.copyBeanProperties(investor, new InvestorDto(), false);
        SecurityAccount securityAccount = investor.getSecurityAccount();
        SecurityAccountDto securityAccountDto = CopyBeanUtils.copyBeanProperties(securityAccount, new SecurityAccountDto(), false);
        investorDto.setSecurityAccountDto(securityAccountDto);
        return new Response<>(investorDto);
    }

    public Response<InvestorDto> fetchByUserName(@PathVariable String username) {
        Investor investor = investorService.findByUserName(username);
        InvestorDto investorDto = CopyBeanUtils.copyBeanProperties(InvestorDto.class, investor, false);
        return new Response<>(investorDto);
    }


    /**
     * 投资人点买记录委托申请买入
     *
     * @param investor               投资人ID
     * @param securitiesStockEntrust 点买记录数据传输对象
     * @param tradeSession           投资人证券商户交易session
     * @return
     * @despriction 券商股票委托申请买入
     */
    public Response<BuyRecordDto> stockApplyBuyIn(@PathVariable Long investor, @RequestBody SecuritiesStockEntrust
            securitiesStockEntrust, String tradeSession) {
        Investor result = investorService.findById(investor);
        String entrustNo = investorService.entrustApplyBuyIn(securitiesStockEntrust, tradeSession);
        BuyRecordDto buyRecordDtoResponse = buyRecordBusiness.buyRecordApplyBuyIn(result, securitiesStockEntrust,
                entrustNo);
        return new Response<>(buyRecordDtoResponse);
    }
    /**
     * 自动买入
     */
    @Override
    public Response<BuyRecordDto> voluntarilyStockApplyBuyIn(@PathVariable Long buyrecord) {
//        BuyRecordDto buyRecordDto = buyRecordBusiness.findById(buyrecord);
//        BuyRecordDto result = investorService.buyIn(buyRecordDto);
        return new Response<>();
    }

    /**
     * 投资人点买记录委托申请卖出
     *
     * @param investor               投资人ID
     * @param securitiesStockEntrust 点买记录数据传输对象
     * @param tradeSession           投资人证券商户交易session
     * @return
     * @despriction 券商股票委托申请卖出
     */
    public Response<BuyRecordDto> stockApplySellOut(@PathVariable Long investor, @RequestBody SecuritiesStockEntrust
            securitiesStockEntrust, String tradeSession) {
        Investor result = investorService.findById(investor);
        //下单，返回委托编号
        String entrustNo = investorService.buyRecordApplySellOut(securitiesStockEntrust, tradeSession);
        //修改订单状态

        BuyRecordDto buyRecordDtoResponse = buyRecordBusiness.entrustApplySellOut(result, securitiesStockEntrust,
                entrustNo,WindControlType.PUBLISHERAPPLY.getIndex());
        return new Response<>(buyRecordDtoResponse);
    }

	@Override
	public Response<List<InvestorDto>> fetchAllInvestors() {
		return new Response<>(CopyBeanUtils.copyListBeanPropertiesToList(investorService.findAll(), InvestorDto.class));
	}
}
