package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FindBeerByIdServiceImpl implements FindBeerByIdService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Cacheable(cacheNames = "beerCache", key = "#id", condition = "#showInventoryOnHand == false ")
  @Override
  public BeerDto execute(UUID id, Boolean showInventoryOnHand) {
    log.debug("called find beer by id for id %s".formatted(id.toString()));
    final var beerEntity = repository.findById(id).orElseThrow(NotFoundException::new);

    return mapper.toDto(beerEntity, showInventoryOnHand);
  }
}
