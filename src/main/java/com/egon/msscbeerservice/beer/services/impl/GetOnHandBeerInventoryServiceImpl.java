package com.egon.msscbeerservice.beer.services.impl;

import com.egon.msscbeerservice.beer.dtos.response.BeerInventoryIntegrationResponseDto;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@Profile("!local-discovery")
@Service
@RequiredArgsConstructor
public class GetOnHandBeerInventoryServiceImpl implements GetOnHandBeerInventoryService {

  @Value("${beer.inventory.service.host}")
  private String host;

  private final RestTemplate restTemplate;

  @Override
  public Integer execute(UUID beerId) {
    log.debug("Getting on hand inventory for beer %s".formatted(beerId.toString()));
    var beerInventoryList =
        restTemplate.getForObject(host.concat(INVENTORY_PATH), BeerInventoryIntegrationResponseDto[].class, beerId);
    var quantityOnHandTotal =
        Arrays.stream(Objects.requireNonNull(beerInventoryList))
            .mapToInt(BeerInventoryIntegrationResponseDto::quantityOnHand)
            .sum();
    log.debug("Quantity on hand for beer %s: %d".formatted(beerId.toString(), quantityOnHandTotal));

    return quantityOnHandTotal;
  }
}
