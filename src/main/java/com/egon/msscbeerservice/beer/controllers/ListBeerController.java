package com.egon.msscbeerservice.beer.controllers;

import com.egon.msscbeerservice.beer.dtos.BeerPagedListDto;
import com.egon.msscbeerservice.beer.dtos.ListBeerFilterDto;
import com.egon.msscbeerservice.beer.dtos.request.ListBeerFilterRequestDto;
import com.egon.msscbeerservice.beer.services.ListBeerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("api/v1/beers")
@RestController
@RequiredArgsConstructor
public class ListBeerController {

  private final ListBeerService service;

  @GetMapping()
  public ResponseEntity<BeerPagedListDto> listBeer(@Valid ListBeerFilterRequestDto filter) {
    log.debug("list beers");
    var beerPage = service.execute(
        ListBeerFilterDto.create(filter.getName(), filter.getStyle()),
        PageRequest.of(filter.getPageNumber(), filter.getPageSize()));

    return ResponseEntity.ok(beerPage);
  }
}
