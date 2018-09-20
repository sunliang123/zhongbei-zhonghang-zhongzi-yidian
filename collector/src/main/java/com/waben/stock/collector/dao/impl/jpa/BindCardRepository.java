package com.waben.stock.collector.dao.impl.jpa;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.waben.stock.collector.entity.BindCard;

/**
 * 绑卡 Repository
 * 
 * @author luomengan
 *
 */
public interface BindCardRepository extends Repository<BindCard, Long> {

	BindCard save(BindCard bindCard);

	void delete(Long id);

	Page<BindCard> findAll(Pageable pageable);
	
	List<BindCard> findAll();

	BindCard findById(Long id);

	BindCard findByDomainAndDataId(String domain, Long dataId);
	
}
