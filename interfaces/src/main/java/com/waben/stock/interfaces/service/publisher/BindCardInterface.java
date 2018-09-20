package com.waben.stock.interfaces.service.publisher;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.waben.stock.interfaces.dto.publisher.BindCardDto;
import com.waben.stock.interfaces.enums.BindCardResourceType;
import com.waben.stock.interfaces.pojo.Response;

/**
 * 
 * @author Created by hujian on 2018年1月18日
 */
public interface BindCardInterface {

	/**
	 * 根据ID获取绑卡信息
	 * 
	 * @param bindCardDto
	 *            绑卡信息
	 * @return 绑卡信息
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Response<BindCardDto> fetchById(@PathVariable("id") Long id);

	/**
	 * 绑卡
	 * 
	 * @param bindCardDto
	 *            绑卡信息
	 * @return 绑卡信息
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<BindCardDto> addBankCard(@RequestBody BindCardDto bindCardDto);

	/**
	 * 更新绑卡
	 * 
	 * @param bindCardDto
	 *            绑卡信息
	 * @return 绑卡信息
	 */
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<BindCardDto> modifyBankCard(@RequestBody BindCardDto bindCardDto);

	/**
	 * 删除绑卡
	 * 
	 * @param id
	 *            绑卡ID
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	Response<Long> dropBankCard(@PathVariable("id") Long id);

	/**
	 * 获取某个资源对象的绑卡列表
	 * 
	 * @param resourceType
	 *            绑卡资源 类型 {@link BindCardResourceType}
	 * @param resourceId
	 *            资源ID
	 * @return 绑卡列表
	 */
	@RequestMapping(value = "/{resourceType}/{resourceId}/lists", method = RequestMethod.GET)
	Response<List<BindCardDto>> listsByResourceTypeAndResourceId(@PathVariable("resourceType") String resourceType,
			@PathVariable("resourceId") Long resourceId);

	@RequestMapping(value = "/name/{name}",method = RequestMethod.GET)
    Response<BindCardDto> fetchOrgBindCardByName(@PathVariable("name") String name);
}
