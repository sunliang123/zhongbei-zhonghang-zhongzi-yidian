package com.waben.stock.applayer.tactics.wrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import com.waben.stock.applayer.tactics.service.RedisCache;

@Component
public class WebDisposableBean implements DisposableBean, ExitCodeGenerator {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RedisCache redisCache;

	@Override
	public int getExitCode() {
		return 5;
	}

	@Override
	public void destroy() throws Exception {
		logger.info("服务销毁!");
		// 销毁jedis的连接池
		if (redisCache != null) {
			redisCache.getPool().destroy();
		}
	}

}
