package com.waben.stock.interfaces.pojo.query;

import com.waben.stock.interfaces.util.CopyBeanUtils;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Created by yuyidi on 2017/11/19.
 * @desc
 */
public class PageInfo<T> {

    private List<T> content;
    private Integer totalPages;
    private Boolean last;
    private Long totalElements;
    private Integer size;
    private Integer number;
    private Boolean frist;

    public PageInfo() {
    }

    public PageInfo(List<T> content, Integer totalPages, Boolean last, Long totalElements, Integer size, Integer
            number, Boolean frist) {
        this.content = content;
        this.totalPages = totalPages;
        this.last = last;
        this.totalElements = totalElements;
        this.size = size;
        this.number = number;
        this.frist = frist;
    }

    public PageInfo(Page<?> page, Class<T> tClass) {
        this.content = CopyBeanUtils.copyListBeanPropertiesToList(page.getContent(), tClass);
        this.totalPages = page.getTotalPages();
        this.last = page.isLast();
        this.totalElements = page.getTotalElements();
        this.size = page.getSize();
        this.number = page.getNumber();
        this.frist = page.isFirst();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getFrist() {
        return frist;
    }

    public void setFrist(Boolean frist) {
        this.frist = frist;
    }
}
