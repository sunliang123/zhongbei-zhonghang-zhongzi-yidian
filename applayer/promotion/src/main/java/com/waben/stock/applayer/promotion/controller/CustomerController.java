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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.waben.stock.applayer.promotion.business.CustomerBusiness;
import com.waben.stock.applayer.promotion.business.OrganizationPublisherBusiness;
import com.waben.stock.applayer.promotion.security.SecurityUtil;
import com.waben.stock.applayer.promotion.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.organization.CustomerDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.organization.CustomerQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 客户 Controller
 * 
 * @author luomengan
 *
 */
@RestController("promotionCustomerController")
@RequestMapping("/customer")
@Api(description = "客户接口列表")
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CustomerBusiness customerBusiness;

	@Autowired
	private OrganizationPublisherBusiness orgPublisherBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@RequestMapping(value = "/adminPage", method = RequestMethod.GET)
	public Response<PageInfo<CustomerDto>> adminPage(CustomerQuery query) {
		query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		return new Response<>(customerBusiness.adminPage(query));
	}

	@RequestMapping(value = "/publisherOrg/{publisherId}/{orgCode}", method = RequestMethod.POST)
	public Response<String> setPublisherOrg(@PathVariable("publisherId") Long publisherId,
			@PathVariable("orgCode") String orgCode) {
		orgPublisherBusiness.addOrgPublisher(publisherId, orgCode);
		Response<String> result = new Response<>();
		result.setResult("success");
		return new Response<>();
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出客户列表")
	public void export(CustomerQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		// query.setCurrentOrgId(SecurityUtil.getUserDetails().getOrgId());
		PageInfo<CustomerDto> result = customerBusiness.adminPage(query);
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

	private List<List<String>> dataList(List<CustomerDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (CustomerDto trade : content) {
			String state = "正常";
			Integer ste = trade.getState();
			if (ste != null && ste == 2) {
				state = "黑名单";
			}
			Boolean isTest = trade.getIsTest();
			String test = "";
			if (isTest != null && isTest) {
				test = "是";
			} else {
				test = "否";
			}
			List<String> data = new ArrayList<>();
			data.add(String.valueOf(trade.getPublisherId() == null ? "" : trade.getPublisherId()));
			data.add(trade.getPublisherName() == null ? "" : trade.getPublisherName());
			data.add(trade.getPublisherPhone() == null ? "" : trade.getPublisherPhone());
			data.add(trade.getOrgCode() + "/" + trade.getOrgName());
			data.add(String.valueOf(trade.getBalance() == null ? "" : trade.getBalance()));
			data.add(trade.getCreateTime() != null ? sdf.format(trade.getCreateTime()) : "");
			data.add(trade.getEndType() == null ? "" : trade.getEndType());
			data.add(test);
			data.add(state);
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("客户ID");
		result.add("客户姓名");
		result.add("客户帐号");
		result.add("所属代理代码/名称");
		result.add("账户余额");
		result.add("注册时间");
		result.add("注册来源");
		result.add("是否测试");
		result.add("状态");
		return result;
	}

}
