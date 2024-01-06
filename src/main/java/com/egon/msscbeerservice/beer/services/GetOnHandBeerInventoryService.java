package com.egon.msscbeerservice.beer.services;

import java.util.UUID;

public interface GetOnHandBeerInventoryService {
  Integer execute(UUID beerId);
}
