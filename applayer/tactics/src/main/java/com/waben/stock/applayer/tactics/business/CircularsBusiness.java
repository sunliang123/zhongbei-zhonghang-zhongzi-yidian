package com.waben.stock.applayer.tactics.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.interfaces.dto.manage.CircularsDto;
import com.waben.stock.interfaces.exception.ServiceException;
import com.waben.stock.interfaces.pojo.Response;
import com.waben.stock.interfaces.pojo.query.CircularsQuery;
import com.waben.stock.interfaces.pojo.query.PageInfo;
import com.waben.stock.interfaces.service.manage.CircularsInterface;

/**
 * 公告 Business
 * 
 * @author luomengan
 *
 */
@Service("tacticsCircularsBusiness")
public class CircularsBusiness {

	@Autowired
	private CircularsInterface circularsReference;

	public PageInfo<CircularsDto> pages(CircularsQuery query) {
		Response<PageInfo<CircularsDto>> response = circularsReference.pages(query);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

	public List<CircularsDto> fetchCirculars(Boolean enable) {
		Response<List<CircularsDto>> response = circularsReference.fetchCirculars(enable);
		if ("200".equals(response.getCode())) {
			return response.getResult();
		}
		throw new ServiceException(response.getCode());
	}

}
