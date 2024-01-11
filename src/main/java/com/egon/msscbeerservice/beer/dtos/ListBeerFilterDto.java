package com.egon.msscbeerservice.beer.dtos;

import com.egon.msscbeerservice.beer.enums.BeerStyleEnum;

import java.io.Serializable;

public record ListBeerFilterDto(String name, BeerStyleEnum style) implements Serializable {
  static public ListBeerFilterDto create(String name, BeerStyleEnum style) {
    return new ListBeerFilterDto(name, style);
  }
}
