package com.waben.stock.datalayer.investors.warpper.messagequeue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Created by yuyidi on 2017/11/27.
 * @desc
 */
public abstract class RabbitMQProducer<T> implements Producer, RabbitTemplate.ConfirmCallback {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RabbitTemplate template;

    public void direct(String routingKey, String message) {
        template.convertAndSend(routingKey, message);
    }

    public void topic(String exchange, String routingKey, T message) {
        template.convertAndSend(exchange, routingKey, message);
    }

    public void fanout(String exchange, String message) {
        template.convertAndSend(exchange, "", message);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("消息ID:{}", correlationData.getId());
    }
}
