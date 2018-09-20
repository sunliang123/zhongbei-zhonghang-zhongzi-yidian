package com.waben.stock.interfaces.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 冻结资金状态
 * 
 * @author luomengan
 *
 */
public enum FrozenCapitalStatus implements CommonalityEnum {

	Frozen("1", "冻结"),

	Thaw("2", "解冻");

	private String index;

	private String status;

	private FrozenCapitalStatus(String index, String status) {
		this.index = index;
		this.status = status;
	}

	private static Map<String, FrozenCapitalStatus> valueMap = new HashMap<String, FrozenCapitalStatus>();

	static {
		for (FrozenCapitalStatus _enum : FrozenCapitalStatus.values()) {
			valueMap.put(_enum.getIndex(), _enum);
		}
	}

	public static FrozenCapitalStatus getByIndex(String index) {
		FrozenCapitalStatus result = valueMap.get(index);
		if (result == null) {
			throw new IllegalArgumentException("No element matches " + index);
		}
		return result;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
