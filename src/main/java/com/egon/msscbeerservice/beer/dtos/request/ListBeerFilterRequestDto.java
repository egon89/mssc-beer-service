package com.egon.msscbeerservice.beer.dtos.request;

import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ListBeerFilterRequestDto {

  @Min(0)
  private Integer pageNumber = 0;

  @Positive()
  private Integer pageSize = 25;

  private String name;

  private BeerStyleEnum style;

  private Boolean showInventoryOnHand = false;
}
