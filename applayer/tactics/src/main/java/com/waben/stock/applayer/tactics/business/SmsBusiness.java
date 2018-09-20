package com.waben.stock.applayer.tactics.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.constants.ExceptionConstant;
import com.waben.stock.interfaces.dto.publisher.PublisherDto;
import com.waben.stock.interfaces.enums.SmsType;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.service.message.SmsInterface;
import com.waben.stock.interfaces.service.publisher.PublisherInterface;

/**
 * 绑卡 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsSmsBusiness")
public class SmsBusiness {

	@Autowired
	private SmsInterface smsReference;

	@Autowired
	private PublisherInterface publisherReference;

	public void sendAuthCode(String phone, SmsType type) {
		if (type == SmsType.RegistVerificationCode) {
			try {
				Response<PublisherDto> publisherResp = publisherReference.fetchByPhone(phone);
				if (!"200".equals(publisherResp.getCode())
						&& !ExceptionConstant.DATANOTFOUND_EXCEPTION.equals(publisherResp.getCode())) {
					throw new ServiceException(publisherResp.getCode());
				}
				if (publisherResp.getResult() != null) {
					throw new ServiceException(ExceptionConstant.PHONE_BEEN_REGISTERED_EXCEPTION);
				}
			} catch(ServiceException ex) {
				if(!ExceptionConstant.DATANOTFOUND_EXCEPTION.equals(ex.getType())) {
					throw new ServiceException(ex.getType());
				}
			}
		} else if (type == SmsType.ModifyPasswordCode) {
			try {
				Response<PublisherDto> publisherResp = publisherReference.fetchByPhone(phone);
				if (publisherResp.getResult() == null) {
					throw new ServiceException(ExceptionConstant.PHONE_ISNOT_REGISTERED_EXCEPTION);
				}
			} catch(ServiceException ex) {
				if(ExceptionConstant.DATANOTFOUND_EXCEPTION.equals(ex.getType())) {
					throw new ServiceException(ExceptionConstant.PHONE_ISNOT_REGISTERED_EXCEPTION);
				} else {
					throw ex;
				}
			}
		}
		send(phone, type);
	}

	public void send(String phone, SmsType type) {
		Response<String> response = smsReference.send(phone, type.getIndex());
		if ("200".equals(response.getCode())) {
			return;
		}
		throw new ServiceException(response.getCode());
	}

}
