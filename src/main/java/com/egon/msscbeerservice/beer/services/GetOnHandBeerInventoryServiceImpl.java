package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.response.BeerInventoryIntegrationResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;


@Slf4j
@Service
@RequiredArgsConstructor
public class GetOnHandBeerInventoryServiceImpl implements GetOnHandBeerInventoryService {

  @Value("${beer.inventory.service.host}")
  private String host;

  private final static String INVENTORY_PATH = "/beers/{beerId}/inventory";

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
