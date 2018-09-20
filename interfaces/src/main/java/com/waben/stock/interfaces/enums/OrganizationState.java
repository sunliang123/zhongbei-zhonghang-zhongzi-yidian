package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 机构状态
 * 
 * @author luomengan
 *
 */
public enum OrganizationState implements CommonalityEnum {

	NORMAL("1", "正常"),

	FROZEN("2", "停用"),
	
	DESTROY("3", "注销");
	
	private String index;

	private String state;

	private OrganizationState(String index, String state) {
		this.index = index;
		this.state = state;
	}

	private static Map<String, OrganizationState> indexMap = new HashMap<String, OrganizationState>();

	static {
		for (OrganizationState _enum : OrganizationState.values()) {
			indexMap.put(_enum.getIndex(), _enum);
		}
	}

	public static OrganizationState getByIndex(String index) {
		return indexMap.get(index);
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
