package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.services.DeleteBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/beers")
@RequiredArgsConstructor
public class DeleteBeerController {

  private final DeleteBeerService deleteBeerService;

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBeer(@PathVariable UUID id) {
    deleteBeerService.execute(id);

    return ResponseEntity.noContent().build();
  }
}
