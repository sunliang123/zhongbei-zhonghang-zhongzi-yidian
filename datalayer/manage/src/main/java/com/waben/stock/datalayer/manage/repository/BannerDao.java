package com.waben.stock.datalayer.manage.repository;

import java.util.List;

import com.waben.stock.datalayer.manage.entity.Banner;

/**
 * 轮播 Dao
 * 
 * @author luomengan
 *
 */
public interface BannerDao extends BaseDao<Banner, Long> {

	List<Banner> retrieveBanners(Boolean enable);

	List<Banner> retrieveBannersOrderByCreateTime();
}
