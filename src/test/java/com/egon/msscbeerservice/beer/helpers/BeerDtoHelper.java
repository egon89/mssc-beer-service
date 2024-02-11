package com.egon.msscbeerservice.beer.helpers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@UtilityClass
public class BeerDtoHelper extends BeerHelper {
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
        .style(STYLE)
        .upc(UPC)
        .price(PRICE)
        .minOnHand(MIN_ON_HAND)
        .createdAt(OffsetDateTime.of(CREATED_AT, ZoneOffset.UTC))
        .updatedAt(OffsetDateTime.of(UPDATED_AT, ZoneOffset.UTC));
  }
}
