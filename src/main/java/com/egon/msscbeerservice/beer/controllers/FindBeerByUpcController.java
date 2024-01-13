package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.services.FindBeerByUpcService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/beers")
@RestController
@RequiredArgsConstructor
public class FindBeerByUpcController {

  private final FindBeerByUpcService findBeerByUpcService;

  @GetMapping("/upc/{upc}")
  public ResponseEntity<BeerDto> findByUpc(@PathVariable Long upc) {
    return ResponseEntity.ok(findBeerByUpcService.execute(upc));
  }
}
