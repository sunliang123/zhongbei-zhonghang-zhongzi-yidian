package com.waben.stock.applayer.admin.controller.publisher;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.admin.business.publisher.CapitalAccountBusiness;
import com.waben.stock.applayer.admin.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.publisher.CapitalAccountAdminDto;
import com.waben.stock.interfaces.dto.publisher.CapitalAccountDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalAccountAdminQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 资金账户 Controller
 * 
 * @author luomengan
 *
 */
@RestController("adminCapitalAccountController")
@RequestMapping("/capitalAccount")
@Api(description = "资金账户")
public class CapitalAccountController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CapitalAccountBusiness business;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@GetMapping("/pages")
	@ApiOperation(value = "查询资金账户")
	public Response<PageInfo<CapitalAccountAdminDto>> pages(CapitalAccountAdminQuery query) {
		return new Response<>(business.adminPagesByQuery(query));
	}

	@PutMapping("/state/{id}/{state}")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "账户id", required = true),@ApiImplicitParam(paramType = "path", dataType = "Integer", name = "state", value = "账户状态｛正常（1），冻结（2）｝", required = true)})
	@ApiOperation(value = "设置资金账户状态")
	public Response<CapitalAccountDto> modifyState(@PathVariable Long id, @PathVariable Integer state) {
		CapitalAccountDto response = business.revisionState(id, state);
		return new Response<>(response);
	}

	@PutMapping("/account/{id}/{availableBalance}")
	@ApiImplicitParams({@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "账户id", required = true),@ApiImplicitParam(paramType = "path", dataType = "BigDecimal", name = "availableBalance", value = "帐号可用余额", required = true)})
	@ApiOperation(value = "设置资金账户可用余额")
	public Response<CapitalAccountDto> modifyAccount(@PathVariable Long id, @PathVariable BigDecimal availableBalance) {
		CapitalAccountDto response = business.revisionAccount(id, availableBalance);
		return new Response<>(response);
	}
	
	
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出会员资产信息")
	public void export(CapitalAccountAdminQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		PageInfo<CapitalAccountAdminDto> result = business.adminPagesByQuery(query);
		File file = null;
		FileInputStream is = null;
		try {
			String fileName = "capitalaccount_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			List<String> columnDescList = columnDescList();
			List<List<String>> dataList = dataList(result.getContent());
			PoiUtil.writeDataToExcel("会员资产数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出会员资产数据到excel异常：" + e.getMessage());
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

	private List<List<String>> dataList(List<CapitalAccountAdminDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (CapitalAccountAdminDto trade : content) {
			Boolean isTest = trade.getIsTest();
			String test = "";
			if (isTest != null && isTest) {
				test = "是";
			} else {
				test = "否";
			}
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getId() == null ? "" : trade.getId()));
			data.add(trade.getName() == null ? "" : trade.getName());
			data.add(trade.getPhone() == null ? "" : trade.getPhone());
			data.add(String.valueOf(trade.getTotalRecharge() != null ? trade.getTotalRecharge() : ""));
			data.add(String.valueOf(trade.getTotalWithdraw() != null ? trade.getTotalWithdraw() : ""));
			data.add(String.valueOf(trade.getFrozenCapital() != null ? trade.getFrozenCapital() : ""));
			data.add(String.valueOf(trade.getAvailableBalance() != null ? trade.getAvailableBalance() : ""));
			data.add(trade.getUpdateTime() != null ? sdf.format(trade.getUpdateTime()) : "");
			Integer state = trade.getState();
			String stateStr = "";
			if (state == null || state == 1) {
				stateStr = "正常";
			} else if (state == 2) {
				stateStr = "冻结";
			}
			data.add(stateStr);
			data.add(test);
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("ID");
		result.add("客户姓名");
		result.add("帐号");
		result.add("充值金额");
		result.add("提现金额");
		result.add("冻结金额");
		result.add("账户余额");
		result.add("更新时间");
		result.add("资产状态");
		result.add("是否测试");
		return result;
	}
}
