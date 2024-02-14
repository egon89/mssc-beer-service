package com.egon.msscbeerservice.beer.listeners;

import com.egon.common.events.dtos.LowInventoryBeerEventDto;

public interface BrewBeerListener {
  void listen(LowInventoryBeerEventDto eventDto);
}
