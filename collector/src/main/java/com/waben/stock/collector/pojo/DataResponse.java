package com.waben.stock.collector.pojo;

/**
 * 数据结果对象
 * 
 * @author luomengan
 *
 * @param <T>
 *            数据类型
 */
public class DataResponse<T> {

	/**
	 * 状态码
	 */
	private String code = "200";
	/**
	 * 响应实体对象
	 */
	private T data;
	/**
	 * 响应消息提示
	 */
	private String message = "OK";

	public DataResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public DataResponse(T data) {
		this.data = data;
	}

	public DataResponse(String code, T data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
