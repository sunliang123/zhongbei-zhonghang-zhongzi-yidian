package com.waben.stock.datalayer.message.repository;

import com.waben.stock.datalayer.message.entity.OutsidePushConfig;

/**
 * 站外推送配置 Dao
 * 
 * @author luomengan
 *
 */
public interface OutsidePushConfigDao extends BaseDao<OutsidePushConfig, Long> {

	OutsidePushConfig getDefaultConfig();

	OutsidePushConfig retrieveByDeviceTypeAndShellIndex(Integer deviceType, Integer shellIndex);

}
