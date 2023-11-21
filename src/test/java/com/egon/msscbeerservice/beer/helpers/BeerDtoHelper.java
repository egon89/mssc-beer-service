package com.egon.msscbeerservice.beer.helpers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;

import java.util.UUID;

public class BeerDtoHelper {
  public static final UUID ID = UUID.fromString("aeb60d96-6aa1-4c2e-a82b-1683a7439d47");
  public static final String NAME = "Beer 1";
  public static final BeerStyleEnum STYLE = BeerStyleEnum.ALE;

  public static BeerDto create() {
    return beerDtoBuilder().build();
  }

  public static BeerDto createWithId(UUID id) {
    return beerDtoBuilder()
        .id(id)
        .build();
  }

  public static BeerDto createWithoutId() {
    return beerDtoBuilder()
        .id(null)
        .build();
  }

  private static BeerDto.BeerDtoBuilder beerDtoBuilder() {
    return BeerDto.builder()
        .id(ID)
        .name(NAME)
        .style(STYLE);
  }
}
