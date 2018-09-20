package com.waben.stock.interfaces.commonapi.netease.imbean;

public class NeteaseChatroomRequestAddrParam {

	/**
	 * 聊天室id
	 */
	private long roomid;
	/**
	 * 进入聊天室的账号
	 */
	private String accid;
	/**
	 * 客户端类型
	 * <ul>
	 * <li>1:weblink（客户端为web端时使用）;默认1</li>
	 * <li>2:commonlink（客户端为非web端时使用）</li>
	 * </ul>
	 */
	private int clienttype;

	public long getRoomid() {
		return roomid;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	public String getAccid() {
		return accid;
	}

	public void setAccid(String accid) {
		this.accid = accid;
	}

	public int getClienttype() {
		return clienttype;
	}

	public void setClienttype(int clienttype) {
		this.clienttype = clienttype;
	}

}
