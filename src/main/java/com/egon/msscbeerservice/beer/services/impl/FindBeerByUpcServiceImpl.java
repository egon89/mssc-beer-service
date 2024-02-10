package com.egon.msscbeerservice.beer.services.impl;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.beer.services.FindBeerByUpcService;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindBeerByUpcServiceImpl implements FindBeerByUpcService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Cacheable(cacheNames = "findBeerByUpcCache", key = "#upc")
  @Override
  public BeerDto execute(Long upc) {
    log.debug("called find beer by upc for upc {}", upc);
    return repository.findByUpc(upc)
        .map(mapper::toDto)
        .orElseThrow(NotFoundException::new);
  }
}
