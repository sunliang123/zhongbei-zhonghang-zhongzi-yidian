package com.waben.stock.applayer.tactics.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waben.stock.applayer.tactics.business.QuickPayBusiness;
import com.waben.stock.applayer.tactics.payapi.wbpay.config.WBConfig;
import com.waben.stock.applayer.tactics.rabbitmq.RabbitmqConfiguration;
import com.waben.stock.applayer.tactics.rabbitmq.RabbitmqProducer;
import com.waben.stock.applayer.tactics.rabbitmq.message.PayQueryMessage;
import com.waben.stock.interfaces.commonapi.wabenpay.WabenPayOverHttp;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.PayQueryOrderParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.PayQueryOrderRet;
import com.waben.stock.interfaces.enums.PaymentState;
import com.waben.stock.interfaces.util.JacksonUtil;

@Component
@RabbitListener(queues = { RabbitmqConfiguration.payQueryQueueName })
public class PayQueryConsumer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RabbitmqProducer producer;

	@Autowired
	private WBConfig wbConfig;

	@Autowired
	private QuickPayBusiness quickPayBusiness;

	@RabbitHandler
	public void handlerMessage(String message) {
		logger.info("消费支付查询消息:{}", message);
		PayQueryMessage messgeObj = JacksonUtil.decode(message, PayQueryMessage.class);
		try {
			PayQueryOrderParam param = new PayQueryOrderParam();
			param.setAppId(messgeObj.getAppId());
			param.setOutOrderNo(messgeObj.getOutOrderNo());
			param.setOrderNo(messgeObj.getOrderNo());

			PayQueryOrderRet queryRet = WabenPayOverHttp.payQuery(param, wbConfig.getKey());
			if (queryRet != null && queryRet.getStatus() == 2) {
				quickPayBusiness.payCallback(queryRet.getOutOrderNo(), PaymentState.Paid);
			} else {
				retry(messgeObj);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			retry(messgeObj);
		}
	}

	private void retry(PayQueryMessage messgeObj) {
		try {
			int consumeCount = messgeObj.getConsumeCount();
			if (consumeCount < 60) {
				messgeObj.setConsumeCount(consumeCount + 1);
				Thread.sleep(5000);
				producer.sendMessage(RabbitmqConfiguration.payQueryQueueName, messgeObj);
			}
		} catch (Exception ex) {
			throw new RuntimeException(RabbitmqConfiguration.payQueryQueueName + " message retry exception!", ex);
		}
	}

}
