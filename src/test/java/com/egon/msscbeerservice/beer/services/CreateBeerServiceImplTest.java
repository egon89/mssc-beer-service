package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.helpers.BeerDtoHelper;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CreateBeerServiceImplTest {

  @Autowired
  private CreateBeerServiceImpl service;

  @Test
  void execute() {
    var result = service.execute(BeerDtoHelper.createWithoutId());
    assertThat(result).isNotNull();
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