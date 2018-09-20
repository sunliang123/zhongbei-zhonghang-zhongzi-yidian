package com.waben.stock.applayer.tactics.crawler.util.mvc;

import java.util.List;

/**
 * 
 * @author SUNDONG_
 *
 * @param <T>
 */
public class Page<T> {
	List<T> data;
	Integer total;
	Integer start;

	Integer pageSize = 10;
	Integer resultCount;
	Integer Other;

	public static final int DEFAULE_PAGESIZE = 10;


	public Page(org.springframework.data.domain.Page page){
		this(page.getNumber(),page.getSize(),(int)page.getTotalElements(), page.getContent());

	}

	public Page(com.baomidou.mybatisplus.plugins.Page page){

		this(page.getCurrent(),page.getSize(),page.getTotal(),page.getRecords());
	}


	public Page(Integer start, Integer pageSize, Integer resultCount) {
		super();
		this.total = resultCount / pageSize + (resultCount % pageSize > 0 ? 1 : 0);
		this.start = start;
		this.pageSize = pageSize;
		this.resultCount = resultCount;
	}


	public Page(Integer start, Integer pageSize, Integer resultCount,List<T> data  ) {
		this(start,pageSize,resultCount);
		this.data=data;
	}

	public Page() {
		super();
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}
	public Integer getOther() {
		return Other;
	}
	
	public void setOther(Integer other) {
		Other = other;
	}


	@Override
	public String toString() {
		return "Page{" +
				"data=" + data +
				", total=" + total +
				", start=" + start +
				", pageSize=" + pageSize +
				", resultCount=" + resultCount +
				", Other=" + Other +
				'}';
	}
}
