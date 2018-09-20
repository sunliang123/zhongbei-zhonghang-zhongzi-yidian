package com.waben.stock.datalayer.message.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

@Component("messageRedisCache")
public class RedisCache {

	@Value("${redis.host}")
	private String redisHost;

	@Value("${redis.port}")
	private String redisPort;

	@Value("${redis.password}")
	private String redisPassword;

	private JedisPool pool;

	@PostConstruct
	public void initJedisPool() {
		if (redisPassword != null && !"".equals(redisPassword.trim())) {
			pool = new JedisPool(new JedisPoolConfig(), redisHost, Integer.parseInt(redisPort),
					Protocol.DEFAULT_TIMEOUT, redisPassword);
		} else {
			pool = new JedisPool(new JedisPoolConfig(), redisHost, Integer.parseInt(redisPort));
		}
	}

	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public void set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	public void set(String key, String value, int seconds) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.setex(key, seconds, value);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	public JedisPool getPool() {
		return pool;
	}

}
