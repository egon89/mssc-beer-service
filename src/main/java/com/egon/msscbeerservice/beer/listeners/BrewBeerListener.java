package com.egon.msscbeerservice.beer.listeners;

import com.egon.msscbeerservice.beer.dtos.events.BeerEventDto;

public interface BrewBeerListener {
  void listen(BeerEventDto eventDto);
}
