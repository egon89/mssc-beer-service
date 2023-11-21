package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class UpdateBeerServiceImpl implements UpdateBeerService {
  @Override
  public BeerDto execute(UUID id, BeerDto beerDto) {
    log.debug("updating beer");
    return null;
  }
}
