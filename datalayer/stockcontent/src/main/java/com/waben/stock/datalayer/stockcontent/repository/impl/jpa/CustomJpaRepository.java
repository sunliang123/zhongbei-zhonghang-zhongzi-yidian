package com.waben.stock.datalayer.stockcontent.repository.impl.jpa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface CustomJpaRepository<T,S extends Serializable> extends Repository<T,S> {
    T save(T t);

    Page<T> findAll(Specification<T> specification, Pageable page);

    Page<T> findAll(Pageable pageable);

    List<T> findAll();

    T findById(S id);

    void delete(S id);
}