package com.waben.stock.applayer.admin.controller.publisher;

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
import org.springframework.web.bind.annotation.*;

import com.waben.stock.applayer.admin.business.publisher.BindCardBusiness;
import com.waben.stock.applayer.admin.business.publisher.CapitalAccountBusiness;
import com.waben.stock.applayer.admin.business.publisher.PublisherBusiness;
import com.waben.stock.applayer.admin.util.PoiUtil;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.publisher.CapitalAccountAdminDto;
import com.waben.stock.interfaces.dto.admin.publisher.PublisherAdminDto;
import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.CapitalAccountAdminQuery;
import com.waben.stock.interfaces.pojo.query.admin.publisher.PublisherAdminQuery;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 发布人 Controller
 * 
 * @author luomengan
 *
 */
@RestController("adminPublisherController")
@RequestMapping("/publisher")
@Api(description = "发布人")
public class PublisherController {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PublisherBusiness business;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	@Autowired
	private BindCardBusiness bindCardBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


	@GetMapping("/pages")
	@ApiOperation(value = "查询发布人")
	public Response<PageInfo<PublisherAdminDto>> pages(PublisherAdminQuery query) {
		return new Response<>(business.adminPagesByQuery(query));
	}

	@GetMapping("/detail/{id}")
	@ApiOperation(value = "查看发布人详情")
	public Response<CapitalAccountAdminDto> pages(@PathVariable Long id) {
		CapitalAccountAdminQuery query = new CapitalAccountAdminQuery();
		query.setPublisherId(id);
		PageInfo<CapitalAccountAdminDto> pageInfo = accountBusiness.adminPagesByQuery(query);
		if (pageInfo.getContent() != null && pageInfo.getContent().size() > 0) {
			return new Response<>(pageInfo.getContent().get(0));
		} else {
			return new Response<>();
		}
	}

	@GetMapping("/bindcard/lists/{id}")
	@ApiOperation(value = "查询绑卡列表")
	public Response<List<BindCardDto>> bindCardList(@PathVariable Long id) {
		return new Response<>(bindCardBusiness.listsByPublisherId(id));
	}

	@PostMapping("/defriend/{id}")
	@ApiOperation(value = "拉黑")
	@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "会员id", required = true)
	public Response<PublisherDto> defriend(@PathVariable Long id) {
		PublisherDto response = business.defriend(id);
		return new Response<>(response);
	}

	@PostMapping("/recover/{id}")
	@ApiOperation(value = "恢复")
	@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "会员id", required = true)
	public Response<PublisherDto> recover(@PathVariable Long id) {
		PublisherDto response = business.recover(id);
		return new Response<>(response);
	}

	@PutMapping("/{id}/{password}")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "会员id", required = true),
			@ApiImplicitParam(paramType = "path", dataType = "String", name = "password", value = "会员密码", required = true) })
	@ApiOperation(value = "修改密码")
	public Response<PublisherDto> password(@PathVariable Long id, @PathVariable String password) {
		PublisherDto response = business.password(id, password);
		return new Response<>(response);
	}

	@GetMapping("/{id}")
	@ApiImplicitParam(paramType = "path", dataType = "Long", name = "id", value = "会员id", required = true)
	@ApiOperation(value = "通过会员id获取会员")
	public Response<PublisherDto> fetchById(@PathVariable Long id) {
		PublisherDto respone = business.findById(id);
		return new Response<>(respone);
	}

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	@ApiOperation(value = "导出会员信息")
	public void export(PublisherAdminQuery query, HttpServletResponse svrResponse) {
		query.setPage(0);
		query.setSize(Integer.MAX_VALUE);
		PageInfo<PublisherAdminDto> result = business.adminPagesByQuery(query);
		File file = null;
		FileInputStream is = null;
		try {
			String fileName = "publisher_" + String.valueOf(System.currentTimeMillis());
			file = File.createTempFile(fileName, ".xls");
			List<String> columnDescList = columnDescList();
			List<List<String>> dataList = dataList(result.getContent());
			PoiUtil.writeDataToExcel("会员数据", file, columnDescList, dataList);

			is = new FileInputStream(file);
			svrResponse.setContentType("application/vnd.ms-excel");
			svrResponse.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
			IOUtils.copy(is, svrResponse.getOutputStream());
			svrResponse.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "导出会员数据到excel异常：" + e.getMessage());
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

	private List<List<String>> dataList(List<PublisherAdminDto> content) {
		List<List<String>> result = new ArrayList<>();
		for (PublisherAdminDto trade : content) {
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
			data.add(String.valueOf(trade.getAvailableBalance() != null ? trade.getAvailableBalance() : ""));
			Boolean isRelName = trade.isRealName();
			String rName = "";
			if (isRelName) {
				rName = "已认证";
			} else {
				rName = "未认证";
			}
			data.add(rName);
			data.add(trade.getCreateTime() != null ? sdf.format(trade.getCreateTime()) : "");

			String endType = trade.getEndType();
			if (endType != null) {
				if (endType.equals("I")) {
					endType = "IOS";
				} else if (endType.equals("A")) {
					endType = "Android";
				} else if (endType.equals("PC")) {
					endType = "PC";
				} else if (endType.equals("H5")) {
					endType = "移动端";
				}
			} else {
				endType = "";
			}
			data.add(endType);
			Integer state = trade.getState();
			String stateStr = "";
			if (state == null || state == 1) {
				stateStr = "正常";
			} else if (state == 2) {
				stateStr = "黑名单";
			}
			data.add(stateStr);
			data.add(test);
			result.add(data);
		}
		return result;
	}

	private List<String> columnDescList() {
		List<String> result = new ArrayList<>();
		result.add("会员ID");
		result.add("客户姓名");
		result.add("注册帐号");
		result.add("账户余额");
		result.add("实名认证");
		result.add("注册时间");
		result.add("注册来源");
		result.add("状态");
		result.add("是否测试");
		return result;
	}
	/**==================模拟账户========================**/

	@RequestMapping(value = "/savePublisher", method = RequestMethod.POST)
	@ApiOperation(value = "新增虚拟测试账号")
	public Response<PublisherAdminDto> savePublisher(PublisherAdminDto dto){
		return new Response<>(business.savePublisher(dto));
	}

	@PutMapping("/modifyPublisher")
	@ApiOperation(value = "修改虚拟测试账号")
	public Response<PublisherAdminDto> modifyPublisher(PublisherAdminDto dto){
		return new Response<>(business.modifyPublisher(dto));
	}

	@DeleteMapping("/delete/{id}")
	@ApiOperation(value = "删除虚拟测试账号")
	public Response<Long> delete(@PathVariable Long id){
		return new Response<>(business.delete(id));
	}

}
