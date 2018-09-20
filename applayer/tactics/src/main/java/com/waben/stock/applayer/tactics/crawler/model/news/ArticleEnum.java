/*
 * Copyright (c) 2016 Srs Group - 版权所有
 * 
 * This software is the confidential and proprietary information of
 * Strong Group. You shall not disclose such confidential information 
 * and shall use it only in accordance with the terms of the license 
 * agreement you entered into with www.srs.cn.
 */
package com.waben.stock.applayer.tactics.crawler.model.news;

/**
 *
 * 描述:
 *
 * @author  C.C
 * @created 2016年11月1日 下午2:45:59
 * @since   v1.0.0
 */
public enum ArticleEnum {
	
	TYPE_NEWBIE_GUIDE(1, "新手引导"),
	TYPE_GAME_RULES(2, "玩法规则"),
	TYPE_COOPERATIVE_AGREEMENTS(3, "合作协议"),
	TYPE_REGISTRATIOIN_PROTOCOL(4, "风险告知"),
	TYPE_RISK_DISCLOSURE(5, "注册协议"),
	
	
	
	FORMAT_HTML(1, "html"),//富文本
	FORMAT_H5(2, "h5"),
	
	
	
	;
	
	
	private int value;
	private String name;
	
	
	private ArticleEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}
	
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
