package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateBeerServiceImpl implements CreateBeerService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Override
  public BeerDto execute(BeerDto beer) {
    final var beerEntity = repository.save(mapper.toEntity(beer));

    return mapper.toDto(beerEntity);
  }
}
