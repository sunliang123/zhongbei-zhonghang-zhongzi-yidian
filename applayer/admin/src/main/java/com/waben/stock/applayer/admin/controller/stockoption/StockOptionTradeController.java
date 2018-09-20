package com.waben.stock.applayer.admin.controller.stockoption;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.waben.stock.applayer.admin.business.organization.PriceMarkupConfigBusiness;
import com.waben.stock.applayer.admin.business.stockcontent.StockBusiness;
import com.waben.stock.applayer.admin.business.stockoption.StockOptionCycleBusiness;
import com.waben.stock.applayer.admin.business.stockoption.StockOptionTradeBusiness;
import com.waben.stock.applayer.admin.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionBlacklistAdminDto;
import com.waben.stock.interfaces.dto.admin.stockoption.StockOptionRiskAdminDto;
import com.waben.stock.interfaces.dto.organization.PriceMarkupConfigDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionAmountLimitDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionOrgDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionQuoteDto;
import com.waben.stock.interfaces.dto.stockoption.StockOptionTradeDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.PriceMarkupForm;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionAdminQuery;
import com.waben.stock.interfaces.pojo.query.admin.stockoption.StockOptionRiskAdminQuery;
import com.waben.stock.interfaces.util.JacksonUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 期权管理 Controller
 * 
 * @author luomengan
 *
 */
