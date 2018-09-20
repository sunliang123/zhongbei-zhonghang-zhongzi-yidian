package com.waben.stock.applayer.strategist.crawler;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.mongodb.MongoClientURI;
import com.waben.stock.applayer.strategist.crawler.util.cache.RedisJsonSerializer;
import com.waben.stock.applayer.strategist.crawler.util.prop.CustomProperties;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class CrawlerConfiguration {

	@Autowired
	CustomProperties springProperties;

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private String redisPort;

	@Value("${redis.password}")
	private String redisPassword;
	
	@Value("${sys.mongo.uri}")
	private String mongoUri;
	
	@Bean
	public MongoTemplate mongoTemplate() {
		try {
			// String url = springProperties.getProperty("sys.mongo.uri");
			MongoDbFactory fac = new SimpleMongoDbFactory(
					new MongoClientURI(mongoUri));
			return new MongoTemplate(fac);
		} catch (UnknownHostException e) {
			throw new RuntimeException(e);
		}
	}

	@Bean
	public RedisTemplate redisTemplate() {
		RedisTemplate template = new RedisTemplate();
		template.setConnectionFactory(redisConnectionFactory());
		template.setDefaultSerializer(fastJsonSerializer());
		template.setKeySerializer(stringSerializer());
		return template;
	}

	@Bean
	public JedisPoolConfig jedisPoolConfig() {
		JedisPoolConfig result = new JedisPoolConfig();
		result.setMaxTotal(20);
		result.setMaxIdle(10);
		result.setMinIdle(5);
		return result;
	}

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		JedisConnectionFactory result = new JedisConnectionFactory(jedisPoolConfig());
//		result.setHostName(springProperties.getProperty("sys.redis.hostName"));
//		result.setPort(Integer.parseInt(springProperties.getProperty("sys.redis.port").trim()));
//		result.setPassword(springProperties.getProperty("sys.redis.pass"));
//		result.setUsePool(Boolean.parseBoolean(springProperties.getProperty("sys.redis.usePool")));
//		result.setDatabase(Integer.parseInt(springProperties.getProperty("sys.redis.database")));
		
		result.setHostName(redisHost);
		result.setPort(Integer.parseInt(redisPort));
		result.setPassword(redisPassword);
		result.setUsePool(true);
		result.setDatabase(0);
		return result;
	}

	@Bean
	public RedisJsonSerializer fastJsonSerializer() {
		return new RedisJsonSerializer();
	}
	
	@Bean
	public StringRedisSerializer stringSerializer(){
		return new StringRedisSerializer();
	}

}
