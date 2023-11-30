package com.egon.msscbeerservice.beer.repositories;

import com.egon.msscbeerservice.beer.entities.Beer;
import com.egon.msscbeerservice.shared.interfaces.CrudWithPagingAndSortRepository;

import java.util.UUID;

public interface BeerRepository extends CrudWithPagingAndSortRepository<Beer, UUID> {
}
