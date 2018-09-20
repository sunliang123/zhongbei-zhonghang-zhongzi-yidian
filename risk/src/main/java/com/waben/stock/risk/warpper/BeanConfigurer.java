package com.waben.stock.risk.warpper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @author Created by yuyidi on 2017/9/19.
 * @desc
 */
@Configuration
public class BeanConfigurer {

    Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_SELF_REFERENCES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        jackson2HttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return jackson2HttpMessageConverter;
    }


    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        logger.info("host,username:{}{}", connectionFactory.getHost(), connectionFactory.getUsername());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        return rabbitTemplate;
    }

    /**
     * 创建 重新委托卖出队列
     * @return
     */
    @Bean(name = "againEntrust")
    public Queue againEntrustQueue() {
        return new Queue("againEntrust");
    }

    /**
     * 创建 委托单已买入成功队列
     * @return
     */
    @Bean(name = "entrustBuyIn")
    public Queue entrustBuyInQueue() {
        return new Queue("entrustBuyIn");
    }
    /**
     * 创建 卖出委托单卖出成功队列
     * @return
     */
    @Bean(name = "entrustSellOut")
    public Queue entrustSellOutQueue() {
        return new Queue("entrustSellOut");
    }

    /**
     * 创建废单队列
     * @return
     */
    @Bean(name = "entrustWaste")
    public Queue wasteQueue() {
        return new Queue("entrustWaste");
    }


    /**
     * 点买交换机
     */
    @Bean("buyRecord")
    public TopicExchange buyRecordExchange() {
        return new TopicExchange("buyRecord");
    }

//    /**
//     * 风控交换机
//     */
//    @Bean("risk")
//    public TopicExchange riskExchange() {
//        return new TopicExchange("risk");
//    }

    @Bean
    public Binding bindingExchangAgainEntrust(@Qualifier("againEntrust") Queue queue,
                                              @Qualifier("buyRecord") TopicExchange buyRecordExchange) {
        return BindingBuilder.bind(queue).to(buyRecordExchange).with("again");
    }

    @Bean
    public Binding bindingExchangEntrustBuyIn(@Qualifier("entrustBuyIn") Queue queue,
                                              @Qualifier("buyRecord") TopicExchange buyRecordExchange) {
        return BindingBuilder.bind(queue).to(buyRecordExchange).with("buyIn");
    }
    @Bean
    public Binding bindingExchangEntrustSellOut(@Qualifier("entrustSellOut") Queue queue,
                                         @Qualifier("buyRecord") TopicExchange buyRecordExchange) {
        return BindingBuilder.bind(queue).to(buyRecordExchange).with("sellOut");
    }

    @Bean
    public Binding bindingExchangWaste(@Qualifier("entrustWaste") Queue queue,
                                         @Qualifier("buyRecord") TopicExchange buyRecordExchange) {
        return BindingBuilder.bind(queue).to(buyRecordExchange).with("waste");
    }

}
