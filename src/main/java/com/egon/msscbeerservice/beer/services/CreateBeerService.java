package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;

public interface CreateBeerService {
  BeerDto execute(BeerDto beer);
}