@RestController("adminStockOptionTradeController")
@RequestMapping("/stockOption")
@Api(description = "期权管理")
public class StockOptionTradeController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private StockOptionTradeBusiness business;

	@Autowired
	private StockOptionCycleBusiness cycleBusiness;

	@Autowired
	private StockBusiness stockBusiness;

	@Autowired
	private PriceMarkupConfigBusiness markupBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@ApiOperation(value = "获取期权策略风控服务费加价比例")
	@GetMapping(value = "/pricemarkup")
	public Response<List<PriceMarkupConfigDto>> fetchPriceMarkup() {
		return new Response<>(markupBusiness.priceMarkupConfigList(1L, 2));
	}

	@ApiOperation(value = "设置期权策略风控服务费加价比例")
	@PostMapping(value = "/pricemarkup")
	public Response<String> settingPriceMarkup(String data) {
		Response<String> response = new Response<>();
		List<PriceMarkupForm> formList = JacksonUtil.decode(data,
				JacksonUtil.getGenericType(List.class, PriceMarkupForm.class));
		if (formList != null && formList.size() > 0) {
			for (PriceMarkupForm form : formList) {
				form.setOrgId(1L);
				form.setResourceType(2);
			}
			String result = markupBusiness.priceMarkupConfig(formList);
			response.setResult(result);
		} else {
			response.setResult("failed");
		}
		return response;
	}

	@ApiOperation(value = "设置单只股票额度")
	@PutMapping(value = "/single/amountlimit")
	public Response<StockOptionAmountLimitDto> singleAmountLimit(@RequestParam("stockCode") String stockCode,
			@RequestParam("amountLimit") BigDecimal amountLimit) {
		return new Response<>(business.modifyStockOptionLimit(stockCode, stockBusiness.fetchByCode(stockCode).getName(),
				false, amountLimit));
	}

	@ApiOperation(value = "设置全局股票额度")
	@PutMapping(value = "/global/amountlimit")
	public Response<StockOptionAmountLimitDto> globalAmountLimit(@RequestParam("amountLimit") BigDecimal amountLimit) {
		return new Response<>(business.modifyStockOptionLimit(null, null, true, amountLimit));
	}

	@ApiOperation(value = "获取全局期权交易限额")
	@GetMapping(value = "/amountlimit/global")
	public Response<StockOptionAmountLimitDto> fetchGlobalStockOptionLimit() {
		return new Response<>(business.fetchGlobalStockOptionLimit());
	}

	@ApiOperation(value = "单只股票使用全局限额设置")
	@PostMapping(value = "/amountlimit/delete")
	public Response<String> deleteStockOptionLimit(String stockCode) {
		business.deleteStockOptionLimit(stockCode);
		Response<String> result = new Response<>();
		result.setResult(stockCode);
		return result;
	}

	@ApiOperation(value = "设置平台期权报价")
	@PutMapping(value = "/modify/quote")
	public Response<StockOptionQuoteDto> modifyStockOptionQuote(@RequestParam("stockCode") String stockCode,
			@RequestParam("cycleId") Long cycleId, @RequestParam("rightMoneyRatio") BigDecimal rightMoneyRatio) {
		return new Response<>(business.modifyStockOptionQuote(stockCode, stockBusiness.fetchByCode(stockCode).getName(),
				cycleBusiness.fetchById(cycleId).getCycle(), rightMoneyRatio));
	}

	@GetMapping("/risk/normal/adminpages")
	@ApiOperation(value = "期权股票风控管理（正常股票）")
	public Response<PageInfo<StockOptionRiskAdminDto>> adminNormalRiskPagesByQuery(StockOptionRiskAdminQuery query) {
		return new Response<>(business.adminNormalRiskPagesByQuery(query));
	}

	@GetMapping("/risk/abnormal/adminpages")
	@ApiOperation(value = "期权股票风控管理（异常股票）")
	public Response<PageInfo<StockOptionRiskAdminDto>> adminAbnormalRiskPagesByQuery(StockOptionRiskAdminQuery query) {
		return new Response<>(business.adminAbnormalRiskPagesByQuery(query));
	}

	@GetMapping("/risk/black/adminpages")
	@ApiOperation(value = "期权股票风控管理（黑名单股票）")
	public Response<PageInfo<StockOptionBlacklistAdminDto>> adminBlackRiskPagesByQuery(
			StockOptionRiskAdminQuery query) {
		return new Response<>(business.adminBlackRiskPagesByQuery(query));
	}

	@GetMapping("/orglist")
	@ApiOperation(value = "期权机构列表")
	public Response<List<StockOptionOrgDto>> orgList() {
		return new Response<>(business.orgList());
	}

	@GetMapping("/pages")
	@ApiOperation(value = "查询期权交易")
	public Response<PageInfo<StockOptionAdminDto>> pages(StockOptionAdminQuery query) {
		return new Response<>(business.adminPagesByQuery(query));
	}

	@PutMapping(value = "/fail/{id}")
	@ApiOperation(value = "撤单")
	public Response<StockOptionTradeDto> fail(@PathVariable("id") Long id) {
		return new Response<>(business.fail(id));
	}

	@PutMapping(value = "/turnover/{id}/org/{orgid}")
	@ApiOperation(value = "申购成交")
	public Response<StockOptionTradeDto> turnover(@PathVariable("id") Long id, @PathVariable("orgid") Long orgid,
			@RequestParam("orgRightMoneyRatio") BigDecimal orgRightMoneyRatio,
			@RequestParam("buyingPrice") BigDecimal buyingPrice) {
		return new Response<>(business.turnover(id, orgid, orgRightMoneyRatio, buyingPrice));
	}

	@PutMapping(value = "/mark/{id}")
	@ApiOperation(value = "标记")
	public Response<StockOptionTradeDto> mark(@PathVariable("id") Long id, @RequestParam("isMark") Boolean isMark) {
		return new Response<>(business.mark(id, isMark));
	}

	@PutMapping(value = "/insettlement/{id}")
	@ApiOperation(value = "行权结算中")
	public Response<StockOptionTradeDto> insettlement(@PathVariable("id") Long id,
			@RequestParam("sellingPrice") BigDecimal sellingPrice) {
		return new Response<>(business.insettlement(id, sellingPrice));
	}

	@PutMapping(value = "/dosettlement/{id}")
	@ApiOperation(value = "结算")
	public Response<StockOptionTradeDto> dosettlement(@PathVariable("id") Long id) {
		return new Response<>(business.dosettlement(id));
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出期权交易信息")
	public void export(StockOptionAdminQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		PageInfo<StockOptionAdminDto> result = business.adminPagesByQuery(query);
		File file = null;
		FileInputStream is = null;

		List<String> columnDescList = null;
		try {
			String fileName = "optiontrade_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			if (query.getQueryType() == 0 || query.getQueryType() == 3) {// 订单列表和结算
				columnDescList = columnDescList();
			} else if (query.getQueryType() == 1) {// 询价列表
				columnDescList = inquiryDescList();
			} else if (query.getQueryType() == 2) { // 持仓列表
				columnDescList = positionDescList();
			} else if (query.getQueryType() == 4) { // 撤单列表
				columnDescList = cancelDescList();
			} else {
				columnDescList = columnDescList();
			}
			List<List<String>> dataList = dataList(result.getContent(), query.getQueryType());
			PoiUtil.writeDataToExcel("期权交易数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出期权交易数据到excel异常：" + e.getMessage());
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

	private List<List<String>> dataList(List<StockOptionAdminDto> content, Integer type) {
		List<List<String>> result = new ArrayList<>();
		for (StockOptionAdminDto trade : content) {

			Boolean isTest = trade.getIsTest();
			String test = "";
			if (isTest != null && isTest) {
				test = "是";
			} else {
				test = "否";
			}
			Boolean isMark = trade.getIsMark();
			String mark = "";
			if (isMark != null && isMark) {
				mark = "是";
			} else {
				mark = "否";
			}
			String state = "";
			if (trade.getState() != null) {
				state = trade.getState().getState();
			}
			List<String> data = new ArrayList<>();
			if (type == 0 || type == 3) {
				data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
				data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
				data.add(trade.getTradeNo() == null ? "" : trade.getTradeNo());
				data.add(trade.getStockCode() + "/" + trade.getStockName());
				data.add(String.valueOf(trade.getNominalAmount() == null ? "" : trade.getNominalAmount()));
				data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
				data.add(String.valueOf(trade.getRightMoney() == null ? "" : trade.getRightMoney()));
				data.add(String.valueOf(trade.getRightMoneyRatio() == null ? "" : trade.getRightMoneyRatio()));
				data.add(trade.getNumberOfStrand() == null ? "" : String.valueOf(trade.getNumberOfStrand()));
				data.add(String.valueOf(trade.getOrgRightMoneyRatio() == null ? "0" : trade.getOrgRightMoneyRatio()));
				data.add(trade.getApplyTime() == null ? "" : sdf.format(trade.getApplyTime()));
				data.add(trade.getBuyingTime() == null ? "" : sdf.format(trade.getBuyingTime()));
				data.add(String.valueOf(trade.getBuyingPrice() == null ? "" : trade.getBuyingPrice()));
				data.add(trade.getSellingTime() == null ? "" : sdf.format(trade.getSellingTime()));
				data.add(String.valueOf(trade.getSellingPrice() != null ? trade.getSellingPrice() : ""));
				data.add(String.valueOf(trade.getLastPrice() != null ? trade.getLastPrice() : ""));
				data.add(String.valueOf(trade.getProfit() != null ? trade.getProfit() : ""));
				data.add(test);
				data.add(trade.getRightTime() == null ? "" : sdf.format(trade.getRightTime()));
				data.add(state);
				data.add(mark);
			} else if (type == 1) {
				data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
				data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
				data.add(trade.getTradeNo() == null ? "" : trade.getTradeNo());
				data.add(trade.getStockCode() + "/" + trade.getStockName());
				data.add(String.valueOf(trade.getNominalAmount() == null ? "" : trade.getNominalAmount()));
				data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
				data.add(String.valueOf(trade.getRightMoney() == null ? "" : trade.getRightMoney()));
				data.add(String.valueOf(trade.getRightMoneyRatio() == null ? "" : trade.getRightMoneyRatio()));
				data.add(String.valueOf(trade.getOrgRightMoneyRatio() == null ? "0" : trade.getOrgRightMoneyRatio()));
				data.add(trade.getApplyTime() == null ? "" : sdf.format(trade.getApplyTime()));
				data.add(String.valueOf(trade.getLastPrice() != null ? trade.getLastPrice() : ""));
				data.add(test);
				data.add(state);
			} else if (type == 2) {
				data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
				data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
				data.add(trade.getTradeNo() == null ? "" : trade.getTradeNo());
				data.add(trade.getStockCode() + "/" + trade.getStockName());
				data.add(String.valueOf(trade.getNominalAmount() == null ? "" : trade.getNominalAmount()));
				data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
				data.add(String.valueOf(trade.getRightMoney() == null ? "" : trade.getRightMoney()));
				data.add(String.valueOf(trade.getRightMoneyRatio() == null ? "" : trade.getRightMoneyRatio()));
				data.add(String.valueOf(trade.getOrgRightMoneyRatio() == null ? "0" : trade.getOrgRightMoneyRatio()));
				data.add(trade.getApplyTime() == null ? "" : sdf.format(trade.getApplyTime()));
				data.add(trade.getBuyingTime() == null ? "" : sdf.format(trade.getBuyingTime()));
				data.add(String.valueOf(trade.getBuyingPrice() == null ? "" : trade.getBuyingPrice()));
				data.add(String.valueOf(trade.getLastPrice() != null ? trade.getLastPrice() : ""));
				data.add(String.valueOf(trade.getProfit() != null ? trade.getProfit() : ""));
				data.add(test);
				data.add(trade.getRightTime() == null ? "" : sdf.format(trade.getRightTime()));
				data.add(state);
				data.add(mark);
			} else if (type == 4) {
				data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
				data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
				data.add(trade.getTradeNo() == null ? "" : trade.getTradeNo());
				data.add(trade.getStockCode() + "/" + trade.getStockName());
				data.add(String.valueOf(trade.getNominalAmount() == null ? "" : trade.getNominalAmount()));
				data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
				data.add(String.valueOf(trade.getRightMoney() == null ? "" : trade.getRightMoney()));
				data.add(String.valueOf(trade.getRightMoneyRatio() == null ? "" : trade.getRightMoneyRatio()));
				data.add(String.valueOf(trade.getOrgRightMoneyRatio() == null ? "0" : trade.getOrgRightMoneyRatio()));
				data.add(trade.getApplyTime() == null ? "" : sdf.format(trade.getApplyTime()));
				data.add(String.valueOf(trade.getLastPrice() != null ? trade.getLastPrice() : ""));
				data.add(test);
				data.add(state);
			} else {
				data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
				data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
				data.add(trade.getTradeNo() == null ? "" : trade.getTradeNo());
				data.add(trade.getStockCode() + "/" + trade.getStockName());
				data.add(String.valueOf(trade.getNominalAmount() == null ? "" : trade.getNominalAmount()));
				data.add(trade.getCycleName() == null ? "" : trade.getCycleName());
				data.add(String.valueOf(trade.getRightMoney() == null ? "" : trade.getRightMoney()));
				data.add(String.valueOf(trade.getRightMoneyRatio() == null ? "" : trade.getRightMoneyRatio()));
				data.add(String.valueOf(trade.getOrgRightMoneyRatio() == null ? "0" : trade.getOrgRightMoneyRatio()));
				data.add(trade.getApplyTime() == null ? "" : sdf.format(trade.getApplyTime()));
				data.add(trade.getBuyingTime() == null ? "" : sdf.format(trade.getBuyingTime()));
				data.add(String.valueOf(trade.getBuyingPrice() == null ? "" : trade.getBuyingPrice()));
				data.add(trade.getSellingTime() == null ? "" : sdf.format(trade.getSellingTime()));
				data.add(String.valueOf(trade.getSellingPrice() != null ? trade.getSellingPrice() : ""));
				data.add(String.valueOf(trade.getLastPrice() != null ? trade.getLastPrice() : ""));
				data.add(String.valueOf(trade.getProfit() != null ? trade.getProfit() : ""));
				data.add(test);
				data.add(state);
				data.add(mark);
			}

			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("客户姓名");
		result.add("交易账户");
		result.add("订单编号");
		result.add("股票代码/名称");
		result.add("策略金额");
		result.add("策略周期");
		result.add("策略风控服务费");
		result.add("平台报价");
		result.add("实际买入手数");
		result.add("机构报价");
		result.add("申购时间");
		result.add("买入时间");
		result.add("买入价格");
		result.add("卖出时间");
		result.add("卖出价格");
		result.add("当前价格");
		result.add("浮动盈亏");
		result.add("是否测试");
		result.add("申请卖出时间");
		result.add("订单状态");
		result.add("是否标记");
		return result;
	}

	// 询价
	private List<String> inquiryDescList() {
		List<String> result = new ArrayList<>();
		result.add("客户姓名");
		result.add("交易账户");
		result.add("订单编号");
		result.add("股票代码/名称");
		result.add("策略金额");
		result.add("策略周期");
		result.add("策略风控服务费");
		result.add("平台报价");
		result.add("机构报价");
		result.add("申购时间");
		result.add("当前价格");
		result.add("是否测试");
		result.add("订单状态");
		return result;
	}

	// 持仓
	private List<String> positionDescList() {
		List<String> result = new ArrayList<>();
		result.add("客户姓名");
		result.add("交易账户");
		result.add("订单编号");
		result.add("股票代码/名称");
		result.add("策略金额");
		result.add("策略周期");
		result.add("策略风控服务费");
		result.add("平台报价");
		result.add("机构报价");
		result.add("申购时间");
		result.add("买入时间");
		result.add("买入价格");
		result.add("当前价格");
		result.add("浮动盈亏");
		result.add("是否测试");
		result.add("申请卖出时间");
		result.add("订单状态");
		result.add("是否标记");
		return result;
	}

	// 撤单
	private List<String> cancelDescList() {
		List<String> result = new ArrayList<>();
		result.add("客户姓名");
		result.add("交易账户");
		result.add("订单编号");
		result.add("股票代码/名称");
		result.add("策略金额");
		result.add("策略周期");
		result.add("策略风控服务费");
		result.add("平台报价");
		result.add("机构报价");
		result.add("申购时间");
		result.add("当前价格");
		result.add("是否测试");
		result.add("订单状态");
		return result;
	}


	@PutMapping("/buyingPrice")
	@ApiOperation(value = "修改买入价格")
	public Response<StockOptionTradeDto> updateBuyingPrice(StockOptionTradeDto dto) {
		logger.info("dto:{}",JacksonUtil.encode(dto));
		return new Response<>(business.updateBuyingPrice(dto.getId(),dto.getBuyingPrice()));
	}
}
