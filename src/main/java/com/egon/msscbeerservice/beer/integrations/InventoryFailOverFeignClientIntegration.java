package com.egon.msscbeerservice.beer.integrations;

import com.egon.msscbeerservice.beer.dtos.response.BeerInventoryIntegrationResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

// the spring application name of the fail-over service
@FeignClient(name = "inventory-fail-over-service")
public interface InventoryFailOverFeignClientIntegration {

  @RequestMapping(method = RequestMethod.GET, value = "/inventory-fail-over")
  ResponseEntity<List<BeerInventoryIntegrationResponseDto>> getBeerInventoryList();
}
