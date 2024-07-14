package com.egon.msscbeerservice.beer.integrations.impl;

import com.egon.msscbeerservice.beer.dtos.response.BeerInventoryIntegrationResponseDto;
import com.egon.msscbeerservice.beer.integrations.InventoryFailOverFeignClientIntegration;
import com.egon.msscbeerservice.beer.integrations.InventoryFeignClientIntegration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FailOverInventoryFeignClientIntegrationImpl implements InventoryFeignClientIntegration {

  private final InventoryFailOverFeignClientIntegration failOverFeignClientIntegration;

  @Override
  public ResponseEntity<List<BeerInventoryIntegrationResponseDto>> getBeerInventoryList(UUID beerId) {
    log.debug("Calling fail-over inventory integration for beer {}", beerId);

    return failOverFeignClientIntegration.getBeerInventoryList();
  }
}
