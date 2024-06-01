package com.egon.msscbeerservice.beer.services.impl;

import com.egon.msscbeerservice.beer.dtos.response.BeerInventoryIntegrationResponseDto;
import com.egon.msscbeerservice.beer.integrations.InventoryFeignClientIntegration;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Slf4j
@Profile("local-discovery")
@Service
@RequiredArgsConstructor
public class GetOnHandBeerInventoryFeignClientServiceImpl implements GetOnHandBeerInventoryService {

  private final InventoryFeignClientIntegration inventoryIntegration;

  @Override
  public Integer execute(UUID beerId) {
    log.debug("Feign client: getting on hand inventory for beer {}", beerId);
    final var beerInventoryResponse = inventoryIntegration.getBeerInventoryList(beerId);
    final var beerInventoryList = Optional.ofNullable(beerInventoryResponse)
        .map(HttpEntity::getBody)
        .orElse(List.of());
    log.debug("Inventory size for beer {}", beerInventoryList.size());

    final var quantityOnHandTotal =
        beerInventoryList
            .stream()
            .mapToInt(BeerInventoryIntegrationResponseDto::quantityOnHand)
            .sum();
    log.debug("Quantity on hand for beer {}: {}", beerId, quantityOnHandTotal);

    return quantityOnHandTotal;
  }
}
