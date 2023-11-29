package com.egon.msscbeerservice.beer.repositories;

import com.egon.msscbeerservice.beer.entities.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
