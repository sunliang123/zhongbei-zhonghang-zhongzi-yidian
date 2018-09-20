package com.waben.stock.applayer.strategist.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waben.stock.applayer.strategist.service.RedisCache;

/**
 * 体验活动 Business
 * 
 * @author luomengan
 *
 */
@Service("strategistExperienceBusiness")
public class ExperienceBusiness {

	@Autowired
	private RedisCache redisCache;
	
	public void join() {
		String result = redisCache.get("experience-total-join");
		if(result == null) {
			redisCache.set("experience-total-join", "348");
			redisCache.set("experience-today-left-over", "19652");
		} else {
			Integer totalJoin = Integer.parseInt(result) + 1;
			Integer todayLeftOver = 20000 - totalJoin;
			redisCache.set("experience-total-join", totalJoin.toString());
			redisCache.set("experience-today-left-over", todayLeftOver.toString());
		}
	}

	public Integer getTotalJoin() {
		String result = redisCache.get("experience-total-join");
		if (result == null) {
			redisCache.set("experience-total-join", "348");
			return 348;
		} else {
			return Integer.parseInt(result);
		}
	}

	public Integer getTodayLeftOver() {
		String result = redisCache.get("experience-today-left-over");
		if (result == null) {
			redisCache.set("experience-today-left-over", "19652");
			return 19652;
		} else {
			return Integer.parseInt(result);
		}
	}

}
