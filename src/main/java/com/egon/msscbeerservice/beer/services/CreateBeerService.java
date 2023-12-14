package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public interface CreateBeerService {
  BeerDto execute(@NotNull @Valid BeerDto beer);
}
