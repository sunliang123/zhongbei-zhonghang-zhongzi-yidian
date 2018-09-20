package com.waben.stock.datalayer.channel.warpper;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaRepositories(queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND,
        basePackages = "com.waben.stock.datalayer.channel.repository.impl.jpa")
@EntityScan(basePackages = "com.waben.stock.datalayer.channel")
@Configuration
public class DataAccessConfigurer {

}