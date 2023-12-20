package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import com.egon.msscbeerservice.beer.helpers.BeerEntityHelper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import jakarta.validation.ConstraintViolationException;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@SpringBootTest
class CreateBeerServiceImplTest {

  @Autowired
  private CreateBeerServiceImpl service;

  @MockBean
  private BeerRepository repository;

  @Test
  void shouldCreateABeer() {
    when(repository.save(any())).thenReturn(BeerEntityHelper.create());

    val result = service.execute(BeerDtoHelper.createWithoutId());

    assertThat(result).isNotNull();
    verify(repository, times(1)).save(
        argThat(beerParameter -> Objects.isNull(beerParameter.getId())));
  }

  @ParameterizedTest
  @MethodSource("provideInvalidDto")
  void shouldThrowErrorWhenDtoDoesNotHaveRequiredFields(BeerDto dto) {
    assertThrows(ConstraintViolationException.class, () -> service.execute(dto));
  }

  private static Stream<Arguments> provideInvalidDto() {
    return Stream.of(
        Arguments.of((BeerDto) null),
        Arguments.of(BeerDto.builder().build()));
  }
}