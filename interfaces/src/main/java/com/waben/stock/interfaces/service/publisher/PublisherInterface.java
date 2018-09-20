package com.waben.stock.interfaces.service.publisher;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.waben.stock.interfaces.dto.admin.publisher.PublisherAdminDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.PublisherQuery;
import com.waben.stock.interfaces.pojo.query.admin.publisher.PublisherAdminQuery;

/**
 * @author Created by yuyidi on 2017/11/12.
 * @desc
 */
public interface PublisherInterface {

	/**
	 * 分页查询发布人（管理后台）
	 * 
	 * @param query
	 *            查询条件
	 * @return 发布人分页数据
	 */
	@RequestMapping(value = "/adminpages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<PublisherAdminDto>> adminPagesByQuery(@RequestBody PublisherAdminQuery query);

	@RequestMapping(value = "/pages", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PageInfo<PublisherDto>> pages(@RequestBody PublisherQuery publisherQuery);

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	Response<PublisherDto> fetchById(@PathVariable("id") Long id);

	@RequestMapping(value = "/phone/{phone}", method = RequestMethod.GET)
	Response<PublisherDto> fetchByPhone(@PathVariable("phone") String phone);

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	Response<PublisherDto> register(@RequestParam(name = "phone") String phone,
			@RequestParam(name = "password") String password, @RequestParam(name = "promoter") String promoter,
			@RequestParam(name = "endType", required = false) String endType);

	@RequestMapping(value = "/modify", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PublisherDto> modify(@RequestBody PublisherDto publisherDto);

	@RequestMapping(value = "/{phone}/modifyPassword", method = RequestMethod.PUT)
	Response<PublisherDto> modifyPassword(@PathVariable("phone") String phone,
			@RequestParam(name = "password") String password);

	@RequestMapping(value = "/{id}/headportrait", method = RequestMethod.PUT)
	Response<PublisherDto> modiyHeadportrait(@PathVariable("id") Long id,
			@RequestParam(name = "headPortrait") String headPortrait);

	@RequestMapping(value = "/{id}/promotion/count", method = RequestMethod.GET)
	Response<Integer> promotionCount(@PathVariable("id") Long id);

	@RequestMapping(value = "/{id}/promotion/userpages", method = RequestMethod.GET)
	Response<PageInfo<PublisherDto>> pagePromotionUser(@PathVariable("id") Long id,
			@RequestParam(name = "page") int page, @RequestParam(name = "size") int size);

	@RequestMapping(value = "/")
	Response<List<PublisherDto>> fetchPublishers();

	@RequestMapping(value = "/istest/{test}", method = RequestMethod.GET)
	Response<List<PublisherDto>> fetchByIsTest(@PathVariable("test") Boolean test);

	@RequestMapping(value = "/defriend/{id}", method = RequestMethod.POST)
	Response<PublisherDto> defriend(@PathVariable("id") Long id);

	@RequestMapping(value = "/recover/{id}", method = RequestMethod.POST)
	Response<PublisherDto> recover(@PathVariable("id") Long id);


	/**模拟账户*/
	@RequestMapping(value = "/savePublisher", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PublisherAdminDto> savePublisher(@RequestBody PublisherAdminDto dto);

	@RequestMapping(value = "/modifyPublisher", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	Response<PublisherAdminDto> modifyPublisher(@RequestBody PublisherAdminDto dto);

	@RequestMapping(value = "/deletePublisher/{id}", method = RequestMethod.GET)
	Response<Long> deletePublisher(@PathVariable("id") Long id);
}
