package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;

public interface FindBeerByUpcService {
  BeerDto execute(Long upc);
}
