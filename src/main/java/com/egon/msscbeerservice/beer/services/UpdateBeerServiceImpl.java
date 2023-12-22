package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateBeerServiceImpl implements UpdateBeerService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Override
  public BeerDto execute(UUID id, BeerDto beerDto) {
    val beerEntity = repository.findById(id).orElseThrow(NotFoundException::new);
    // TODO: refactor
    beerEntity.setName(beerDto.getName());
    beerEntity.setStyle(beerDto.getStyle().toString());
    beerEntity.setPrice(beerDto.getPrice());
    beerEntity.setUpc(beerDto.getUpc());

    val beerUpdated = repository.save(beerEntity);

    log.debug("updating beer");
    return mapper.toDto(beerUpdated);
  }
}
