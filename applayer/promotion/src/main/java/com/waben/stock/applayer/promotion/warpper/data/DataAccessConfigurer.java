package com.waben.stock.applayer.promotion.warpper.data;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND, basePackages = {
		"com.waben.stock.datalayer.activity.repository.jpa",
		"com.waben.stock.datalayer.buyrecord.repository.impl.jpa",
		"com.waben.stock.datalayer.manage.repository.impl.jpa",
		"com.waben.stock.datalayer.message.repository.impl.jpa",
		"com.waben.stock.datalayer.publisher.repository.impl.jpa",
		"com.waben.stock.datalayer.stockcontent.repository.impl.jpa",
		"com.waben.stock.datalayer.stockoption.repository.impl.jpa",
		"com.waben.stock.datalayer.organization.repository.impl.jpa"})
@EntityScan(basePackages = { "com.waben.stock.datalayer.activity.entity",
		"com.waben.stock.datalayer.buyrecord.entity",
		"com.waben.stock.datalayer.manage.entity",
		"com.waben.stock.datalayer.message.entity",
		"com.waben.stock.datalayer.publisher.entity",
		"com.waben.stock.datalayer.stockcontent.entity",
		"com.waben.stock.datalayer.stockoption.entity",
		"com.waben.stock.datalayer.organization.entity"})
@Configuration
public class DataAccessConfigurer {

}