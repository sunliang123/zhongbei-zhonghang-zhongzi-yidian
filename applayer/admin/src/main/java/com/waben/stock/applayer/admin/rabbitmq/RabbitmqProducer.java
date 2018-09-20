package com.waben.stock.applayer.admin.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.waben.stock.interfaces.util.JacksonUtil;

@Component
public class RabbitmqProducer {

	@Autowired
	private RabbitTemplate template;

	public void sendMessage(String queueName, Object message) {
		template.convertAndSend(queueName, JacksonUtil.encode(message));
	}

}
