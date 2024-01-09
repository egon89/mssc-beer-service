package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.helpers.BeerHelper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class DeleteBeerServiceImplTest {

  @Autowired
  private DeleteBeerServiceImpl service;

  @MockBean
  private BeerRepository repository;

  @MockBean
  private GetOnHandBeerInventoryService getOnHandBeerInventoryService;

  @Test
  void shouldDeleteABeer() {
    doNothing().when(repository).deleteById(any());

    service.execute(BeerHelper.ID);

    verify(repository, times(1)).deleteById(BeerHelper.ID);
  }
}