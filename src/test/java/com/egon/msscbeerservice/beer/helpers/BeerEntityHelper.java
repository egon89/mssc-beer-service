package com.egon.msscbeerservice.beer.helpers;

import com.egon.msscbeerservice.beer.entities.BeerEntity;
import lombok.experimental.UtilityClass;

import java.sql.Timestamp;

@UtilityClass
public class BeerEntityHelper extends BeerHelper {
  public static BeerEntity create() {
    return BeerEntity.builder()
        .id(ID)
        .name(NAME)
        .style(STYLE.toString())
        .price(PRICE)
        .upc(UPC)
        .minOnHand(MIN_ON_HAND)
        .quantityToBrew(QUANTITY_TO_BREW)
        .version(VERSION)
        .createdAt(Timestamp.valueOf(CREATED_AT))
        .updatedAt(Timestamp.valueOf(UPDATED_AT))
        .build();
  }
}
