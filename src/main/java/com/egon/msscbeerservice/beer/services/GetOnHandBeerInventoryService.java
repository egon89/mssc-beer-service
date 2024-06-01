package com.egon.msscbeerservice.beer.services;

import java.util.UUID;

public interface GetOnHandBeerInventoryService {
  String INVENTORY_PATH = "/beers/{beerId}/inventory";

  Integer execute(UUID beerId);
}
