package com.waben.stock.interfaces.dto.message;

/**
 * 
 * @author Created by hujian on 2018年1月7日
 */
public class MessageReceiptDto {

	private Long id;
	
	private MessagingDto message;
	
	private String recipient;
	
	private Boolean state = false;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MessagingDto getMessage() {
		return message;
	}

	public void setMessage(MessagingDto message) {
		this.message = message;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
	
	
	
}
