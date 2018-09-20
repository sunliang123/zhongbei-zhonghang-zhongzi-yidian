package com.waben.stock.datalayer.publisher.repository;

import org.springframework.data.domain.Page;

import com.waben.stock.datalayer.publisher.entity.Publisher;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/12.
 * @desc
 */
public interface PublisherDao extends BaseDao<Publisher, Long> {

	Publisher retriveByPhone(String phone);

	Publisher retriveBySerialCode(String serialCode);

	Publisher retrieveByPromotionCode(String promotionCode);

	Integer promotionCount(String promotionCode);

	Page<Publisher> pageByPromoter(String promotionCode, int page, int size);

    List<Publisher> retrieveByIsTest(Boolean test);
}
