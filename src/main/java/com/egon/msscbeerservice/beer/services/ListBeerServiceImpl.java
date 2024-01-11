package com.egon.msscbeerservice.beer.services;

import com.egon.msscbeerservice.beer.dtos.BeerPagedListDto;
import com.egon.msscbeerservice.beer.dtos.ListBeerFilterDto;
import com.egon.msscbeerservice.beer.mappers.BeerMapper;
import com.egon.msscbeerservice.beer.repositories.BeerRepository;
import com.egon.msscbeerservice.beer.repositories.specifications.BeerSpecificationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ListBeerServiceImpl implements ListBeerService {

  private final BeerRepository repository;

  private final BeerMapper mapper;

  @Cacheable(cacheNames = "listBeerCache", condition = "#showInventoryOnHand == false ")
  @Override
  public BeerPagedListDto execute(ListBeerFilterDto filter, PageRequest pageRequest, Boolean showInventoryOnHand) {
    log.debug("called list beers");
    final var page = repository.findAll(BeerSpecificationUtil.byFilter(filter), pageRequest);

    return new BeerPagedListDto(
        page.getContent().stream().map(beerEntity -> mapper.toDto(beerEntity, showInventoryOnHand)).toList(),
        PageRequest.of(page.getPageable().getPageNumber(), page.getPageable().getPageSize()),
        page.getTotalElements());
  }
}
