package com.waben.stock.applayer.strategist.rabbitmq.consumer;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waben.stock.applayer.strategist.business.CapitalAccountBusiness;
import com.waben.stock.applayer.strategist.business.QuickPayBusiness;
import com.waben.stock.applayer.strategist.payapi.wabenpay.config.WBConfig;
import com.waben.stock.applayer.strategist.rabbitmq.RabbitmqConfiguration;
import com.waben.stock.applayer.strategist.rabbitmq.RabbitmqProducer;
import com.waben.stock.applayer.strategist.rabbitmq.message.WithdrawQueryMessage;
import com.waben.stock.interfaces.commonapi.wabenpay.WabenPayOverHttp;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawQueryOrderParam;
import com.waben.stock.interfaces.commonapi.wabenpay.bean.WithdrawQueryOrderRet;
import com.waben.stock.interfaces.dto.publisher.WithdrawalsOrderDto;
import com.waben.stock.interfaces.enums.WithdrawalsState;
import com.waben.stock.interfaces.util.JacksonUtil;

@Component
@RabbitListener(queues = { RabbitmqConfiguration.withdrawQueryQueueName }, containerFactory = "withdrawQueryListenerContainerFactory")
public class WithdrawQueryConsumer {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private RabbitmqProducer producer;

	@Autowired
	private WBConfig wbConfig;

	@Autowired
	private QuickPayBusiness quickPayBusiness;

	@Autowired
	private CapitalAccountBusiness accountBusiness;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

	@RabbitHandler
	public void handlerMessage(String message) {
		logger.info("消费代付查询消息:{}", message);
		WithdrawQueryMessage messgeObj = JacksonUtil.decode(message, WithdrawQueryMessage.class);
		try {
			if (messgeObj.getConsumeCount() == 0) {
				Thread.sleep(5 * 60 * 1000);
			}
			WithdrawQueryOrderParam param = new WithdrawQueryOrderParam();
			param.setAppId(messgeObj.getAppId());
			param.setOutOrderNo(messgeObj.getOutOrderNo());
			param.setTimestamp(sdf.format(new Date()));
			WithdrawQueryOrderRet queryRet = WabenPayOverHttp.withdrawQuery(param, wbConfig.getKey());
			if (queryRet.getPayStatus() == 4) {
				WithdrawalsOrderDto order = quickPayBusiness.findByWithdrawalsNo(param.getOutOrderNo());
				accountBusiness.withdrawals(order.getPublisherId(), order.getId(), WithdrawalsState.PROCESSED);
			} else if (queryRet.getPayStatus() == 5) {
				WithdrawalsOrderDto order = quickPayBusiness.findByWithdrawalsNo(param.getOutOrderNo());
				accountBusiness.withdrawals(order.getPublisherId(), order.getId(), WithdrawalsState.FAILURE);
			} else if (messgeObj.getConsumeCount() > 50 && queryRet.getStatus() == 3 && queryRet.getMsg() != null
					&& queryRet.getMsg().indexOf("不存在") > 0) {
				WithdrawalsOrderDto order = quickPayBusiness.findByWithdrawalsNo(param.getOutOrderNo());
				accountBusiness.withdrawals(order.getPublisherId(), order.getId(), WithdrawalsState.FAILURE);
			} else {
				retry(messgeObj);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			retry(messgeObj);
		}
	}

	private void retry(WithdrawQueryMessage messgeObj) {
		try {
			int consumeCount = messgeObj.getConsumeCount();
			if (consumeCount < 100) {
				messgeObj.setConsumeCount(consumeCount + 1);
				Thread.sleep(3000);
				producer.sendMessage(RabbitmqConfiguration.withdrawQueryQueueName, messgeObj);
			}
		} catch (Exception ex) {
			throw new RuntimeException(RabbitmqConfiguration.withdrawQueryQueueName + " message retry exception!", ex);
		}
	}

}
