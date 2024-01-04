package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerPagedListDto;
import com.egon.msscbeerservice.beer.dtos.ListBeerFilterDto;
import org.springframework.data.domain.PageRequest;

public interface ListBeerService {
  BeerPagedListDto execute(ListBeerFilterDto filter, PageRequest pageRequest);
}
