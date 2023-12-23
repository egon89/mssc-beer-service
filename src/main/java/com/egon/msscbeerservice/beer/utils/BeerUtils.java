package com.egon.msscbeerservice.beer.utils;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.entities.BeerEntity;
import com.egon.msscbeerservice.shared.interfaces.BiFunctionCustom;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BeerUtils {
  private static final BiFunctionCustom<BeerDto, BeerEntity> setName = (beerDto, beerEntity) -> {
    beerEntity.setName(beerDto.getName());

    return beerEntity;
  };

  private static final BiFunctionCustom<BeerDto, BeerEntity> setStyle = (beerDto, beerEntity) -> {
    beerEntity.setStyle(beerDto.getStyle().toString());

    return beerEntity;
  };

  private static final BiFunctionCustom<BeerDto, BeerEntity> setPrice = (beerDto, beerEntity) -> {
    beerEntity.setPrice(beerDto.getPrice());

    return beerEntity;
  };
  private static final BiFunctionCustom<BeerDto, BeerEntity> setUpc = (beerDto, beerEntity) -> {
    beerEntity.setUpc(beerDto.getUpc());

    return beerEntity;
  };

  public static void setUpdatableValuesToEntity(BeerDto dto, BeerEntity entity) {
        setName
            .andThen(setStyle)
            .andThen(setPrice)
            .andThen(setUpc)
            .apply(dto, entity);
  }
}
