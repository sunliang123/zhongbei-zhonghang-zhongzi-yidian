package com.waben.stock.applayer.promotion.controller;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.WriterException;
import com.waben.stock.applayer.promotion.business.BindCardBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.applayer.promotion.util.PoiUtil;
import com.waben.stock.applayer.promotion.util.QRCodeUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.OrganizationDetailDto;
import com.waben.stock.interfaces.dto.organization.OrganizationDto;
import com.waben.stock.interfaces.dto.organization.OrganizationStaDto;
import com.waben.stock.interfaces.dto.organization.TradingFowDto;
import com.waben.stock.interfaces.dto.organization.TreeNode;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.CapitalFlowType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.form.organization.OrganizationForm;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationQuery;
import com.waben.stock.interfaces.pojo.query.organization.OrganizationStaQuery;
import com.waben.stock.interfaces.pojo.query.organization.TradingFowQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 机构 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionOrganizationController")
@RequestMapping("/organization")
@Api(description = "代理商接口列表")
public class OrganizationController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public OrganizationBusiness business;

	@Autowired
	public BindCardBusiness bindCardBusiness;

	@Value("${register.url:}")
	private String registerUrl;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Response<OrganizationDto> addition(OrganizationForm orgForm) {
		return new Response<>(business.addition(orgForm));
	}

	@RequestMapping(value = "/adminPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response<PageInfo<OrganizationDto>> adminPage(@RequestBody OrganizationQuery query) {
		return new Response<>(business.adminPage(query));
	}

	@RequestMapping(value = "/pages", method = RequestMethod.POST)
	public Response<PageInfo<OrganizationDto>> pages(@RequestBody OrganizationQuery query) {
		// UserDto userDto = (UserDto) SecurityAccount.current().getSecurity();
		// if(!"admin".equals(userDto.getUsername())){
		// query.setParentId(userDto.getOrg().getId());
		// }
		return new Response<>(business.pages(query));
	}

	@RequestMapping(value = "/adminTree", method = RequestMethod.GET)
	public Response<List<TreeNode>> adminTree() {
		return new Response<>(business.adminTree(SecurityUtil.getUserDetails().getOrgId()));
	}

	@RequestMapping(value = "/listByParentId", method = RequestMethod.GET)
	public Response<List<OrganizationDto>> listByParentId(Long parentId) {
		return new Response<>(business.listByParentId(parentId));
	}

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	// @ApiOperation(value = "根据代理商ID获取代理商信息")
	public Response<OrganizationDetailDto> detail(Long orgId) {
		return new Response<>(business.detail(orgId));
	}

	/**
	 * 
	 * @param id
	 *            代理ID
	 * @param name
	 *            代理名称
	 * @param billCharge
	 *            提现手续费
	 * @param settlementType
	 *            结算方式
	 * @return
	 */
	@RequestMapping(value = "/modifyName", method = RequestMethod.POST)
	@ApiOperation(value = "编辑代理商信息")
	public Response<OrganizationDto> modifyName(Long id, String name, BigDecimal billCharge, Integer settlementType) {
		return new Response<>(business.modifyName(id, name, billCharge, settlementType));
	}

	@RequestMapping(value = "/bindcard", method = RequestMethod.GET)
	@ApiOperation(value = "获取绑卡信息")
	public Response<BindCardDto> fetchBindCard() {
		return new Response<>(bindCardBusiness.getOrgBindCard(SecurityUtil.getUserDetails().getOrgId()));
	}

	@RequestMapping(value = "/bindcard", method = RequestMethod.POST)
	@ApiOperation(value = "编辑绑卡信息")
	public Response<BindCardDto> saveBindCard(BindCardDto bindCardDto) {
		return new Response<>(bindCardBusiness.orgBindCard(SecurityUtil.getUserDetails().getOrgId(), bindCardDto));
	}

	@RequestMapping(value = "/qrcode", method = RequestMethod.GET)
	@ApiOperation(value = "获取推广二维码")
	public Response<String> qrcode(Long orgId) throws IOException, WriterException {
		OrganizationStaQuery query = new OrganizationStaQuery();
		query.setCurrentOrgId(orgId);
		query.setQueryType(1);
		query.setPage(0);
		query.setSize(1);
		PageInfo<OrganizationStaDto> pages = business.adminStaPageByQuery(query);
		OrganizationStaDto result = null;
		if (pages.getContent() != null && pages.getContent().size() > 0) {
			result = pages.getContent().get(0);
			String content = registerUrl + "?orgCode=" + result.getCode();
			return new Response<>("200", QRCodeUtil.create(content, 200, 200), "响应成功");
		}
		return new Response<>();
	}

	@RequestMapping(value = "/adminAgentPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @ApiOperation(value = "获取代理商列表")
	public Response<PageInfo<OrganizationDetailDto>> adminAgentPage(@RequestBody OrganizationQuery query) {
		return new Response<>(business.adminAgentPageByQuery(query));
	}

	@RequestMapping(value = "/singleSta/{currentOrgId}", method = RequestMethod.GET)
	@ApiOperation(value = "单个代理商数据统计")
	public Response<OrganizationStaDto> singleSta(@PathVariable("currentOrgId") Long currentOrgId) {
		OrganizationStaQuery query = new OrganizationStaQuery();
		query.setCurrentOrgId(currentOrgId);
		query.setQueryType(1);
		query.setPage(0);
		query.setSize(1);
		PageInfo<OrganizationStaDto> pages = business.adminStaPageByQuery(query);
		OrganizationStaDto result = null;
		if (pages.getContent() != null && pages.getContent().size() > 0) {
			result = pages.getContent().get(0);
		}
		return new Response<>(result);
	}

	@RequestMapping(value = "/childrenSta", method = RequestMethod.GET)
	@ApiOperation(value = "下级代理商数据统计")
	public Response<PageInfo<OrganizationStaDto>> childrenSta(OrganizationStaQuery query) {
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		query.setQueryType(2);
		return new Response<>(business.adminStaPageByQuery(query));
	}

	@RequestMapping(value = "/tradingFow/", method = RequestMethod.GET)
	@ApiOperation(value = "查询交易流水")
	public Response<PageInfo<TradingFowDto>> tradingFow(TradingFowQuery query) {
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		query.setTreeCode(SecurityUtil.getUserDetails().getTreeCode());
		return new Response<>(business.tradingFowPageByQuery(query));
	}

	@RequestMapping(value = "add/agent", method = RequestMethod.POST)
	@ApiOperation(value = "添加代理商")
	public Response<OrganizationDto> agent(OrganizationForm orgForm) {
		return new Response<>(business.agent(orgForm));
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出代理商数据")
	public void export(OrganizationStaQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		// query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		PageInfo<OrganizationStaDto> result = business.adminStaPageByQuery(query);
		File file = null;
		FileInputStream is = null;
		try {
			String fileName = "agnet_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			List<String> columnDescList = columnDescList();
			List<List<String>> dataList = dataList(result.getContent());
			PoiUtil.writeDataToExcel("代理商数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出代理商数据到excel异常：" + e.getMessage());
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

	private List<List<String>> dataList(List<OrganizationStaDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (OrganizationStaDto trade : content) {
			String state = "";
			if (trade.getState() != null) {
				state = trade.getState().getState();
			}
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getId() == null ? "" : trade.getId()));
			data.add(trade.getCode() == null ? "" : trade.getCode());
			data.add(trade.getName() == null ? "" : trade.getName());
			data.add(String.valueOf(trade.getLevel() == null ? "" : (trade.getLevel() + "级")));
			data.add(String.valueOf(trade.getChildrenCount() == null ? "" : trade.getChildrenCount()));
			data.add(String.valueOf(trade.getPromotionCount() == null ? "" : trade.getPromotionCount()));
			data.add(String.valueOf(trade.getAvailableBalance() == null ? "" : trade.getAvailableBalance()));
			data.add(trade.getBindName() == null ? "" : trade.getBindName());
			data.add(trade.getBingPhone() == null ? "" : trade.getBingPhone());
			data.add(state);
			data.add(trade.getCreateTime() != null ? sdf.format(trade.getCreateTime()) : "");
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("代理商ID");
		result.add("代理商代码");
		result.add("代理商名称");
		result.add("代理商层级");
		result.add("下线代理");
		result.add("推广客户");
		result.add("账户余额");
		result.add("代理商姓名");
		result.add("手机号");
		result.add("状态");
		result.add("创建时间");
		return result;
	}

	@RequestMapping(value = "/trading/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出交易流水数据")
	public void tradingExport(TradingFowQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		// query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		query.setTreeCode(SecurityUtil.getUserDetails().getTreeCode());
		PageInfo<TradingFowDto> result = business.tradingFowPageByQuery(query);
		File file = null;
		FileInputStream is = null;
		try {
			String fileName = "trading_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			List<String> columnDescList = columnTradingList();
			List<List<String>> dataList = tradingList(result.getContent());
			PoiUtil.writeDataToExcel("交易流水数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出交易流水数据到excel异常：" + e.getMessage());
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

	private List<List<String>> tradingList(List<TradingFowDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (TradingFowDto trade : content) {
			CapitalFlowType type = trade.getType();
			String busType = "";
			if (type != null) {
				busType = type.getType();
			}
			String isTest = "";
			if (trade.getIsTest() != null && trade.getIsTest()) {
				isTest = "是";
			} else {
				isTest = "否";
			}
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getId() == null ? "" : trade.getId()));
			data.add(trade.getCustomerName() == null ? "" : trade.getCustomerName());
			data.add(trade.getTradingNumber() == null ? "" : trade.getTradingNumber());
			data.add(trade.getFlowNo() == null ? "" : trade.getFlowNo());
			data.add(trade.getOccurrenceTime() != null ? sdf.format(trade.getOccurrenceTime()) : "");
			data.add(busType);
			data.add(String.valueOf(trade.getAmount() == null ? "" : trade.getAmount()));
			data.add(String.valueOf(trade.getAvailableBalance() == null ? "" : trade.getAvailableBalance()));
			data.add(trade.getStockCode() == null ? "" : trade.getStockCode());
			data.add(trade.getMarkedStock() == null ? "" : trade.getMarkedStock());
			data.add(trade.getAgentCode() + "/" + trade.getAgentCodeName());
			data.add(isTest);
			result.add(data);
		}
		return result;
	}

	private List<String> columnTradingList() {
		List<String> result = new ArrayList<>();
		result.add("订单ID");
		result.add("客户姓名");
		result.add("交易帐号");
		result.add("交易编码");
		result.add("交易时间");
		result.add("业务类型");
		result.add("交易金额");
		result.add("账户余额");
		result.add("股票代码");
		result.add("标的股票");
		result.add("所属代理商代码/名称");
		result.add("是否测试账户");
		return result;
	}

}
