package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBeerServiceImpl implements DeleteBeerService {

  private final BeerRepository repository;

  @Override
  public void execute(UUID id) {
    repository.deleteById(id);
  }
}
