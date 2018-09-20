package com.waben.stock.datalayer.message.repository.impl.jpa;

import java.util.List;

import com.waben.stock.datalayer.message.entity.OutsidePushConfig;

/**
 * 站外推送配置 Jpa
 * 
 * @author luomengan
 *
 */
public interface OutsidePushConfigRepository extends CustomJpaRepository<OutsidePushConfig, Long> {

	List<OutsidePushConfig> findByIsDefault(Boolean isDefault);

	List<OutsidePushConfig> findByDeviceTypeAndShellIndex(Integer deviceType, Integer shellIndex);

}
