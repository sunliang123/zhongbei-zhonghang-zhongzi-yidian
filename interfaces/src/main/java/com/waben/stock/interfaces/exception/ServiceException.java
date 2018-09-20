package com.waben.stock.interfaces.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 3246281045201642452L;
	
	private String type;
	private String message;
	private Object[] variables;

	public ServiceException(String type, Object... variables) {
		super(type);
		this.type = type;
		this.variables = variables;
	}

	public ServiceException(String type, String message, Object... variables) {
		super(type);
		this.type = type;
		this.message = message;
		this.variables = variables;
	}

	public ServiceException(String type, Throwable cause, Object... variables) {
		super(type, cause);
		this.type = type;
		this.variables = variables;
	}

	public String getType() {
		return type;
	}

	public String getCustomMessage() {
		return message;
	}

	public Object[] getVariables() {
		return variables;
	}

	public void setVariables(Object[] variables) {
		this.variables = variables;
	}

}
