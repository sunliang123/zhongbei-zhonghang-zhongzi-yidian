package com.waben.stock.applayer.promotion.warpper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

@Component
public class WebDisposableBean implements DisposableBean, ExitCodeGenerator {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public int getExitCode() {
		return 5;
	}

	@Override
	public void destroy() throws Exception {
		logger.info("服务销毁!");
	}

}
