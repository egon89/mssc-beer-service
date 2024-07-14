package com.egon.msscbeerservice.beer.integrations;

import com.egon.msscbeerservice.beer.dtos.response.BeerInventoryIntegrationResponseDto;
import com.egon.msscbeerservice.beer.integrations.impl.FailOverInventoryFeignClientIntegrationImpl;
import com.egon.msscbeerservice.beer.services.GetOnHandBeerInventoryService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.UUID;

// the spring.application.name of the target service that is recognized by the eureka
@FeignClient(name = "beer-inventory-service", fallback = FailOverInventoryFeignClientIntegrationImpl.class)
public interface InventoryFeignClientIntegration {

  @RequestMapping(method = RequestMethod.GET, value = GetOnHandBeerInventoryService.INVENTORY_PATH)
  ResponseEntity<List<BeerInventoryIntegrationResponseDto>> getBeerInventoryList(@PathVariable UUID beerId);
}
