package com.waben.stock.datalayer.stockcontent.repository.impl.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.waben.stock.datalayer.stockcontent.entity.Stock;

/**
 * 股票 Jpa
 * 
 * @author luomengan
 *
 */
public interface StockRepository extends CustomJpaRepository<Stock, Long> {

	@Query(value = "select t.* from stock t where t.name like ?1 or t.code like ?2 or t.pinyin_abbr like ?3 limit 0, ?4", nativeQuery = true)
	List<Stock> findByNameLikeOrCodeLikeOrPinyinAbbrLike(String name, String code, String pinyinAbbr, Integer limit);

	Stock findByCode(String code);

	@Transactional
	@Modifying
	@Query(value="update stock set status=?1, name=?2, code=?3 where id=?4",nativeQuery = true)
	Integer revisionById(Boolean status, String name, String code, Long id);

	@Query(value = "select t.* from stock t where t.exponent=?1", nativeQuery = true)
	List<Stock> findByExponentCode(String exponentCode);
	
}
