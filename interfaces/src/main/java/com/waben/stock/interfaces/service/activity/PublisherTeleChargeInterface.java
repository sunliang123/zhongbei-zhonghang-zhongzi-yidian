package com.waben.stock.interfaces.service.activity;

import com.waben.stock.interfaces.dto.activity.PublisherDeduTicketDto;
import com.waben.stock.interfaces.dto.activity.PublisherTeleChargeDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * 
 * @author guowei 2018/4/11
 *
 */
public interface PublisherTeleChargeInterface {

	/**
	 * 保存优惠券
	 * @param publisherTeleChargeDtod
	 * @return
	 */
	@RequestMapping(value = "/savePublisherTeleCharge", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PublisherTeleChargeDto> savePublisherTeleCharge(@RequestBody PublisherTeleChargeDto publisherTeleChargeDtod);

	/**
	 * 获取优惠券列表
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@RequestMapping(value = "/getPublisherTeleChargeList", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<PublisherTeleChargeDto>> getPublisherTeleChargeList(@RequestParam(value = "pageno") int pageno, @RequestParam(value = "pagesize") Integer pagesize);


	@RequestMapping(value = "/getPublisherTeleCharge/{publisherTeleChargeId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<PublisherTeleChargeDto> getPublisherTeleCharge(@PathVariable("publisherTeleChargeId") long publisherTeleChargeId);

	@RequestMapping(value = "/setPay/{publisherTeleChargeId}", method = RequestMethod.POST)
	Response<Void> setPay(@PathVariable("publisherTeleChargeId") long publisherTeleChargeId);

	@RequestMapping(value = "/getPublisherTeleChargeByApId/{apId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	Response<List<PublisherTeleChargeDto>> getPublisherTeleChargesByApId(@PathVariable("apId") long apId);
}
