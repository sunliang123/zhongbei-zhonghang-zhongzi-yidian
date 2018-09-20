package com.waben.stock.applayer.admin.business.publisher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.waben.stock.applayer.admin.business.cache.RedisCache;
import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.admin.publisher.PublisherAdminDto;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.pojo.query.admin.publisher.PublisherAdminQuery;
import com.waben.stock.interfaces.service.organization.OrganizationPublisherInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;
import com.waben.stock.interfaces.util.PasswordCrypt;
import com.waben.stock.interfaces.util.StringUtil;

/**
 * 发布人 Business
 * 
 * @author luomengan
 */
@Service("adminPublisherBusiness")
public class PublisherBusiness {

	@Autowired
	private PublisherInterface reference;

	@Autowired
	private OrganizationPublisherInterface orgReference;

	@Autowired
	private RedisCache redisCache;

	private static final String BLACKUSER_REDISKEY = "BLACKUSER";

	public PageInfo<PublisherAdminDto> adminPagesByQuery(@RequestBody PublisherAdminQuery query) {
		Response<PageInfo<PublisherAdminDto>> response = reference.adminPagesByQuery(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto defriend(Long id) {
		Response<PublisherDto> response = reference.defriend(id);
		if ("200".equals(response.getCode())) {
			redisCache.set(BLACKUSER_REDISKEY + "_" + String.valueOf(id), "true");
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto findById(Long id) {
		Response<PublisherDto> response = reference.fetchById(id);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto recover(Long id) {
		Response<PublisherDto> response = reference.recover(id);
		if ("200".equals(response.getCode())) {
			redisCache.remove(BLACKUSER_REDISKEY + "_" + String.valueOf(id));
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto password(Long id, String password) {
		if(StringUtil.isEmpty(password)) {
			throw new ServiceException(ExceptionConstant.ARGUMENT_EXCEPTION);
		}
		Response<PublisherDto> response = reference.fetchById(id);
		if ("200".equals(response.getCode())) {
			return reference.modifyPassword(response.getResult().getPhone(), password).getResult();
			// TODO 发送信息通知用户
		}
		throw new ServiceException(response.getCode());
	}

	//虚拟账号相关

	public PublisherAdminDto savePublisher(PublisherAdminDto dto){
		Response<PublisherAdminDto> response = reference.savePublisher(dto);
		if("200".equals(response.getCode())){
			if(response.getResult() !=null ){
				orgReference.addOrgPublisher(response.getResult().getId());
			}
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherAdminDto modifyPublisher(PublisherAdminDto dto){
		Response<PublisherAdminDto> response = reference.modifyPublisher(dto);
		if("200".equals(response.getCode())){
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public Long delete(Long id){
		Response<Long> response = reference.deletePublisher(id);
		if("".equals(response.getCode())){
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}
}
