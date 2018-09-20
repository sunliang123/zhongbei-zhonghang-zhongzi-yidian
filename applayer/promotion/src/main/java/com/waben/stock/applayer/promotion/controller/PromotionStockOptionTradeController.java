package com.waben.stock.applayer.promotion.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.stockoption.StockOptionCycleBusiness;
import com.waben.stock.applayer.promotion.business.stockoption.StockOptionTradeBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.applayer.promotion.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionPromotionDto;
import com.waben.stock.interfaces.dto.promotion.stockoption.StockOptionStaDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionCycleDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.promotion.stockoption.StockOptionPromotionQuery;
import com.waben.stock.interfaces.util.JacksonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 推广渠道产生的期权交易 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionPromotionStockOptionTradeController")
@RequestMapping("/promotionStockOptionTrade")
@Api(description = "推广渠道产生的期权交易")
public class PromotionStockOptionTradeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public StockOptionTradeBusiness business;

	@Autowired
	private StockOptionCycleBusiness cycleBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@GetMapping("/cycles")
	@ApiOperation(value = "获取所有期权周期")
	public Response<List<StockOptionCycleDto>> fetchAll() {
		List<StockOptionCycleDto> response = cycleBusiness.findAll();
		return new Response<>(response);
	}

	@RequestMapping(value = "/sta", method = RequestMethod.GET)
	@ApiOperation(value = "统计名义本金、权利金总和")
	public Response<StockOptionStaDto> promotionSta(StockOptionPromotionQuery query) {
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		query.setTreeCode(SecurityUtil.getUserDetails().getTreeCode());
		return new Response<>(business.promotionSta(query));
	}

	@RequestMapping(value = "/pages", method = RequestMethod.GET)
	@ApiOperation(value = "查询期权订单")
	public Response<PageInfo<StockOptionPromotionDto>> promotionPagesByQuery(StockOptionPromotionQuery query) {
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		query.setTreeCode(SecurityUtil.getUserDetails().getTreeCode());
		logger.info("数据组装成功:{}", SecurityUtil.getUserDetails().getTreeCode());
		return new Response<>(business.promotionPagesByQuery(query));
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出期权订单")
	public void export(StockOptionPromotionQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		query.setTreeCode(SecurityUtil.getUserDetails().getTreeCode());
		PageInfo<StockOptionPromotionDto> result = business.promotionPagesByQuery(query);
		File file = null;
		FileInputStream is = null;
		try {
			String fileName = "stockoption_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			List<String> columnDescList = columnDescList();
			List<List<String>> dataList = dataList(result.getContent());
			PoiUtil.writeDataToExcel("期权订单数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出期权订单数据到excel异常：" + e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
				}
			}
			if (file != null) {
				file.delete();
			}
		}
	}

	private List<List<String>> dataList(List<StockOptionPromotionDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (StockOptionPromotionDto trade : content) {
			String state = "";
			if (trade.getState() != null) {
				state = trade.getState().getState();
			}
			Boolean isTest = trade.getIsTest();
			String test = "";
			if (isTest != null && isTest) {
				test = "测试交易";
			} else {
				test = "正式交易";
			}
			List<String> data = new ArrayList<>();
			data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
			data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
			data.add(test);
			data.add(trade.getTradeNo() == null ? "" : trade.getTradeNo());
			data.add(trade.getStockCode() + "/" + trade.getStockName());
			data.add(String.valueOf(trade.getNominalAmount() == null ? "" : trade.getNominalAmount()));
			data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
			data.add(String.valueOf(trade.getRightMoney() == null ? "" : trade.getRightMoney()));
			data.add(trade.getApplyTime() == null ? "" : sdf.format(trade.getApplyTime()));
			data.add(String.valueOf(trade.getBuyingLastPrice() == null ? "" : trade.getBuyingLastPrice()));
			data.add(trade.getBuyingTime() == null ? "" : sdf.format(trade.getBuyingTime()));
			data.add(String.valueOf(trade.getBuyingPrice() == null ? "" : trade.getBuyingPrice()));
			data.add(trade.getRightTime() == null ? "" : sdf.format(trade.getRightTime()));
			data.add(trade.getSellingTime() == null ? "" : sdf.format(trade.getSellingTime()));
			data.add(String.valueOf(trade.getSellingPrice() != null ? trade.getSellingPrice() : ""));
			data.add(String.valueOf(trade.getLastPrice() != null ? trade.getLastPrice() : ""));
			data.add(String.valueOf(trade.getProfit() != null ? trade.getProfit() : ""));
			data.add(state);
			data.add(trade.getOrgName() == null ? "" : trade.getOrgName());
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("客户姓名");
		result.add("交易帐号");
		result.add("交易类型");
		result.add("订单编号");
		result.add("股票代码/名称");
		result.add("名义本金");
		result.add("行权周期");
		result.add("权利金");
		result.add("申购时间");
		result.add("申购价格");
		result.add("买入时间");
		result.add("买入价格");
		result.add("申请行权时间");
		result.add("卖出时间");
		result.add("卖出价格");
		result.add("当前价格");
		result.add("浮动盈亏");
		result.add("订单状态");
		result.add("所属代理商");
		return result;
	}

}
