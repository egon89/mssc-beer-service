package com.egon.msscbeerservice.shared.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface CrudWithPagingAndSortRepository<T, ID>
        extends CrudRepository<T, ID>, PagingAndSortingRepository<T, ID> {
}
