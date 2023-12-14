package com.egon.msscbeerservice.beer.helpers;

import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class BeerHelper {
  public static final UUID ID = UUID.fromString("aeb60d96-6aa1-4c2e-a82b-1683a7439d47");
  public static final String NAME = "Beer 1";
  public static final BeerStyleEnum STYLE = BeerStyleEnum.ALE;
  public static final long UPC = 123L;
  public static final BigDecimal PRICE = BigDecimal.valueOf(10.90);
  public static final LocalDateTime CREATED_AT = LocalDateTime.of(2023, 12, 13, 0, 0);
  public static final LocalDateTime UPDATED_AT = LocalDateTime.of(2023, 12, 14, 0, 0);
  public static final Integer MIN_ON_HAND = 50;
  public static final Integer QUANTITY_TO_BREW = 10;
  public static final Long VERSION = 1L;
}
