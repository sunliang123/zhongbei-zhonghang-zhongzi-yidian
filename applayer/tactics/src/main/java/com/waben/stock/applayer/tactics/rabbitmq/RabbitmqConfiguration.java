package com.waben.stock.applayer.tactics.rabbitmq;

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

	public static final String withdrawQueryQueueName = "tactics-withdrawQuery";
	
	public static final String payQueryQueueName = "tactics-payQuery";

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
	
	/********************* 其他队列 *************************/
	
	@Bean
	public Queue tacticsAgainEntrust() {
		return new Queue("againEntrust");
	}
	
	@Bean
	public Queue tacticsEntrustApplyBuyIn() {
		return new Queue("entrustApplyBuyIn");
	}
	
	@Bean
	public Queue tacticsEntrustApplySellOut() {
		return new Queue("entrustApplySellOut");
	}
	
	@Bean
	public Queue tacticsEntrustApplyWithdraw() {
		return new Queue("entrustApplyWithdraw");
	}
	
	@Bean
	public Queue tacticsEntrustBuyIn() {
		return new Queue("entrustBuyIn");
	}
	
	@Bean
	public Queue tacticsEntrustQueryWithdraw() {
		return new Queue("entrustQueryWithdraw");
	}
	
	@Bean
	public Queue tacticsEntrustSellOut() {
		return new Queue("entrustSellOut");
	}
	
	@Bean
	public Queue tacticsEntrustWaste() {
		return new Queue("entrustWaste");
	}
	
	@Bean
	public Queue tacticsRisk() {
		return new Queue("risk");
	}
	
	@Bean
	public Queue tacticsRiskPositionSellOut() {
		return new Queue("riskPositionSellOut");
	}
	
	@Bean
	public Queue tacticsVoluntarilyApplyBuyIn() {
		return new Queue("voluntarilyApplyBuyIn");
	}
	
	@Bean
	public Queue tacticsVoluntarilyApplySellOut() {
		return new Queue("voluntarilyApplySellOut");
	}
	
}
