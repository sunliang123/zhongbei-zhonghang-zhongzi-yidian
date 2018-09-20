package com.waben.stock.datalayer.buyrecord.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author Created by yuyidi on 2017/9/20.
 * @desc
 */
public interface BaseDao<T, S extends Serializable> {
    T create(T t);

    void delete(S id);

    T update(T t);

    T retrieve(S id);

    Page<T> page(int page, int limit);

    Page<T> page(Specification<T> specification, Pageable pageable);

    List<T> list();
}
