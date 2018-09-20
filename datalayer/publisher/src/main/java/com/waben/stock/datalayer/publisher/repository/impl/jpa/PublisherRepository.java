package com.waben.stock.datalayer.publisher.repository.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.waben.stock.datalayer.publisher.entity.Publisher;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/12.
 * @desc
 */
public interface PublisherRepository extends CustomJpaRepository<Publisher, Long> {

	Publisher findByPhone(String phone);

	Publisher findBySerialCode(String serialCode);

	Publisher findByPromotionCode(String promotionCode);

	@Query("select count(*) from Publisher where promoter=?1")
	Integer promotionCount(String promotionCode);

	Page<Publisher> findByPromoter(String promoter, Pageable pageable);

	List<Publisher> findPublishersByIsTest(Boolean test);

	List<Publisher> findByIsTestIsNull();
}
