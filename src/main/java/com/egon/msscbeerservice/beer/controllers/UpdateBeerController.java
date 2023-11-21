package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.services.UpdateBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/beers")
@RequiredArgsConstructor
public class UpdateBeerController {

  private final UpdateBeerService updateBeerService;

   @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Void> updateBeer(@PathVariable UUID id, @RequestBody BeerDto beerDto) {
    updateBeerService.execute(id, beerDto);
    return ResponseEntity.noContent().build();
  }
}
