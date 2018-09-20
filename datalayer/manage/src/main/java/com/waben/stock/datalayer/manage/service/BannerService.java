package com.waben.stock.datalayer.manage.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.waben.stock.datalayer.manage.entity.Banner;
import com.waben.stock.datalayer.manage.entity.BannerForward;
import com.waben.stock.datalayer.manage.repository.BannerDao;
import com.waben.stock.interfaces.enums.BannerForwardCategory;
import com.waben.stock.interfaces.pojo.query.BannerQuery;
import org.springframework.util.StringUtils;

/**
 * 轮播 Service
 *
 * @author luomengan
 */
@Service
public class BannerService {

	@Autowired
	private BannerDao bannerDao;

	/***
	 * @author yuyidi 2017-11-21 11:15:44
	 * @method findBanners
	 * @param enable
	 * @return java.util.List<com.waben.stock.datalayer.manage.entity.Banner>
	 * @description
	 */
	public List<Banner> findBanners(Boolean enable) {
		if (enable) {
			return bannerDao.retrieveBanners(enable);
		}
		return bannerDao.retrieveBannersOrderByCreateTime();
	}

	public Page<Banner> pagesByQuery(final BannerQuery query) {
		Pageable pageable = new PageRequest(query.getPage(), query.getSize());
		Page<Banner> pages = bannerDao.page(new Specification<Banner>() {
			@Override
			public Predicate toPredicate(Root<Banner> root, CriteriaQuery<?> criteriaQuery,
					CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicatesList = new ArrayList<>();
				if (query.getCategory() != null) {
					// APP查询
					Join<Banner, BannerForward> join = root.join("bannerForward", JoinType.LEFT);
					Predicate categoryQuery = criteriaBuilder
							.equal(join.get("category").as(BannerForwardCategory.class), query.getCategory());
					predicatesList.add(categoryQuery);
					criteriaQuery.orderBy(criteriaBuilder.asc(root.<Date>get("sort").as(Integer.class)));
				} else {
					criteriaQuery.orderBy(criteriaBuilder.desc(root.<Date>get("createTime").as(Date.class)));
				}

				if (!StringUtils.isEmpty(query.getDescription())) {
					Predicate descriptionQuery = criteriaBuilder.equal(root.get("description").as(String.class),
							query.getDescription());
					predicatesList.add(descriptionQuery);
				}

				if (query.getEnable() != null && query.getEnable() != 2) {
					Predicate enableQuery = criteriaBuilder.equal(root.get("enable").as(Integer.class),
							query.getEnable());
					predicatesList.add(enableQuery);
				}
				criteriaQuery.where(predicatesList.toArray(new Predicate[predicatesList.size()]));
				
				return criteriaQuery.getRestriction();
			}
		}, pageable);
		return pages;
	}

	public Banner fetchById(Long id) {
		return bannerDao.retrieve(id);
	}

	public void delete(Long id) {
		bannerDao.delete(id);
	}

	public Banner save(Banner banner) {
		return bannerDao.create(banner);
	}

	public Banner revision(Banner banner) {
		return bannerDao.update(banner);
	}
}
