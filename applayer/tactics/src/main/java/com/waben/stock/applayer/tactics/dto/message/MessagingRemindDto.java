package com.waben.stock.applayer.tactics.dto.message;

public class MessagingRemindDto {

	/**
	 * 是否有未读的站外消息
	 */
	private boolean hasOutsideMessage;
	/**
	 * 是否有未读的站内消息
	 */
	private boolean hasInsideMessage;

	public boolean isHasOutsideMessage() {
		return hasOutsideMessage;
	}

	public void setHasOutsideMessage(boolean hasOutsideMessage) {
		this.hasOutsideMessage = hasOutsideMessage;
	}

	public boolean isHasInsideMessage() {
		return hasInsideMessage;
	}

	public void setHasInsideMessage(boolean hasInsideMessage) {
		this.hasInsideMessage = hasInsideMessage;
	}

}
