package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.dtos.BeerDto;
import com.egon.msscbeerservice.beer.services.FindBeerByIdService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("api/v1/beers")
@RestController
@RequiredArgsConstructor
public class FindBeerByIdController {

  private final FindBeerByIdService findBeerByIdService;

  @GetMapping("/{id}")
  public ResponseEntity<BeerDto> findById(@PathVariable UUID id) {
    return ResponseEntity.ok(findBeerByIdService.execute(id));
  }
}
