package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FindBeerByIdServiceImpl implements FindBeerByIdService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Override
  public BeerDto execute(UUID id, Boolean showInventoryOnHand) {
    final var beerEntity = repository.findById(id).orElseThrow(NotFoundException::new);

    return mapper.toDto(beerEntity, showInventoryOnHand);
  }
}
