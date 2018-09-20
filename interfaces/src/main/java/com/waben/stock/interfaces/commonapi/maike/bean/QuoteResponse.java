package com.waben.stock.interfaces.commonapi.maike.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class QuoteResponse<T> {

	@JsonProperty("Result")
	private List<T> Result;

	@JsonProperty("Signatrue")
	private String Signatrue;

	@JsonProperty("PageCount")
	private int PageCount;

	public List<T> getResult() {
		return Result;
	}

	public void setResult(List<T> result) {
		Result = result;
	}

	public String getSignatrue() {
		return Signatrue;
	}

	public void setSignatrue(String signatrue) {
		Signatrue = signatrue;
	}

	public int getPageCount() {
		return PageCount;
	}

	public void setPageCount(int pageCount) {
		PageCount = pageCount;
	}

}
