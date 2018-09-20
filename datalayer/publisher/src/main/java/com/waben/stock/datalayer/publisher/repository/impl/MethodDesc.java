package com.waben.stock.datalayer.publisher.repository.impl;

public class MethodDesc {

	private String name;

	private Class<?>[] paramTypes;

	public MethodDesc(String name, Class<?>[] paramTypes) {
		super();
		this.name = name;
		this.paramTypes = paramTypes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class<?>[] getParamTypes() {
		return paramTypes;
	}

	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}

}
