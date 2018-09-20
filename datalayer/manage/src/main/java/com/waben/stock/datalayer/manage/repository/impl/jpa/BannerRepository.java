package com.waben.stock.datalayer.manage.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.Banner;

/**
 * 轮播 Jpa
 * 
 * @author luomengan
 *
 */
public interface BannerRepository extends CustomJpaRepository<Banner, Long> {

	List<Banner> findByEnableOrderBySortAsc(Boolean enable);

	List<Banner> findByOrderByCreateTimeDesc();

}
