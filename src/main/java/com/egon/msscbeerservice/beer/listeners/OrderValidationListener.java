package com.egon.msscbeerservice.beer.listeners;

import com.egon.brewery.dtos.events.ValidateBeerOrderRequest;

public interface OrderValidationListener {
  void listen(ValidateBeerOrderRequest beerOrder);
}
