package com.waben.stock.interfaces.pojo.query;

/**
 * 
 * @author Created by hujian on 2018年1月7日
 */
public class MessageReceiptQuery extends PageAndSortQuery{

	private String recipient;

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	
	
}
