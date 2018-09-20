package com.waben.stock.applayer.strategist.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitmqConfiguration {

	Logger logger = LoggerFactory.getLogger(getClass());

	public static final String withdrawQueryQueueName = "strategist-withdrawQuery";
	
	public static final String payQueryQueueName = "strategist-payQuery";

	@Autowired
	private ConnectionFactory connectionFactory;

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public RabbitTemplate rabbitTemplate() {
		logger.info("host,username:{}{}", connectionFactory.getHost(), connectionFactory.getUsername());
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		return rabbitTemplate;
	}
	
	@Bean(name = { "withdrawQueryListenerContainerFactory" })
	public SimpleRabbitListenerContainerFactory historicalDataListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrentConsumers(2);
		factory.setMaxConcurrentConsumers(4);
		return factory;
	}

	/**
	 * 创建 查询代扣 队列
	 */
	@Bean
	public Queue withdrawQueryQueue() {
		return new Queue(withdrawQueryQueueName);
	}

	/**
	 * 创建 查询支付 队列
	 */
	@Bean
	public Queue payQueryQueue() {
		return new Queue(payQueryQueueName);
	}
	
}
