package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.helpers.BeerEntityHelper;
import com.egon.msscbeerservice.beer.helpers.BeerHelper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.shared.exceptions.NotFoundException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class FindBeerByIdServiceImplTest {

  @Autowired
  private FindBeerByIdServiceImpl service;

  @MockBean
  private BeerRepository repository;

  @Test
  void shouldFindABeerById() {
    val beerEntity = BeerEntityHelper.create();
    val expected = BeerDtoHelper.create();
    expected.setVersion(1L);
    when(repository.findById(any())).thenReturn(Optional.of(beerEntity));

    val result = service.execute(BeerHelper.ID, Boolean.FALSE);

    assertThat(result).isNotNull();
    assertThat(result).isEqualTo(expected);
    verify(repository, times(1)).findById(BeerHelper.ID);
  }

  @Test
  void shouldThrowNotFoundExceptionWhenABeerNotExist() {
    when(repository.findById(any())).thenThrow(NotFoundException.class);

    assertThrows(NotFoundException.class, () -> service.execute(BeerHelper.ID, Boolean.FALSE));
  }
}