package com.egon.msscbeerservice.beer.dtos;

import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;

public record ListBeerFilterDto(String name, BeerStyleEnum style) {
  static public ListBeerFilterDto create(String name, BeerStyleEnum style) {
    return new ListBeerFilterDto(name, style);
  }
}
