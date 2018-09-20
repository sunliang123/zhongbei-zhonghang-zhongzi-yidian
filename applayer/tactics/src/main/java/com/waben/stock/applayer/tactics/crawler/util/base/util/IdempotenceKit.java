package com.waben.stock.applayer.tactics.crawler.util.base.util;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.atomic.RedisAtomicInteger;

/**
 * Created by 12 on 2017/5/24.
 *
 *
 */
public class IdempotenceKit {

    public static final String REDIS_KEY= "idempotence:";


    /**
     * 传入指定内容的key 通过redis
     * 保持一段时间的幂等性
     *
     * @param key
     * @param redisTemplate
     * @return
     */
    public static boolean isExsit(String key, RedisTemplate redisTemplate,int sec) {
        RedisAtomicInteger atomicInteger = new RedisAtomicInteger(REDIS_KEY+key, redisTemplate);
        if (!atomicInteger.compareAndSet(0, 1)) {
            return true;
        }
        atomicInteger.expire(sec, TimeUnit.SECONDS);
        return false;
    }

}
