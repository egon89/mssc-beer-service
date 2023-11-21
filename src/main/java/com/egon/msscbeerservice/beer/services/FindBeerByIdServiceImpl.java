package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FindBeerByIdServiceImpl implements FindBeerByIdService {
  @Override
  public BeerDto execute(UUID id) {
    return BeerDto.builder().id(UUID.randomUUID())
        .name("Galaxy Cat")
        .style(BeerStyleEnum.IPA)
        .build();
  }
}
