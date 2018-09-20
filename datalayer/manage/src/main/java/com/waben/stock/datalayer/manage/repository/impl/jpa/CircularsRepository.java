package com.waben.stock.datalayer.manage.repository.impl.jpa;

import java.util.Date;
import java.util.List;

import com.waben.stock.datalayer.manage.entity.Circulars;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * 通告 Jpa
 * 
 * @author luomengan
 *
 */
public interface CircularsRepository extends CustomJpaRepository<Circulars, Long> {

	List<Circulars> findByEnableAndExpireTimeGreaterThan(Boolean enable,Date date);

	List<Circulars> findAllByEnable(Boolean enable);

	@Transactional
	@Modifying
	@Query(value="update circulars set title=?2, content=?3 where id=?1",nativeQuery = true)
	Integer updateById(Long id, String title, String content);
}
