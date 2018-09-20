package com.waben.stock.applayer.tactics.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;

/**
 * 发布人 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsPublisherBusiness")
public class PublisherBusiness {

	@Autowired
	private PublisherInterface publisherReference;

	@Value("${operation.server}")
	private String operationServer;

	@Autowired
	private RestTemplate restTemplate;

	public PublisherDto findById(Long publisherId) {
		Response<PublisherDto> response = publisherReference.fetchById(publisherId);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto register(String phone, String password, String promoter, String endType) {
		Response<PublisherDto> response = publisherReference.register(phone, password, promoter, endType);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto modifyPassword(String phone, String password) {
		Response<PublisherDto> response = publisherReference.modifyPassword(phone, password);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public PublisherDto modifyHeadPortrait(Long publisherId, String headPortrait) {
		Response<PublisherDto> response = publisherReference.modiyHeadportrait(publisherId, headPortrait);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public String uploadHeadPortrait(Long publisherId, MultipartFile file) {
		// String headPortrait =
		// "http://img.jf258.com/uploads/2014-04-09/042211205.jpg";
		String headPortrait = remoteUploadFile(file);
		if (headPortrait != null && !"".equals(headPortrait)) {
			modifyHeadPortrait(publisherId, headPortrait);
		}
		return headPortrait;
	}

	private String remoteUploadFile(MultipartFile file) {
		FileOutputStream fos = null;
		File temp = null;
		try {
			String originalFilename = file.getOriginalFilename();
			int sperateIndex = originalFilename.lastIndexOf(".");
			temp = File.createTempFile(String.valueOf(System.currentTimeMillis()) + originalFilename.substring(0, sperateIndex),
					originalFilename.substring(sperateIndex));
			fos = new FileOutputStream(temp);
			IOUtils.copy(file.getInputStream(), fos);
			fos.flush();
			FileSystemResource resource = new FileSystemResource(temp);
			MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
			param.add("file", resource);
			String result = restTemplate.postForObject(operationServer + "file/upload", param, String.class);
			return result;
		} catch (IOException e) {
			throw new ServiceException(ExceptionConstant.UNKNOW_EXCEPTION, "请求operation上传图片异常!");
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
				}
			}
			if(temp != null) {
				temp.delete();
			}
		}
	}

}
