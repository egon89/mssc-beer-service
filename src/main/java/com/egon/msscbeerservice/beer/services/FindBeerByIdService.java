package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;

import java.util.UUID;

public interface FindBeerByIdService {
  BeerDto execute(UUID id);
}
